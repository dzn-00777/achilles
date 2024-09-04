package org.earthdog.achilles;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.earthdog.achilles.exception.ClassCreateException;
import org.earthdog.achilles.service.impl.EditOperationsServiceImpl;
import org.earthdog.achilles.tools.loader.AbstractApiLoadedClass;
import org.earthdog.achilles.tools.loader.ApiLoadedClass;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;
import org.earthdog.achilles.tools.loader.SourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.earthdog.achilles.tools.managers.AchillesContext.LOADED_CLASS_MANAGER;

/**
 * @Date 2024/7/14 13:54
 * @Author DZN
 * @Desc Test
 */
public class Test {

    static GroovyShell groovyShell = new GroovyShell();

    public static void main(String[] args) throws ClassCreateException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        SourceLoader sourceLoader = new SourceLoader(threadPool, new EditOperationsServiceImpl(new JdbcTemplate()));

        loadCLass(sourceLoader);
        loadCLass(sourceLoader);
        loadCLass(sourceLoader);

        String script = "a+b";
        Map<String, Object> env = new HashMap<>();
        env.put("a", 1);
        env.put("b", 2);

        for (int i = 0; i < 10; i++) {
            Object execute = execute(script, env);
            System.out.println(execute);
        }
    }

    private static void loadCLass(SourceLoader sourceLoader) throws ClassCreateException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        long start = System.currentTimeMillis();
        ApiMappingInfo apiMappingInfo = ApiMappingInfo.builder()
                .method("GET")
                .path("test")
                .qualifiedName("com.dzn.Test")
                .code("""
                        package com.dzn;

                        import org.earthdog.achilles.entity.vo.BaseVO;
                        import org.earthdog.achilles.tools.loader.AbstractApiLoadedClass;

                        /**
                         * @Date 2024/7/14 14:16
                         * @Author DZN
                         * @Desc Test
                         */
                        public class Test extends AbstractApiLoadedClass {
                            @Override
                            public BaseVO<Object> execute() {
                                System.out.println("Already execute...");
                                return null;
                            }
                        }
                        """).build();
        sourceLoader.load(apiMappingInfo);
        ApiLoadedClass apiLoadedClass = LOADED_CLASS_MANAGER.getApiLoadedClass(apiMappingInfo);
        long objNewEnd = System.currentTimeMillis();
        System.out.println("obj create time = " + (objNewEnd - start));
        apiLoadedClass.run(apiMappingInfo);
        System.out.println("obj run time = " + (System.currentTimeMillis() - objNewEnd));
    }


    public static Object execute(String scriptText, Map<String, Object> env) {
        long start = System.currentTimeMillis();
        Script script = groovyShell.parse(scriptText);
        script.setBinding(new Binding(env));
        Object run = script.run();
        System.out.println("script run time = " + (System.currentTimeMillis() - start));
        return run;
    }


}

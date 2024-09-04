package org.earthdog.achilles.tools.loader;

import org.earthdog.achilles.exception.ClassCreateException;
import org.earthdog.achilles.service.EditOperationsService;
import org.earthdog.achilles.tools.aop.AopClass;
import org.earthdog.achilles.tools.interceptor.Interceptor;
import org.quartz.Job;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.earthdog.achilles.tools.managers.AchillesContext.*;

/**
 * @Date 2024/7/7 12:00
 * @Author DZN
 * @Desc Test
 */
@Component
public class SourceLoader {

    public static final String JAVA_EXTENSION = ".java";
    public static final String CLASS_EXTENSION = ".class";
    private final JavaCompiler compiler;
    private final SourceJavaFileManager fileManager;
    private final ThreadPoolTaskExecutor threadPool;
    private final EditOperationsService editOperationsService;

    public SourceLoader(ThreadPoolTaskExecutor threadPool, EditOperationsService editOperationsService) {
        this.threadPool = threadPool;
        this.editOperationsService = editOperationsService;
        compiler = ToolProvider.getSystemJavaCompiler();
        // 获取标准的Java文件管理器实例
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, Locale.CHINA, StandardCharsets.UTF_8);
        // 初始化自定义Java文件管理器实例
        fileManager = new SourceJavaFileManager(manager);
    }

    public void load(ApiMappingInfo apiMappingInfo) throws ClassCreateException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String qualifiedName = apiMappingInfo.getQualifiedName();
        String code = apiMappingInfo.getCode();
        fileManager.callPre(qualifiedName);
        String className = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
        // 构建Java源文件实例
        SourceSimpleJavaFileObject javaFileObject = new SourceSimpleJavaFileObject(className, code);
        // 添加Java源文件实例到自定义Java文件管理器实例中
        fileManager.addJavaFileObject(qualifiedName, javaFileObject);
        // 初始化一个编译任务实例
        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null,
                fileManager,
                null,
                null,
                null,
                Collections.singletonList(javaFileObject)
        );
        // 执行编译任务
        Boolean call = compilationTask.call();
        if (!call) {
            throw new ClassCreateException(qualifiedName, code);
        }
        ClassLoader classLoader = fileManager.getClassLoader(null);
        Class<?> klass = classLoader.loadClass(qualifiedName);
        Object object = klass.getDeclaredConstructor().newInstance();
        saveClassByteCode(javaFileObject.getByteCode(), apiMappingInfo.getId());
        postLoad(apiMappingInfo, object);
        //TODO log
    }

    private void saveClassByteCode(byte[] byteCode, Integer id) {
        editOperationsService.saveClassByteCode(byteCode, id);
    }

    private void postLoad(ApiMappingInfo apiMappingInfo, Object object) {
        if (object instanceof ApiLoadedClass apiLoadedClass) {
            LOADED_CLASS_MANAGER.putApiLoadedClass(apiMappingInfo, apiLoadedClass);
        } else if (object instanceof Interceptor interceptor) {
            INTERCEPTOR_MANAGER.add(interceptor);
        } else if (object instanceof AopClass aopClass) {
            AOP_MANAGER.putAopClass(apiMappingInfo, aopClass);
        } else if (object instanceof Job job) {
            JOB_MANAGER.addJob(apiMappingInfo, job);
        }
    }

    public void loadAll() {
        List<ApiMappingInfo> apiMappingInfoList = editOperationsService.findAllWithRun();
        for (ApiMappingInfo apiMappingInfo : apiMappingInfoList) {
            System.out.println(apiMappingInfo);
        }
    }

}

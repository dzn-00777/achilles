package org.earthdog.achilles.tools.loader;

import lombok.Setter;

import javax.tools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2024/7/7 21:26
 * @Author DZN
 * @Desc StringOfJavaFileManager
 */
public class SourceJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private SourceClassLoader classLoader;
    private final Map<String, JavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();
    public SourceJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) {
        return javaFileObjectMap.get(packageName + "." + relativeName);
    }

    /**
     * 这里是编译器返回的同(源)Java文件对象,替换为SourceSimpleJavaFileObject实现
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        return javaFileObjectMap.get(classLoader.getQualifiedName());
    }

    /**
     * 这里覆盖原来的类加载器
     */
    @Override
    public ClassLoader getClassLoader(Location location) {
        return classLoader;
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        if (file instanceof SourceSimpleJavaFileObject) {
            return file.getName();
        }
        return super.inferBinaryName(location, file);
    }

    @Override
    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        Iterable<JavaFileObject> superResult = super.list(location, packageName, kinds, recurse);
        List<JavaFileObject> result = new ArrayList<>();
        // 这里要区分编译的Location以及编译的Kind
        if (location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
            // .class文件以及classPath下
            for (JavaFileObject file : javaFileObjectMap.values()) {
                if (file.getKind() == JavaFileObject.Kind.CLASS && file.getName().startsWith(packageName)) {
                    result.add(file);
                }
            }
            // 这里需要额外添加类加载器加载的所有Java文件对象
            result.addAll(javaFileObjectMap.values());
        } else if (location == StandardLocation.SOURCE_PATH && kinds.contains(JavaFileObject.Kind.SOURCE)) {
            // .java文件以及编译路径下
            for (JavaFileObject file : javaFileObjectMap.values()) {
                if (file.getKind() == JavaFileObject.Kind.SOURCE && file.getName().startsWith(packageName)) {
                    result.add(file);
                }
            }
        }
        for (JavaFileObject javaFileObject : superResult) {
            result.add(javaFileObject);
        }
        return result;
    }

    /**
     * 自定义方法,用于添加和缓存待编译的源文件对象
     */
    public void addJavaFileObject(String qualifiedName, JavaFileObject javaFileObject) {
        javaFileObjectMap.put(qualifiedName, javaFileObject);
    }

    public JavaFileObject getJavaFileObj(String qualifiedName) {
        return javaFileObjectMap.get(qualifiedName);
    }

    public void callPre(String qualifiedName) {
        classLoader = new SourceClassLoader(this, qualifiedName);

    }
}


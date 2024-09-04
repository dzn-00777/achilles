package org.earthdog.achilles.tools.loader;

import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Date 2024/7/7 10:28
 * @Author DZN
 * @Desc Test
 */

public class SourceClassLoader extends ClassLoader {

    private final SourceJavaFileManager fileManager;
    @Getter
    private final String qualifiedName;

    public SourceClassLoader(SourceJavaFileManager fileManager, String qualifiedName) {
        this.fileManager = fileManager;
        this.qualifiedName = qualifiedName;
    }

    @Override
    protected Class<?> findClass(String name) {
        if (!name.equals(qualifiedName))
            throw new IllegalArgumentException("this.qualifiedName is not equal the param name.");
        SourceSimpleJavaFileObject javaFileObj = (SourceSimpleJavaFileObject) fileManager.getJavaFileObj(name);
        byte[] byteCode = javaFileObj.getByteCode();
        return defineClass(name, byteCode, 0, byteCode.length);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name.endsWith(SourceLoader.CLASS_EXTENSION)) {
            String qualifiedClassName = name.substring(0, name.length() - SourceLoader.CLASS_EXTENSION.length()).replace('/', '.');
            SourceSimpleJavaFileObject javaFileObject = (SourceSimpleJavaFileObject) fileManager.getJavaFileObj(qualifiedClassName);
            if (null != javaFileObject && null != javaFileObject.getByteCode()) {
                return new ByteArrayInputStream(javaFileObject.getByteCode());
            }
        }
        return super.getResourceAsStream(name);
    }

}

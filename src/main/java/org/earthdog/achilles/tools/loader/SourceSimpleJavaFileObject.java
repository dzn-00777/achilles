package org.earthdog.achilles.tools.loader;

import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Date 2024/7/7 10:48
 * @Author DZN
 * @Desc SimpleJavaFileObjectOfString
 */
public class SourceSimpleJavaFileObject extends SimpleJavaFileObject {

    private static URI fromClassName(String className) {
        try {
            return new URI(className);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(className, e);
        }
    }

    private ByteArrayOutputStream byteCode;
    private final CharSequence sourceCode;

    public SourceSimpleJavaFileObject(String className, CharSequence sourceCode) {
        super(fromClassName(className + SourceLoader.JAVA_EXTENSION), Kind.SOURCE);
        this.sourceCode = sourceCode;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return sourceCode;
    }

    @Override
    public InputStream openInputStream() {
        return new ByteArrayInputStream(getByteCode());
    }

    // 注意这个方法是编译结果回调的OutputStream，回调成功后就能通过下面的getByteCode()方法获取目标类编译后的字节码字节数组
    @Override
    public OutputStream openOutputStream() {
        byteCode = new ByteArrayOutputStream();
        return byteCode;
    }

    public byte[] getByteCode() {
        return byteCode.toByteArray();
    }

}

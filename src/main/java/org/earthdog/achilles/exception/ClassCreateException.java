package org.earthdog.achilles.exception;

/**
 * @Date 2024/7/7 11:32
 * @Author DZN
 * @Desc ClassCreateException
 */
public class ClassCreateException extends Exception {
    public ClassCreateException(String className, String executeCode) {
        System.out.println("className = " + className);
        System.out.println("executeCode = " + executeCode);
    }
}

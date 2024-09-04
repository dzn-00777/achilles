package org.earthdog.achilles.tools.consts;

/**
 * @Date 2024/7/11 12:21
 * @Author DZN
 * @Desc ApiStatus
 */
public enum ApiStatus {
    OK(200, "OK"),
    INTERCEPTED(30001, "被拦截了.");
    private final int value;
    private final String msg;

    ApiStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int value() {
        return value;
    }

    public String msg() {
        return msg;
    }
}

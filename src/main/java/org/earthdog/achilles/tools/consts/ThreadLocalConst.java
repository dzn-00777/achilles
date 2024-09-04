package org.earthdog.achilles.tools.consts;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Date 2024/7/11 12:05
 * @Author DZN
 * @Desc ThreadLocalConst
 */
public class ThreadLocalConst {

    public static ThreadLocal<JSONObject> paramsThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

    public static JSONObject getParams() {
        return paramsThreadLocal.get();
    }

    public static HttpServletRequest getRequest() {
        return requestThreadLocal.get();
    }

    public static HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

}

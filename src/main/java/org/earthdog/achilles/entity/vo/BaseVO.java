package org.earthdog.achilles.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.earthdog.achilles.tools.consts.ApiStatus;

/**
 * @Date 2024/7/11 11:11
 * @Author DZN
 * @Desc BaseVO
 */

@Data
@AllArgsConstructor
public class BaseVO<T> {

    private int code;
    private String msg;
    private T data;

    public BaseVO(ApiStatus apiStatus, T data) {
        this(apiStatus.value(), apiStatus.msg(), data);
    }

    public static BaseVO<Object> OK() {
        return new BaseVO<>(ApiStatus.OK, null);
    }

    public static <T> BaseVO<T> OK(T data) {
        return new BaseVO<>(ApiStatus.OK, data);
    }

    public static BaseVO<Object> fail(ApiStatus apiStatus) {
        return new BaseVO<>(apiStatus, null);
    }
}

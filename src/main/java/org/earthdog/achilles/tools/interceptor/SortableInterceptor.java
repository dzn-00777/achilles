package org.earthdog.achilles.tools.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.earthdog.achilles.tools.consts.ThreadLocalConst;
import org.earthdog.achilles.tools.loader.ApiLoadedClass;

/**
 * @Date 2024/7/11 14:44
 * @Author DZN
 * @Desc SortableInterceptor
 */
@Setter
public abstract class SortableInterceptor implements Interceptor {

    protected int order;

    @Override
    public boolean preHandle(ApiLoadedClass apiLoadedClass) {
        return preHandle(ThreadLocalConst.getRequest(), ThreadLocalConst.getResponse(), apiLoadedClass);
    }

    public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, ApiLoadedClass apiLoadedClass);

    public abstract int getOrder();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortableInterceptor that = (SortableInterceptor) o;

        return order == that.order;
    }

    @Override
    public int hashCode() {
        return order;
    }
}

package org.earthdog.achilles.tools.managers;

import lombok.Getter;
import org.earthdog.achilles.tools.interceptor.Interceptor;
import org.earthdog.achilles.tools.interceptor.SortableInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Date 2024/7/11 14:38
 * @Author DZN
 * @Desc InterceptorManager
 */

@Getter
public class InterceptorManager {

    private final List<Interceptor> interceptors = new CopyOnWriteArrayList<>();

    public void add(Interceptor interceptor) {
        if (!(interceptor instanceof SortableInterceptor sortableInterceptor)) {
            interceptors.add(interceptor);
            return;
        }
        int order = sortableInterceptor.getOrder();
        sortableInterceptor.setOrder(order);
        int index = binarySearch(order);
        interceptors.add(index, sortableInterceptor);
    }

    public void remove(Interceptor interceptor) {
        interceptors.remove(interceptor);
    }

    private int binarySearch(int order) {
        int left = 0;
        int right = interceptors.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            SortableInterceptor interceptor = (SortableInterceptor) interceptors.get(mid);
            if (order == interceptor.getOrder()) {
                return mid;
            } else if (order > interceptor.getOrder()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}

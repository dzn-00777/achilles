package org.earthdog.achilles.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.earthdog.achilles.entity.vo.BaseVO;
import org.earthdog.achilles.tools.consts.ApiStatus;
import org.earthdog.achilles.tools.interceptor.Interceptor;
import org.earthdog.achilles.tools.loader.ApiLoadedClass;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.earthdog.achilles.tools.managers.AchillesContext.*;

/**
 * @Date 2024/7/9 13:41
 * @Author DZN
 * @Desc BaseOperationController
 */

@RestController
public class BaseOperationController {

    @RequestMapping
    public BaseVO<Object> execute(HttpServletRequest request) {
        String path = request.getRequestURI().substring(1);
        ApiMappingInfo apiMappingInfo = ApiMappingInfo.builder()
                .path(path)
                .method(request.getMethod())
                .build();
        ApiLoadedClass apiLoadedClass = LOADED_CLASS_MANAGER.getApiLoadedClass(apiMappingInfo);
        for (Interceptor interceptor : INTERCEPTOR_MANAGER.getInterceptors()) {
            if (!interceptor.preHandle(apiLoadedClass)) {
                return BaseVO.fail(ApiStatus.INTERCEPTED);
            }
        }
        //TODO 将request解析为params
        return apiLoadedClass.run(apiMappingInfo);
    }

}

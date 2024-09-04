package org.earthdog.achilles.tools.loader;

import org.earthdog.achilles.entity.vo.BaseVO;
import org.springframework.http.ResponseEntity;

/**
 * @Date 2024/7/11 10:28
 * @Author DZN
 * @Desc LoadedClass
 */
public interface ApiLoadedClass {

    BaseVO<Object> run(ApiMappingInfo apiMappingInfo);

}

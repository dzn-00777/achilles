package org.earthdog.achilles.service;

import org.earthdog.achilles.entity.dto.ApiSaveDTO;
import org.earthdog.achilles.entity.vo.BaseVO;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;

import java.util.List;

/**
 * @Date 2024/7/13 11:42
 * @Author DZN
 * @Desc EditOperationsService
 */
public interface EditOperationsService {

    BaseVO<?> save(ApiSaveDTO apiSaveDTO);

    void saveClassByteCode(byte[] byteCode, Integer id);

    List<ApiMappingInfo> findAllWithRun();
}

package org.earthdog.achilles.service.impl;

import org.earthdog.achilles.entity.dto.ApiSaveDTO;
import org.earthdog.achilles.entity.vo.BaseVO;
import org.earthdog.achilles.service.EditOperationsService;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2024/7/13 11:43
 * @Author DZN
 * @Desc EditOperationsServiceImpl
 */

@Service
public class EditOperationsServiceImpl implements EditOperationsService {

    private final JdbcTemplate jdbcTemplate;

    public EditOperationsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BaseVO<?> save(ApiSaveDTO apiSaveDTO) {
        Object[] args = new Object[3];
        Integer apiID = apiSaveDTO.getApiID();
        String sql = "update api_info set code = ? where id = ?";

        jdbcTemplate.update(sql, new ArgumentPreparedStatementSetter(args));
        return null;
    }

    @Override
    public void saveClassByteCode(byte[] byteCode, Integer id) {

    }

    @Override
    public List<ApiMappingInfo> findAllWithRun() {
        return null;
    }
}

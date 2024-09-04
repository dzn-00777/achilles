package org.earthdog.achilles.entity.dto;

import lombok.Data;

/**
 * @Date 2024/7/10 11:43
 * @Author DZN
 * @Desc ApiSaveDTO
 */

@Data
public class ApiSaveDTO {

    private Integer apiID; // 数据库储存id
    private String path; // 请求路径
    private String method; // 请求方法
    private String code; // 源码
    private String qualifiedName; // 动态加载类的全类名 example: org.earthdog.achilles.Test

}

package org.earthdog.achilles.tools.loader;

import lombok.Builder;
import lombok.Data;

/**
 * @Date 2024/7/11 14:06
 * @Author DZN
 * @Desc ApiMappingInfo
 */
@Builder
@Data
public class ApiMappingInfo {

    private Integer id;
    private String path;
    private String method;
    private String qualifiedName;
    private String code;
    private boolean isTimerTask;
    private String corn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiMappingInfo that = (ApiMappingInfo) o;

        if (!path.equals(that.path)) return false;
        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + method.hashCode();
        return result;
    }
}

package org.earthdog.achilles.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Date 2024/7/14 14:30
 * @Author DZN
 * @Desc AchillesYmlConfiguration
 */

@Data
@ConfigurationProperties(prefix = "achilles")
public class AchillesYmlConfiguration {

    private String relativeClassFilePath;

}

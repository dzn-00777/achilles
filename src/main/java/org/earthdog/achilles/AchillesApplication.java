package org.earthdog.achilles;

import org.earthdog.achilles.config.AchillesYmlConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("org.earthdog.achilles")
@SpringBootApplication
@EnableConfigurationProperties({AchillesYmlConfiguration.class})
@EnableTransactionManagement
public class AchillesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchillesApplication.class, args);
    }

}

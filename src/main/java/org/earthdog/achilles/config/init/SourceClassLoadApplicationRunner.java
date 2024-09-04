package org.earthdog.achilles.config.init;

import org.earthdog.achilles.service.EditOperationsService;
import org.earthdog.achilles.tools.loader.SourceLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/7/14 15:49
 * @Author DZN
 * @Desc SourceClassLoadApplicationStartingEvent
 */
@Component
public class SourceClassLoadApplicationRunner implements ApplicationRunner {

    private final SourceLoader loader;

    public SourceClassLoadApplicationRunner(SourceLoader loader, EditOperationsService editOperationsService) {
        this.loader = loader;
    }

    @Override
    public void run(ApplicationArguments args) {
//        loader.loadAll();
    }
}

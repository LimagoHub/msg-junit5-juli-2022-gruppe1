package de.msg.webapp.services.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BlacklistConfig {

    @Bean
    public List<String> antipathen() {
        return List.of("Attila","Peter","Paul","Mary");
    }
}

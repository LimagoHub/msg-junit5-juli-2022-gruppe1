package de.msg.webapp.services.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BlacklistConfig {

    @Bean
    @Qualifier("blackList")
    public List<String> blackList() {
        return List.of("Attila","Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> fruits() {
        return List.of("Banana","Cherry","Strawberry","Apple");
    }
}

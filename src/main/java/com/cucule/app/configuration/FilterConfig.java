package com.cucule.app.configuration;

import com.cucule.app.filter.ClientFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    ClientFilter logFilter() {
        return new ClientFilter();
    }
}

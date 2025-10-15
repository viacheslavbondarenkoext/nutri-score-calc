package com.nutriscorecalc;

import com.nutriscorecalc.controller.ProductController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {

    @Bean
    public ResourceConfig resourceConfig(ProductController myController) {
        ResourceConfig config = new ResourceConfig();
        config.register(myController);
        return config;
    }
}

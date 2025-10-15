package com.nutriscorecalc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Logs custom classes loaded by Spring during application startup.
 */
@Component
public class CustomBeanLogger implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CustomBeanLogger.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        String className = bean.getClass().getName();
        // Log only custom classes from the application's packages
        if (className.startsWith("com.nutriscorecalc.controller") ||
            className.startsWith("com.nutriscorecalc.logic") ||
            className.startsWith("com.nutriscorecalc.database") || className.contains("ProductController")) {
            logger.info("Custom class loaded: {} (bean name: {})", className, beanName);
        }
        return bean;
    }
}


package com.example.calculator.configuration;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfiguration {
    @Bean
    public TracerImpl getTracerImpl() {
        return new TracerImpl();
    }
}

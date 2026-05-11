package com.config;

import com.utils.DateJsonDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonDateCustomizer() {
        return builder -> {
            builder.timeZone(TimeZone.getTimeZone("GMT+8"));
            builder.deserializerByType(Date.class, new DateJsonDeserializer());
        };
    }
}

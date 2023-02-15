package com.edstem.demo.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper setModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, LocalDate> toStringDate =
                new AbstractConverter<>() {
                    @Override
                    protected LocalDate convert(String source) {
                        return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                };
        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(toStringDate);
        return mapper;
    }
}


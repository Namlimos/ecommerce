package com.ecommerce.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig{

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Optionally configure the mapper
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT); // Set strategy to strict matching

        return modelMapper;
    }

}



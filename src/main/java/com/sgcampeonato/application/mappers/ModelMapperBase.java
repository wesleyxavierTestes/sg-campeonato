package com.sgcampeonato.application.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

public final class ModelMapperBase {
    
    public static ModelMapper get() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }
}
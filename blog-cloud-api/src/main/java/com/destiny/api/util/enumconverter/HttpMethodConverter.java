package com.destiny.api.util.enumconverter;

import com.destiny.api.domain.enums.HttpMethodEnum;

import javax.persistence.AttributeConverter;

public class HttpMethodConverter implements AttributeConverter<HttpMethodEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(HttpMethodEnum httpMethodEnum) {
        return httpMethodEnum.getValue();
    }

    @Override
    public HttpMethodEnum convertToEntityAttribute(Integer value) {
        return EnumUtil.valueOf(HttpMethodEnum.class,value);
    }
}

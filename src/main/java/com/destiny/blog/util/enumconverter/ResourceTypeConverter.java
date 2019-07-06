package com.destiny.blog.util.enumconverter;

import com.destiny.blog.domain.enums.ResourceType;

import javax.persistence.AttributeConverter;

public class ResourceTypeConverter implements AttributeConverter<ResourceType,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ResourceType resourceType) {
        return resourceType.getValue();
    }

    @Override
    public ResourceType convertToEntityAttribute(Integer value) {
        return EnumUtil.valueOf(ResourceType.class,value);
    }
}

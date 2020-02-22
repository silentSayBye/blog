package com.destiny.api.util.enumconverter;

import com.destiny.api.util.BaseEnum;

public class EnumUtil {

    public static <E extends Enum<?> & BaseEnum> E valueOf(Class<E> enumClass,Integer value){
        if (value != null){
            E[] enumConstants = enumClass.getEnumConstants();
            for(E e:enumConstants){
                if(value.equals(e.getValue())){
                    return e;
                }
            }
        }
        return null;
    }
}

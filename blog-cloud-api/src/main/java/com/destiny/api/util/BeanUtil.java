package com.destiny.api.util;

import com.destiny.api.exception.CustomException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @Date 2020/4/2 23:37
 * @Version 1.0
 **/
public class BeanUtil {

    public static void copyBean(Object des, Object src){
        try {
            BeanUtils.copyProperties(des,src);
        } catch (Exception e) {
            throw new CustomException("Bean duplication error:" + e.getMessage());
        }
    }
}

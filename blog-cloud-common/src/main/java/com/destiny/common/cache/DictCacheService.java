package com.destiny.common.cache;

import com.destiny.common.cache.constant.CacheConstant;
import com.destiny.common.domain.pojo.Dict;
import com.destiny.common.service.DictService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DictCacheService {

    @Autowired
    private DictService dictService;

    @Cacheable(value = CacheConstant.DICT_KEY, key = "#categoryCode + ':' + #code")
    public Dict findDictByCategoryCodeAndCode(String categoryCode, String code){
        Preconditions.checkNotNull(categoryCode, "categoryCode can not be null");
        Preconditions.checkNotNull(code, "code can not be null");
        log.info("search dict by db,categoryCode is {},code is {}", categoryCode, code);
        return dictService.getDictByCategoryCodeAndCode(categoryCode,code);
    }

    @Cacheable(value = CacheConstant.DICT_LIST_KEY, key = "#categoryCode")
    public List<Dict> findDictListByCategory(String categoryCode){
        Preconditions.checkNotNull(categoryCode, "categoryCode can not be null");
        log.info("search dictList by db,categoryCode is {}", categoryCode);
        return dictService.getDictListByCategoryCode(categoryCode);
    }

    @CacheEvict(value = CacheConstant.DICT_KEY, key = "#categoryCode + ':' + #code")
    public void removeDictByCategoryCodeAndCode(String categoryCode, String code){
        log.info("remove value:{}, key: {}:{} cache.", CacheConstant.DICT_KEY,categoryCode, code);
    }

    @CacheEvict(value = CacheConstant.DICT_LIST_KEY, key = "#categoryCode")
    public void removeDictListByCategoryCode(String categoryCode){
        log.info("remove value:{}, key: {}:{} cache.", CacheConstant.DICT_LIST_KEY,categoryCode);
    }
}

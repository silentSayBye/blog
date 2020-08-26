package com.destiny.common.service.impl;

import com.destiny.common.dao.DictRepository;
import com.destiny.common.domain.pojo.Dict;
import com.destiny.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Override
    public Dict getDictByCategoryCodeAndCode(String categoryCode, String code) {
        return dictRepository.findDictByCategoryCodeAndCode(categoryCode, code);
    }

    @Override
    public List<Dict> getDictListByCatgoryCode(String categoryCode) {
        return dictRepository.findDictByCategoryCode(categoryCode);
    }
}

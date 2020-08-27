package com.destiny.common.service.impl;

import com.destiny.common.dao.DictRepository;
import com.destiny.common.domain.pojo.Dict;
import com.destiny.common.exception.DataNoFoundException;
import com.destiny.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Override
    public Dict getDictByCategoryCodeAndCode(String categoryCode, String code) {
        return dictRepository.findDictByCategoryCodeAndCode(categoryCode, code);
    }

    @Override
    public List<Dict> getDictListByCategoryCode(String categoryCode) {
        return dictRepository.findDictByCategoryCode(categoryCode);
    }

    @Override
    public void insertDict(Dict dict) {
        dictRepository.save(dict);
    }

    @Transactional
    @Override
    public void updateDict(Long id) {
        Optional<Dict> dictOptional = dictRepository.findById(id);
        if (dictOptional.isPresent()) {
            Dict dict = dictOptional.get();
            dict.setStatus(0);
            dictRepository.save(dict);
        } else {
            throw new DataNoFoundException("", String.format("根据id:&s未查询到数据", id));
        }

    }
}

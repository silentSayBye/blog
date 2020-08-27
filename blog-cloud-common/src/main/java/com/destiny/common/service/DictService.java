package com.destiny.common.service;

import com.destiny.common.domain.pojo.Dict;

import java.util.List;

public interface DictService {

    Dict getDictByCategoryCodeAndCode(String categoryCode, String code);

    List<Dict> getDictListByCategoryCode(String categoryCode);

    void insertDict(Dict dict);

    void updateDict(Long id);
}

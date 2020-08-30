package com.destiny.api.service;

import com.destiny.api.domain.vo.CustomCategoryVO;

import java.util.List;

public interface CustomCategoryService {

    List<CustomCategoryVO> findAllCustomCategoryByUseIdAndStatus(Integer userId, Integer status);

    void saveCustomCategory(CustomCategoryVO customCategoryVO);

    void saveAllCustomCategory(List<CustomCategoryVO> customCategoryVOList);
}

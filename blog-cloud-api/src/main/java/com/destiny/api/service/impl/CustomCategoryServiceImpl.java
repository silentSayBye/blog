package com.destiny.api.service.impl;

import com.destiny.api.dao.CustomCategoryRepository;
import com.destiny.api.dao.UserRepository;
import com.destiny.api.domain.pojo.CustomCategory;
import com.destiny.api.domain.pojo.User;
import com.destiny.api.domain.vo.CustomCategoryVO;
import com.destiny.api.exception.UserNotExistException;
import com.destiny.api.service.CustomCategoryService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/30 1:57
 * @Version 1.0
 **/
@Service
public class CustomCategoryServiceImpl implements CustomCategoryService {

    @Autowired
    private CustomCategoryRepository customCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CustomCategoryVO> findAllCustomCategoryByUseIdAndStatus(Integer userId, Integer status) {
        Preconditions.checkNotNull(userId, "userId is null");
        Preconditions.checkNotNull(status, "status is null");
        List<CustomCategory> customCategoryList = customCategoryRepository.findByUseIdAndStatus(status, userId);
        List<CustomCategoryVO> customCategoryVOList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(customCategoryList)){
            customCategoryList.forEach(customCategory ->{
                CustomCategoryVO  category = new CustomCategoryVO();
                BeanUtils.copyProperties(customCategory, category);
                customCategoryVOList.add(category);
            });
        }
        return customCategoryVOList;
    }

    @Override
    public void saveCustomCategory(CustomCategoryVO articleCategoryVO) {
        CustomCategory customCategory = new CustomCategory();
        Integer userId = articleCategoryVO.getUserId();
        User user = userRepository.findUserById(userId, 1);
        if (user == null){
            throw new UserNotExistException(100001, "用户不存在");
        }
        BeanUtils.copyProperties(articleCategoryVO,customCategory);
        customCategoryRepository.save(customCategory);
    }

    @Override
    public void saveAllCustomCategory(List<CustomCategoryVO> customCategoryVOList) {
        if (CollectionUtils.isNotEmpty(customCategoryVOList)){
            ArrayList<CustomCategory> customCategoryList = Lists.newArrayList();
            for (CustomCategoryVO customCategoryVO : customCategoryVOList) {
                CustomCategory  category = new CustomCategory();
                BeanUtils.copyProperties(customCategoryVO, category);
                customCategoryList.add(category);
            }
            customCategoryRepository.saveAll(customCategoryList);
        }
    }
}

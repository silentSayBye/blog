package com.destiny.api.controller;

import com.destiny.api.domain.vo.CustomCategoryVO;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.service.CustomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Date 2020/8/30 14:44
 * @Version 1.0
 **/
@RequestMapping("/article/customCategory")
@RestController
public class CustomCategoryController {

    @Autowired
    private CustomCategoryService customCategoryService;

    @GetMapping("/{userId}")
    public Response getCustomCategoryByUserId(@PathVariable @NotNull @Valid Integer userId){
        List<CustomCategoryVO> categoryList = customCategoryService.findAllCustomCategoryByUseIdAndStatus(userId, 1);
        return Response.success(categoryList);
    }

    @PostMapping("/save")
    public Response saveCustomCategory(@Valid CustomCategoryVO customCategoryVO){
        customCategoryService.saveCustomCategory(customCategoryVO);
        return Response.success(null);
    }
}

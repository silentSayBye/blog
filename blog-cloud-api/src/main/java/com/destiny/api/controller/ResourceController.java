package com.destiny.api.controller;

import com.destiny.api.domain.pojo.Resource;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date 2020/2/23 16:07
 * @Version 1.0
 **/

@Slf4j
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("获取资源信息")
    @GetMapping("/resource")
    public Response findAllResources(){
        List<Resource> allResources = resourceService.findAllResources();
        return Response.success(allResources);
    }
}

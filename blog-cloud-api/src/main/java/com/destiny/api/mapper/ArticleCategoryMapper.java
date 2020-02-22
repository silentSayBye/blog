package com.destiny.api.mapper;

import com.destiny.api.domain.pojo.ArticleCategory;
import com.destiny.api.domain.vo.ArticleCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Date 2020/2/21 17:16
 * @Version 1.0
 **/
@Mapper
public interface ArticleCategoryMapper {

    public ArticleCategoryMapper getInstance = Mappers.getMapper(ArticleCategoryMapper.class);

    List<ArticleCategoryVO> entityToVOList(List<ArticleCategory> articleCategoryList);
}

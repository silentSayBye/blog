package com.destiny.common.dao;

import com.destiny.common.dao.custom.DictCategoryCustom;
import com.destiny.common.domain.pojo.DictCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictCategoryRepository extends JpaRepository<DictCategory,Long>,DictCategoryCustom {

}

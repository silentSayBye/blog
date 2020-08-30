package com.destiny.api.dao;

import com.destiny.api.domain.pojo.CustomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCategoryRepository extends JpaRepository<CustomCategory,Long> {

    @Query("select a from CustomCategory a  where a.status = :status and a.userId = :userId")
    List<CustomCategory> findByUseIdAndStatus(@Param("status") Integer status, @Param("userId") Integer userId);

}

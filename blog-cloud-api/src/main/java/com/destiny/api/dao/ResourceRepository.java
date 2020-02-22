package com.destiny.api.dao;

import com.destiny.api.dao.custom.ResourceCustom;
import com.destiny.api.domain.pojo.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ResourceRepository
 * @Author Administrator
 * @Date 2019/6/1723:39
 * @Version 1.0
 **/

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer>, ResourceCustom {

    Page<Resource> findAll(Pageable pageable);

    List<Resource> findAllByState(@Param("state") Integer flag);

    Resource findByCodeAndState(@Param("code") String code, @Param("state") String flag);

    Resource findAllByParentIdAndState(@Param("parentId") Integer parentId, @Param("state") Integer flag);

    @Modifying
    @Transactional
    @Query("update Resource r set r.state = 1 where r.id = :id")
    boolean deleteResource(@Param("id") Integer id);


}

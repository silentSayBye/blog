package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.RoleResourceCustom;
import com.destiny.blog.domain.pojo.RoleResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResource,Integer>, RoleResourceCustom {

    Page<RoleResource> findAll(Pageable pageable);

    List<RoleResource> findAllByDeleteFlag(@Param("flag")Integer flag);

    @Transactional
    @Modifying
    @Query("update RoleResource r set r.deleteFlag = 0 where r.id = :id")
    boolean delRoleResource(@Param("id")Integer id);
}

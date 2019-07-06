package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.RoleCustom;
import com.destiny.blog.domain.pojo.Role;
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
 * @ClassName RoleRepository
 * @Author Administrator
 * @Date 2019/6/1721:30
 * @Version 1.0
 **/

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>, RoleCustom {

    List<Role> findAllByDeleteFlag(@Param("deleteFlag") Integer flag);

    Role findByCodeAndDeleteFlag(@Param("code") String code,@Param("deleteFlag") String flag);

    Page<Role> findAll(Pageable pageable);

    /**
     * @Author Administrator
     * @Description 逻辑删除 role
     * @Date 23:08 2019/6/17
     * @Param [id]
     * @Return java.lang.Boolean
     **/
    @Modifying
    @Transactional
    @Query("update Role r set r.deleteFlag = 0 where r.id = :id")
    Boolean delRole(@Param("id")Integer id);

}

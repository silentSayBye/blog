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

import java.net.InterfaceAddress;
import java.util.List;

@Repository
public interface   RoleRepository extends JpaRepository<Role,Integer>, RoleCustom {

    List<Role> findAllByState(@Param("state") Integer flag);

    Page<Role> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Role r set r.state = 0 where r.id = :id")
    Boolean delRole(@Param("id")Integer id);

    Role findByCodeAndState(@Param("code")String code, @Param("state")Integer state);

}

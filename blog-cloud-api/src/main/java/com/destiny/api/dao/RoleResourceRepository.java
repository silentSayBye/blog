package com.destiny.api.dao;/*
package com.destiny.api.dao;

import com.destiny.api.dao.custom.RoleResourceCustom;
import com.destiny.api.domain.pojo.RoleResource;
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

    List<RoleResource> findAllByState(@Param("state")Integer state);

    @Transactional
    @Modifying
    @Query("update RoleResource r set r.state = 0 where r.id = :id")
    boolean delRoleResource(@Param("id")Integer id);
}
*/

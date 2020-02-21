package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.UserRoleCostom;
import com.destiny.blog.domain.pojo.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> , UserRoleCostom {

        List<UserRole> findAllByState(@Param("state") Integer flag);

        @Modifying
        @Transactional
        @Query("update UserRole u set u.state = 0 where u.id = :id")
        Boolean delUserRole(@Param("id") Integer id);
}

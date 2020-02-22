package com.destiny.api.dao;

import com.destiny.api.dao.custom.UserCustom;
import com.destiny.api.domain.pojo.User;
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
public interface UserRepository extends JpaRepository<User,Integer>, UserCustom {

    @Query("select u from User u where u.id = :id and u.state = :flag")
    User  findUserById(@Param("id") Integer id, @Param("flag") Integer flag);


    List<User> findAllByState(@Param("flag") Integer flag);


    Page<User> findAll(Pageable pageable);


    @Modifying
    @Transactional
    @Query("update User u set u.state = 0 where u.id = :id")
    Boolean deleteUser(@Param("id") Integer id);

    User findByEmailAndState(@Param("email") String email, @Param("state") Integer flag);

    User findByUsernameAndState(@Param("username") String username, @Param("state") Integer flag);

    User findByUsername(@Param("username") String username);

}

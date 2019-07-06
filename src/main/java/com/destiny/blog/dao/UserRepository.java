package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.UserCustom;
import com.destiny.blog.domain.pojo.User;
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
   /**
    * @Author Administrator
    * @Description
    * @Date 20:54 2019/6/15
    * @Param [id]
    * @Return com.destiny.blog.domain.pojo.User
    **/
    @Query("select u from User u where u.id = :id and u.state = :flag")
    User  findUserById(@Param("id")Integer id, @Param("flag")Integer flag);

    /**
     * @Author Administrator
     * @Description
     * @Date 14:21 2019/6/16
     * @Param []
     * @Return java.util.List<com.destiny.blog.domain.pojo.User>
     **/
//    @Query("select u from User u where u.state = :flag")
    List<User> findAllByState(@Param("flag") Integer flag);

    /**
     * @Author Administrator
     * @Description 查询所有User
     * @Date 23:04 2019/6/17
     * @Param [pageable]
     * @Return org.springframework.data.domain.Page<com.destiny.blog.domain.pojo.User>
     **/
    Page<User> findAll(Pageable pageable);

    /**
     * @Author Administrator
     * @Description
     * @Date 14:21 2019/6/16
     * @Param []
     * @Return java.lang.Boolean
     **/
    @Modifying
    @Transactional
    @Query("update User u set u.state = 0 where u.id = :id")
    Boolean deleteUser(@Param("id")Integer id);

    User findByEmailAndState(@Param("email") String email,@Param("state") Integer flag);

}

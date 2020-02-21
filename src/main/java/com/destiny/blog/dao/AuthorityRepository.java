package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.AuthorityCustom;
import com.destiny.blog.domain.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer>, AuthorityCustom {

}

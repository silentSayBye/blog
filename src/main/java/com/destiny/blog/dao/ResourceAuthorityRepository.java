package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.ResourceAuthorityCustom;
import com.destiny.blog.domain.pojo.ResourceAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAuthorityRepository extends JpaRepository<ResourceAuthority,Integer>, ResourceAuthorityCustom {

}

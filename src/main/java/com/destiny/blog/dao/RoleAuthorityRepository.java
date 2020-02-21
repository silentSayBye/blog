package com.destiny.blog.dao;

import com.destiny.blog.dao.custom.RoleAuthorityCustom;
import com.destiny.blog.domain.pojo.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority,Integer>, RoleAuthorityCustom {

}

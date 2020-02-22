package com.destiny.api.dao;

import com.destiny.api.dao.custom.RoleAuthorityCustom;
import com.destiny.api.domain.pojo.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority,Integer>, RoleAuthorityCustom {

}

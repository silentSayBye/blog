package com.destiny.api.dao;

import com.destiny.api.dao.custom.ResourceAuthorityCustom;
import com.destiny.api.domain.pojo.ResourceAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAuthorityRepository extends JpaRepository<ResourceAuthority,Integer>, ResourceAuthorityCustom {

}

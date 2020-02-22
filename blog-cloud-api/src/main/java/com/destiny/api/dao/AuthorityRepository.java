package com.destiny.api.dao;

import com.destiny.api.dao.custom.AuthorityCustom;
import com.destiny.api.domain.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer>, AuthorityCustom {

}

package com.destiny.common.dao;

import com.destiny.common.dao.custom.DictRepositoryCustom;
import com.destiny.common.domain.pojo.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictRepository extends JpaRepository<Dict,Long>, DictRepositoryCustom {

    @Query("select Dict from Dict where categoryCode = :categoryCode and code = :code")
    Dict findDictByCategoryCodeAndCode(@Param("categoryCode")String categoryCode,@Param("code")String code);

    @Query("select Dict from Dict where categoryCode = :categoryCode")
    List<Dict> findDictByCategoryCode(@Param("categoryCode")String categoryCode);

}

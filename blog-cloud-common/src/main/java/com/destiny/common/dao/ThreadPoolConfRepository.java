package com.destiny.common.dao;

import com.destiny.common.domain.pojo.ThreadPoolConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadPoolConfRepository extends JpaRepository<ThreadPoolConf, Long> {

    @Query("select t from ThreadPoolConf  t where t.taskCode = :taskCode")
    List<ThreadPoolConf> findAllByTaskCode(@Param("taskCode") String taskCode);
}

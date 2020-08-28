package com.destiny.common.dao;

import com.destiny.common.domain.pojo.TaskScheduleConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskScheduleConfRepository extends JpaRepository<TaskScheduleConf, Long> {

    @Query("select t from TaskScheduleConf  t where t.taskCode = :taskCode")
    List<TaskScheduleConf> findAllByTaskCode(@Param("taskCode") String taskCode);

    @Query("select t from TaskScheduleConf  t where t.serviceName = :serviceName")
    List<TaskScheduleConf> findAllByServiceName(@Param("serviceName") String serviceName);
}

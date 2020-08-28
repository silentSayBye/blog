package com.destiny.common.dao;

import com.destiny.common.domain.pojo.TaskInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskInterfaceRepository extends JpaRepository<TaskInterface, Long> {

    @Query("select t from TaskInterface t where t.taskId = :taskId")
    TaskInterface findByTaskId(@Param("taskId") Integer taskId);

    @Query("select t from TaskInterface t where t.id = :id")
    Optional<TaskInterface> findById(@Param("id") Long id);

    @Query("select t from TaskInterface t where t.status = :status")
    List<TaskInterface> findAllByStatus(@Param("status") Integer status);
}

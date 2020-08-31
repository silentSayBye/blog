package com.destiny.common.dao.custom;

import com.destiny.common.domain.pojo.TaskInterface;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskInterfaceRepositoryCustom {

    List<TaskInterface> findBySubTaskType(Integer limitNum, String subTaskCode);

}

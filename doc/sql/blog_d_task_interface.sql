DROP TABLE IF EXISTS `d_task_interface`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `d_task_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL COMMENT '任务主键',
  `task_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `task_type` varchar(45) DEFAULT NULL COMMENT '任务大类',
  `sub_task_type` varchar(45) DEFAULT NULL COMMENT '任务小类',
  `recent_execute_time` timestamp(3) NULL DEFAULT NULL COMMENT '最近执行时间',
  `next_execute_time` timestamp(3) NULL DEFAULT NULL COMMENT '下次执行时间',
  `failed_num` int(3) DEFAULT NULL COMMENT '失败次数',
  `max_num` int(3) DEFAULT NULL COMMENT '最多执行次数',
  `execution_status` char(1) DEFAULT NULL COMMENT '1-未执行，2-执行中，3-执行成功',
  `description` varchar(255) default NULL COMMENT '错误描述',
  `send_email_flag` tinyint(1) default NULL COMMENT '是否发送邮件',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
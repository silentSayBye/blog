DROP TABLE IF EXISTS `d_task_schedule_conf`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `d_task_schedule_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(45) DEFAULT NULL COMMENT '定时任务代码',
  `task_name` varchar(255) DEFAULT NULL COMMENT '定时任务名称',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'bean名称',
  `service_name` varchar(255) DEFAULT NULL COMMENT '微服务名称',
  `if_lock` tinyint(2) DEFAULT false COMMENT '是否加锁',
  `lock_time` int(11) DEFAULT NULL COMMENT '锁持有时间',
  `cron` varchar(45) DEFAULT NULL COMMENT 'cron表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
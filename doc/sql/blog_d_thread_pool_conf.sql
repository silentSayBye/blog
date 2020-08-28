DROP TABLE IF EXISTS `d_thread_pool_conf`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `d_thread_pool_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(45) DEFAULT NULL COMMENT '定时任务代码',
  `pool_code` varchar(45) DEFAULT NULL COMMENT '线程池代码',
  `pool_name` varchar(255) DEFAULT NULL COMMENT '线程池名称',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'bean名称',
  `wait_time` int(11) DEFAULT NULL COMMENT '线程池线程维护空闲时间',
  `pool_size` int(11) DEFAULT NULL COMMENT '线程池线程数',
  `task_limit_size` int(11) DEFAULT NULL COMMENT '任务数',
  `pool_queue_size` int(11) DEFAULT NULL COMMENT '线程池队列大小',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
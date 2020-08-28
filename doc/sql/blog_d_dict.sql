DROP TABLE IF EXISTS `d_dict`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `d_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '大类代码',
  `code` varchar(45) NOT NULL COMMENT '小类代码',
  `name` varchar(255) DEFAULT NULL COMMENT '小类名称',
  `extend_property1` varchar(255) DEFAULT NULL COMMENT '扩展字段1',
  `extend_property2` varchar(255) DEFAULT NULL COMMENT '扩展字段2',
  `extend_property3` varchar(255) DEFAULT NULL COMMENT '扩展字段3',
  `extend_property4` varchar(255) DEFAULT NULL COMMENT '扩展字段4',
  `extend_property5` varchar(255) DEFAULT NULL COMMENT '扩展字段5',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
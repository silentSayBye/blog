DROP TABLE IF EXISTS `d_dict_category`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `d_dict_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '大类代码',
  `category_name` varchar(255) DEFAULT NULL COMMENT '大类名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
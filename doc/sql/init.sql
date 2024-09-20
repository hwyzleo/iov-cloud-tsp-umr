DROP TABLE IF EXISTS `db_umr`.`tb_msg_template`;
CREATE TABLE `db_umr`.`tb_msg_template`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(255) NOT NULL COMMENT '模板名称',
    `code`        VARCHAR(50)  NOT NULL COMMENT '模板代码',
    `type`        VARCHAR(50)  NOT NULL COMMENT '模板类型',
    `msg_title`   VARCHAR(255) NOT NULL COMMENT '消息标题',
    `msg_content` VARCHAR(4000)         DEFAULT NULL COMMENT '消息内容',
    `msg_config`  JSON                  DEFAULT NULL COMMENT '消息设置',
    `description` VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version` INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='消息模板表';
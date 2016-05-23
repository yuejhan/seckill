-- 数据库脚本的初始化
--


-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
USE seckill;
-- 创建秒杀库存表
CREATE TABLE seckill(seckill_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  name varchar(120) NOT NULL COMMENT '商品名称',
  number INT(11) NOT NULL COMMENT '库存数量',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀库存表';

INSERT INTO seckill(name,number,start_time,end_time) VALUES ('1000秒杀iphone6',100,'2016-05-21 00:00:00','2016-06-21 00:00:00');
INSERT INTO seckill(name,number,start_time,end_time) VALUES ('1000元秒杀小米',200,'2016-05-21 00:00:00','2016-06-21 00:00:00');
INSERT INTO seckill(name,number,start_time,end_time) VALUES ('1000元秒杀魅族',150,'2016-05-21 00:00:00','2016-06-21 00:00:00');
INSERT INTO seckill(name,number,start_time,end_time) VALUES ('1000元秒杀华为',1000,'2016-05-21 00:00:00','2016-06-21 00:00:00');

-- 秒杀成功明细表
CREATE TABLE success_killed (
  seckill_id  BIGINT    NOT NULL
  COMMENT '秒删商品id',
  user_phone  BIGINT    NOT NULL
  COMMENT '用户手机号',
  state       TINYINT   NOT NULL DEFAULT -1
  COMMENT '状态标识位 -1：无效；0：成功；1：已付款',
  create_time TIMESTAMP NOT NULL
  COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '秒杀库存表';

SHOW CREATE TABLE seckill\G;


CREATE DATABASE seckill;

use seckill;

DROP TABLE IF EXISTS seckill;
DROP TABLE IF EXISTS success_killed;

-- 创建数据库
CREATE TABLE seckill (
  seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number int NOT NULL COMMENT '库存数量',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY(seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT='秒杀库存表';

-- 定义一个trigger
CREATE TRIGGER insert_seckill BEFORE INSERT ON seckill
  FOR EACH ROW SET NEW.create_time = now();

-- 初始化数据库
INSERT INTO seckill(name, number, start_time, end_time)
    VALUES
      ('1000元秒杀iPhone 7', 100, '2016-10-20 00:00:00', '2016-10-21 00:00:00'),
      ('500元秒杀Note 7', 100, '2016-10-20 00:00:00', '2016-10-21 00:00:00'),
      ('10元秒杀充电器', 10, '2016-10-20 00:00:00', '2016-10-21 00:00:00'),
      ('100元秒杀iPad Air', 50, '2016-10-20 00:00:00', '2016-10-21 00:00:00'),
      ('1元秒杀Mate7', 20, '2016-10-20 00:00:00', '2016-10-21 00:00:00');

-- 秒杀成功明细表
-- 用戶登录认证
CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL COMMENT '秒殺商品ID',
  user_phone BIGINT NOT NULL COMMENT '用户手机号',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '状态',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT  CHARSET=utf8 COMMENT='秒杀結果明細';

CREATE TRIGGER insert_success_killed BEFORE INSERT ON success_killed
FOR EACH ROW SET NEW.create_time = now();
-- mysql


--秒删执行的存储过程
DELIMITER $$ --console 转化成为 $$
--定义存储过程
CREATE PROCEDURE seckill.execute_seckill
(IN v_seckill_id bigint ,IN v_phone bigint,IN v_kill_time TIMESTAMP ,out r_result int)
BEGIN
    DECLARE insert_count int DEFAULT 0;
    start TRANSACTION ;
    insert ignore into success_killed(seckill_id,user_phone,create_time) VALUES (v_seckill_id,v_phone,v_kill_time);
    SELECT ROW_COUNT() into insert_count;
    IF(insert_count = 0) THEN
        ROLLBACK ;
        SET r_result = -1;
    ELSEIF(insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
    ELSE
        UPDATE seckill SET number = number - 1 WHERE seckill_id = v_seckill_id AND start_time < v_kill_time AND end_time > v_kill_time AND number > 0;
        SELECT ROW_COUNT() into insert_count;
        IF(insert_count = 0) THEN
          ROLLBACK ;
          SET r_result = 0;
        ELSEIF(insert_count < 0) THEN
          ROLLBACK ;
           SET r_result = -2;
        ELSE
            COMMIT ;
            SET r_result = 1;
        END IF;
    END IF;
    END ;
$$--存储过程定义结束

DELIMITER ;

set @r_result = -3;
-- 执行存储过程
call seckill.execute_seckill(1009,18263346977,now(),@r_result);
SELECT @r_result;


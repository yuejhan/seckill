package org.seckill.exception;

/**
 * 重复秒杀异常
 * Created by cody on 2016/5/21.
 */
public class RepeatKillException extends  SeckillException{
    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(String message) {
        super(message);
    }
}

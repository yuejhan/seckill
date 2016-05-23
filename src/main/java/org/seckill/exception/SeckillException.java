package org.seckill.exception;

/**
 * Created by cody on 2016/5/21.
 */
public class SeckillException extends RuntimeException {
    public SeckillException() {
    }

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}

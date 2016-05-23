package org.seckill.service;

import org.seckill.dto.Expore;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口 站在  使用者  的角度进行 设计
 * 三个方面：方法的定义粒度  参数 返回类型
 * Created by cody on 2016/5/21.
 */
public interface SeckillService {
    /**
     * 查询搜有的秒杀商品
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀商品
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     *  秒杀开始输出秒杀接口地址
     *  否则输出秒杀时间和系统时间
     * @param seckillId
     */
    Expore exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws RepeatKillException,SeckillCloseException,SeckillException;
}

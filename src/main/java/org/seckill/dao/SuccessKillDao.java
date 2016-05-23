package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKill;

/**
 * Created by cody on 2016/5/21.
 */
public interface SuccessKillDao {
    /**
     * 插入一条购买明细，可过滤重复 使用的是联合主键
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 查询购买明细并且查查询出产品对象
     *
     * @param seckillId
     * @return
     */
    SuccessKill queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}

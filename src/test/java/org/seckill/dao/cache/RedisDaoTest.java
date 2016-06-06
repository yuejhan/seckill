package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cody on 2016/5/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
   public void seckillUrltest(){
        Seckill seckill = redisDao.getSeckill(1008);
        if(seckill == null){
            seckill = seckillDao.queryById(1008);
            redisDao.putSeckill(seckill);
        }
    }

}
package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 * Created by cody on 2016/5/21.
 */

/**
 * 配置spring和junit的整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入dao的实现类
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
        long id = 1008;
        int i = seckillDao.reduceNumber(id, new Date());
        System.out.println(i);
    }

    @Test
    public void queryById() throws Exception {
       long id = 1008;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);

    }
//
//    @Test
//    public void queryAll() throws Exception {
//        List<Seckill> seckills = seckillDao.queryAll(0, 12);
//        for (Seckill k:seckills) {
//            System.out.println(k);
//        }
//    }
}
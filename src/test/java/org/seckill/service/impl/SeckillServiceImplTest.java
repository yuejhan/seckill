package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Expore;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cody on 2016/5/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private SeckillService seckillService;
//    @Test
//    public void getSeckillList() throws Exception {
////        List<Seckill> seckillList = seckillService.getSeckillList();
////        logger.info("list={}",seckillList);
//    }
//
//    @Test
//    public void getById() throws Exception {
////        Seckill seckill = seckillService.getById(1008);
////        logger.info("seckill={}",seckill);
//    }
//
//    @Test
//    public void exportSeckillUrl() throws Exception {
////        Expore expore = seckillService.exportSeckillUrl(1008);
////        logger.info("expore={}",expore);
//
//    }
//
//    @Test
//    public void executeSeckill() throws Exception {
////        SeckillExcution seckillExcution = seckillService.executeSeckill(1008, 18263346972l, "4263e665644a36bb66d2108fb1ffd96f");
////        logger.info("seckillExcution={}",seckillExcution);
//    }
//    @Test
//    public void testAll(){
////        Expore expore = seckillService.exportSeckillUrl(1008);
////        if(expore.isExposed()){
////            logger.info("expore={}",expore);
////            try {
////
////                SeckillExcution seckillExcution = seckillService.executeSeckill(expore.getSeckillId(), 18263346971l, expore.getMd5());
////                logger.info("seckillExcution={}",seckillExcution);
////            }catch (RepeatKillException e){
////                logger.error(e.getMessage());
////            }catch (SeckillCloseException e){
////                logger.error(e.getMessage());
////            }catch (Exception e){
////                logger.error(e.getMessage());
////            }
////        }else{
////            logger.info("expore={}",expore);
////        }
//    }
}
package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKillDao;
import org.seckill.dto.Expore;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by cody on 2016/5/21.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    // 使用的slf4j
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String salt = ">:ASJF)(EE)";
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKillDao successKillDao;

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Expore exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Expore(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date currentTime = new Date();
        if (startTime.getTime() > currentTime.getTime() || currentTime.getTime() > endTime.getTime()) {
            return new Expore(false, seckillId, currentTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Expore(true, seckillId, md5);
    }
    @Transactional
    //保证事务方法尽可能的短，不要穿插任何的网络操作或者剥离到事务的方法之外
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        Date date = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, date);
            if (updateCount <= 0) {
                // 秒杀失败
                throw new SeckillCloseException("seckill is closed");
            } else {
                // 秒杀成功  添加秒杀记录
                int insertCount = successKillDao.insertSuccessKill(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("repeat kill");
                } else {
                    SuccessKill successKill = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKill);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error");
        }
    }


    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}

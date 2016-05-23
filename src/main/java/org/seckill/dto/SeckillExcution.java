package org.seckill.dto;

import org.seckill.entity.SuccessKill;
import org.seckill.enums.SeckillStateEnum;

/**
 * Created by cody on 2016/5/21.
 */
public class SeckillExcution {
    //秒杀的id
    private long seckillId;
    // 秒杀的状态
    private int state;
    // 状态的信息
    private  String stateInfo;
    // 秒杀的成功对象
    private SuccessKill successKill;

    public SeckillExcution(long seckillId, SeckillStateEnum seckillEnum, SuccessKill successKill) {
        this.seckillId = seckillId;
        this.state = seckillEnum.getState();
        this.stateInfo = seckillEnum.getInfo();
        this.successKill = successKill;
    }

    public SeckillExcution(long seckillId,SeckillStateEnum seckillEnum) {
        this.seckillId = seckillId;
        this.state = seckillEnum.getState();
        this.stateInfo = seckillEnum.getInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKill getSuccessKill() {
        return successKill;
    }

    public void setSuccessKill(SuccessKill successKill) {
        this.successKill = successKill;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKill=" + successKill +
                '}';
    }
}

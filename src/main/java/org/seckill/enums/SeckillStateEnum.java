package org.seckill.enums;

/**
 * Created by cody on 2016/5/21.
 */
public enum  SeckillStateEnum {
    SUCCESS(1,"秒删成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据被修改");
    private int state;
    private String info;
    SeckillStateEnum(int state,String info){
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    public static SeckillStateEnum stateOf(int index){
        for (SeckillStateEnum state:values()){
            if(state.getState() == index){
                return state;
            }
        }
        return  null;
    }
}

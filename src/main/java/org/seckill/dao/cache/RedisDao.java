package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by cody on 2016/5/29.
 */
public class RedisDao {
    private JedisPool jedisPool;

    public RedisDao(String ip,int port) {
        this.jedisPool = new JedisPool(ip,port);
    }
    // 使用protostuff进行序列化
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId){
        Jedis jedis = jedisPool.getResource();
        try{
            // 定义主键名
            String key = "seckill:"+seckillId;
            // redis 并没有实现序列化  所以需要将对象进行序列化进行存储
            // 使用的是第三方的序列化工具
            byte[] bytes = jedis.get(key.getBytes());
            // 缓存获取
            if(bytes != null){
                // 如果不是空的话  进行反序列话
                // new一个新的对象
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                return seckill;
            }
            return null;
        }finally {
            jedis.close();
        }

    }

    public void putSeckill(Seckill seckill){
        Jedis jedis = jedisPool.getResource();
        try{
            String key = "seckillId:"+seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 60 * 60;//缓存时间
            // 返回存储状态信息
            String setex = jedis.setex(key.getBytes(), timeout, bytes);
        }finally {
            jedis.close();;
        }

    }
}

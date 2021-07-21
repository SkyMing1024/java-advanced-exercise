package com.sky.util;

import java.util.ArrayList;
import java.util.List;

public class RedisUtil {


    /**
     * setnx+expire实现
     * 存在原子性问题
     * @param key
     * @param requset
     * @param timeout
     * @return
     */
    public static boolean tryAcquireLock(String key,String requset,int timeout) {
        Long result = jedis.setnx(key, requset);
        // result = 1时，设置成功，否则设置失败
        if (result == 1L) {
            return jedis.expire(key, timeout) == 1L;
        } else {
            return false;
        }
    }

    /**
     * lua脚本具有原子性
     * @param key
     * @param id
     * @param seconds
     * @return
     */
    public boolean tryAcquireLockWithLua(String key, String id, int seconds) {
        String lua = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(id);
        values.add(String.valueOf(seconds));
        Object result = jedis.eval(lua, keys, values);
        return result.equals(1L);
    }
    
}

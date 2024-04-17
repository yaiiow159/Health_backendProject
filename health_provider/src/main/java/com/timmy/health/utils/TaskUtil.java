package com.timmy.health.utils;

import com.timmy.health.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;


@Component
@Slf4j
public class TaskUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static int counter = 0;

    //一小時清理一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void clearImg() {
        log.info(Thread.currentThread().getName() + " 調用清理 " + (++counter) + "次");
        SetOperations<String, String> set = redisTemplate.opsForSet();
        Set<String> sDiffSet = set.difference(
                RedisConstant.SETMEAL_PIC_RESOURCE
                , RedisConstant.SETMEAL_PIC_DB_RESOURCE
        );
        if (sDiffSet != null) {
            sDiffSet.forEach(s -> {
                try {
                    if (Objects.requireNonNull(set).remove(RedisConstant.SETMEAL_PIC_RESOURCE, s) >= 1){
                        GoogleFileUtil.deleteFile(s);
                        log.info("清理無用圖片完成");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

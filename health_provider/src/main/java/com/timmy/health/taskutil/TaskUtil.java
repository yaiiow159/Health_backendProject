package com.timmy.health.taskutil;

import com.timmy.health.constant.RedisConstant;
import com.timmy.health.utils.GoogleFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;


@Component
@Slf4j
public class TaskUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static int counter = 0;
    // to delete the unnessary img from redis and google cloud storage
    @Scheduled(cron = "0/50 * * * * ?")
    public void clearImg() {
        log.info(Thread.currentThread().getName() + " 調用清理 " + (++counter) + "次");
        Set<String> sDiffSet = redisTemplate.opsForSet().difference(
                RedisConstant.SETMEAL_PIC_RESOURCE
                , RedisConstant.SETMEAL_PIC_DB_RESOURCE
        );
        if (sDiffSet != null) {
            sDiffSet.forEach(s -> {
                try {
                    if (redisTemplate.opsForSet().remove(RedisConstant.SETMEAL_PIC_RESOURCE, s) >= 1){
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

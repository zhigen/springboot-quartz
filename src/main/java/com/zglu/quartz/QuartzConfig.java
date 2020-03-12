package com.zglu.quartz;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zglu
 */
@Log4j2
@Configuration
@EnableScheduling
public class QuartzConfig {

    @Scheduled(cron = "*/5 * * * * ?")
    public void test() {
        log.info("注解方式每5秒定时打印");
    }

}

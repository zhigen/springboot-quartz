package com.zglu.quartz;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author zglu
 */
@Log4j2
@Component
@EnableScheduling
public class QuartzJobBean1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("动态创建定时打印");
    }

    @Bean
    public JobDetail jobDetail1() {
        return JobBuilder.newJob(QuartzJobBean1.class).withIdentity("jobDetail1").storeDurably().build();
    }
}
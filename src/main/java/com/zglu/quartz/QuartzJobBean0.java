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
public class QuartzJobBean0 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("bean方式每5秒定时打印");
    }

    @Bean
    public JobDetail jobDetail0() {
        return JobBuilder.newJob(QuartzJobBean0.class).withIdentity("jobDetail0").storeDurably().build();
    }

    @Bean
    public Trigger trigger0(JobDetail jobDetail0) {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail0)
                .withSchedule(cronScheduleBuilder)
                .withIdentity("trigger0")
                .build();
    }
}
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
public class TestJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("bean方式每5秒定时打印");
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(TestJob.class).withIdentity("jobDetail").storeDurably().build();
    }

    @Bean
    public Trigger trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
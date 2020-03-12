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
public class TestJobNew extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("new一个bean方式每10秒定时打印");
    }

    @Bean
    public JobDetail jobDetailNew() {
        return JobBuilder.newJob(TestJobNew.class).withIdentity("jobDetailNew").storeDurably().build();
    }
}
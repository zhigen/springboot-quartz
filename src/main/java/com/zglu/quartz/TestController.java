package com.zglu.quartz;

import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final Scheduler scheduler;
    private final JobDetail jobDetailNew;

    @GetMapping("/set")
    public String set() throws SchedulerException {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder()
                .withSchedule(scheduleBuilder)
                .build();
        //重启触发器
        scheduler.rescheduleJob(triggerKey, trigger);
        return "将定时器设定为10秒";
    }

    @GetMapping("/add")
    public String add() throws SchedulerException {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        Trigger triggerNew = TriggerBuilder.newTrigger().forJob(jobDetailNew)
                .withIdentity("triggerNew")
                .withSchedule(scheduleBuilder)
                .build();
        scheduler.scheduleJob(triggerNew);
        return "启动新定时器";
    }

    @GetMapping("/del")
    public String del() throws SchedulerException {
        scheduler.deleteJob(new JobKey("jobDetailNew"));
        return "删除新定时器";
    }
}

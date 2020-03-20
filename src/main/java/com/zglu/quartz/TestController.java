package com.zglu.quartz;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final Scheduler scheduler;
    private final JobDetail jobDetail1;
    private static final String TRIGGER_KEY = "trigger1";

    @PostMapping
    @ApiOperation("添加定时器")
    public String add() throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail1)
                .withSchedule(cronScheduleBuilder)
                .withIdentity(TRIGGER_KEY)
                .build();
        scheduler.scheduleJob(trigger);
        return "动态创建定时打印5秒";
    }

    @DeleteMapping
    @ApiOperation("删除定时器")
    public String remove() throws SchedulerException {
        scheduler.deleteJob(new JobKey(TRIGGER_KEY));
        return "删除新定时器";
    }

    @PatchMapping
    @ApiOperation("设置定时器")
    public String set() throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_KEY);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        cronTrigger = cronTrigger.getTriggerBuilder()
                .withSchedule(cronScheduleBuilder)
                .build();
        // 重启触发器
        scheduler.rescheduleJob(triggerKey, cronTrigger);
        return "动态修改定时打印10秒";
    }

}

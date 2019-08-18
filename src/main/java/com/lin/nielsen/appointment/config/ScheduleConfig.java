package com.lin.nielsen.appointment.config;

import com.lin.nielsen.appointment.service.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Custom schedule config with different next execution time (within range defined) during runtime.
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ScheduleConfig.class);

    @Value("${schedule.config.range.in.sec}")
    private int rangeInSec;

    @Autowired
    private SchedulingService schedulingService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
        scheduledTaskRegistrar.addTriggerTask(
                () -> schedulingService.scheduleNewAppointment(),
                triggerContext -> {
                    Calendar nextExecutionTime = new GregorianCalendar();
                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                    int nextInt = new Random().nextInt(rangeInSec + 1);
                    log.info("Next job will be triggered in " + nextInt + " sec...");
                    nextExecutionTime.add(Calendar.SECOND, nextInt);
                    return nextExecutionTime.getTime();
                }
        );
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}

package com.yy.job;

import org.quartz.*;

public class MyJob implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        System.out.println(jobDetail.getJobDataMap().get("name"));
        System.out.println(trigger.getJobDataMap().get("hi"));
        System.out.println("hello world");
    }
}

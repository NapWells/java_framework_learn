package com.yy.test;

import com.yy.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSimpleDemo {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //通过任务调度器工厂创建任务调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //创建任务调度模型
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)//执行时间间隔是5秒
                .repeatForever();//一直执行，可以被调度器中断

        //定义触发器，定义触发条件
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","myGroup1")//设置触发器的id和group
                .withSchedule(scheduleBuilder)//设置使用的调度模型
                .usingJobData("hi","asasafafsfs")
                .startNow()//一加入scheduler就执行

                .build();

        //定义任务，做任务的是MyJob.execute()方法
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1","myGroup2")//设置任务的id和group
                .usingJobData("name","test my job")//任务中传递参数，底层就是个map
                .build();

        //将任务加入调度器
        scheduler.scheduleJob(jobDetail,trigger);
        //调度器调度任务
        scheduler.start();


        Thread.sleep(10000);
        //调度器关闭，true表示等待任务全部完成，false反之
        scheduler.shutdown(true);
    }
}

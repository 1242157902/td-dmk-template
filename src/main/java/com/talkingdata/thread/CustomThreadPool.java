package com.talkingdata.thread;

import java.util.concurrent.*;

/**
 * User：    ysl
 * Date:   2016/12/9
 * Time:   15:09
 */
public class CustomThreadPool {

    public static void main(String[] args)
    {
        int QUEUE_LENGTH = 4;
        //创建池子
        ThreadPoolExecutor tpe = getThreadPool(2, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_LENGTH), new ThreadPoolExecutor.DiscardOldestPolicy());
        System.out.println("###");
        //关闭池子
        threadPoolShutdown(tpe);
    }

    /**
     *
     * @param corePoolSize      :池中保存的线程数，包含空闲线程
     * @param maximumPoolSize   ：池中允许的最大线程数
     * @param keepAliveTime     ：当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
     * @param unit                 ：参数的时间单位
     * @param workQueue             ：执行前用于保持任务的队列。此队列保持由execute方法提交的Runnale任务
     * @param handler               ：由于超出线程范围和队列容量而使执行被阻塞时所使用得处理程序。
     * @return
     */
    public static ThreadPoolExecutor getThreadPool(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime,
                            TimeUnit unit,
                            BlockingQueue<Runnable> workQueue,
                             RejectedExecutionHandler handler)
    {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,handler);
        return tpe;
    }

    /**
     * 关闭线程池
     * @param tpe
     */
    public static void threadPoolShutdown(ThreadPoolExecutor tpe)
    {
        if(tpe!=null)
        {
            tpe.shutdown();
        }
    }
}

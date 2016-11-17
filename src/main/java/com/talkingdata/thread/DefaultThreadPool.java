package com.talkingdata.thread;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池
 * User：    ysl
 * Date:   2016/11/17
 * Time:   21:53
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>  {

    //线程池的最大限制数
    private  static final int MAX_WORKER_NMBERS = 10 ;
    //线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //线程池最小数量
    private static final int MIN_WORKER_NUMBERS = 1 ;

    //这是一个工作列表，将会向这里插入工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    //工作者线程的数量
    private int  workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool()
    {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
            workerNum = num> MAX_WORKER_NMBERS?MAX_WORKER_NMBERS:num<MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
            initializeWorkers(workerNum);
    }

    //初始化线程工作

    private void initializeWorkers(int num)
    {
        for (int i = 0; i < num; i++)
        {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"Thread-worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }



    public void execute(Job job)
    {
        if(job!=null)
        {
            //添加一个工作，然后进行通知
            synchronized (jobs)
            {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutdown()
    {
        for(Worker worker:workers)
        {
            worker.shutdown();
        }
    }

    public void addWorkers(int num)
    {
        synchronized (jobs)
        {
            //限制新增worker的数量不得超过最大值
            if(num + this.workerNum >MAX_WORKER_NMBERS)
            {
                num = MAX_WORKER_NMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum +=num;
        }
    }

    public void removeWorkers(int num)
    {
        synchronized (jobs)
        {
            if(num>=this.workerNum)
            {
                throw  new IllegalArgumentException("Beyond workerNum");
            }
            //按照给定的数量停止worker
            int count = 0;
            while (count<num)
            {
                Worker worker = workers.get(count);
                if(workers.remove(worker))
                {
                    worker.shutdown();
                    count++;
                }
            }
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements  Runnable {

        //是否工作
        private volatile boolean running = true ;

        public void run()
        {

            while (running)
            {
                Job job = null;
                synchronized (jobs)
                {
                    //如果工作列表是空的，那么就wait
                    while (jobs.isEmpty())
                    {
                        try{
                            jobs.wait();
                        }catch (InterruptedException ex)
                        {
                            //感知到外界对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();

                }
                if(job!=null)
                {
                    try{
                        job.run();
                    }catch (Exception e)
                    {
                        //忽略job中执行中的Exception
                    }
                }
            }

        }

        public void shutdown()
        {
            running = false ;
        }
    }

}



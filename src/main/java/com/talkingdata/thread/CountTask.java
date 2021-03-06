package com.talkingdata.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * User：    ysl
 * Date:   2016/12/12
 * Time:   15:41
 */
public class CountTask  extends RecursiveTask<Integer>{
    private static final int THRESHOLD=2000;//阈值

    private int start ;
    private int end ;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小，则直接计算任务
        boolean canCompute = (end-start)<=THRESHOLD;
        if(canCompute)
        {
            for (int i = start; i <=end ; i++) {
                sum+=i;
            }

        }else{
            //如果任务大于阈值，则分裂成两个子任务来计算
            int middle = (end + start )/2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完毕，并得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //合并子任务
            sum = leftResult + rightResult;

        }
        return sum;
    }


    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
       ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生产一个计算任务，负责计算1+2+3+4+5
       CountTask task = new CountTask(1,100000);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
     /*   int sum = 0;
        for (int i = 0; i <10000000; i++) {
            sum += i;
        }
        System.out.println("result:"+sum);*/
        try{
            System.out.println("####"+result.get());
            long endTime = System.currentTimeMillis();
            System.out.println("用时："+(endTime - startTime));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

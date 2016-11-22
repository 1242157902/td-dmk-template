package com.talkingdata.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * User：    ysl
 * Date:   2016/11/22
 * Time:   14:20
 */
public class CountDownLatchDemo {

    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  static void main(String[] args)throws Exception
    {
        /**
         * CountDownLatch  是一个同步辅助类，在完成一组其他线程中执行的操作之前，它允许
         *              一个或者多个线程等待。
         *              两种用法：
         *              1、将计数1初始化的CountDownLatch用作一个简单的开/关锁存器，或入口：在通过调用countDown的线程打开
         *                  入口钱，所有线程调用await的线程都在入口处等待。
         *              2、用N初始化的CountDownLatch可以使一个线程在N个线程完成某项操作之前一直等待，或者使其在某项操作
         *                  完成N次之前一直等待
         */
        int n = 5;
        CountDownLatch startSignal = new CountDownLatch(1);//启动信号器
        CountDownLatch doneSignal = new CountDownLatch(n);
        for(int i=0;i<n;i++)
        {
            new Thread(new Worker(startSignal,doneSignal)).start();
        }


        doSomethingElse();//所有线程开始之前，先做一些事情
        startSignal.countDown();    //让所有线程同时开始执行
        doneSomethingElse();          //在所有线程结束之前，做一些事情

        doneSignal.await();//等待所有线程执行完毕,然后再向下执行；或者说，所有准备工作准备完毕

        System.out.println("###All thread execute done!");
    }

    public static  void doneSomethingElse()
    {
        try{
            System.out.println("doneSomethingElse");
            TimeUnit.SECONDS.sleep(5);

        }catch (Exception e)
        {
            System.out.println("########");
        }
    }


    public static void doSomethingElse()
    {
        try{
            System.out.println("doSomethingElse");
            TimeUnit.SECONDS.sleep(10);

        }catch (Exception e)
        {
            System.out.println("########");
        }
    }






    static class Worker implements Runnable
    {
        private final CountDownLatch startSignal ;
        private final CountDownLatch doneSingal;

        public Worker(CountDownLatch startSignal, CountDownLatch doneSingal) {
            this.startSignal = startSignal;
            this.doneSingal = doneSingal;
        }

        public void run()
        {
            try{
                startSignal.await();//启动信号调用，所有线程在此等待
                doWork();
                doneSingal.countDown();//完成器信号减一，代表完成一项工作
            }catch (InterruptedException e)
            {
                System.out.println("异常发生！");
                return ;
            }

        }

        public void doWork()
        {
            System.out.println(Thread.currentThread().getName()+"--"+" doWorking");
        }
    }



}

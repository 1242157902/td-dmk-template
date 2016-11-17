package com.talkingdata.thread;

/**
 * User：    ysl
 * Date:   2016/11/17
 * Time:   21:56
 */
public interface ThreadPool<Job extends Runnable> {

    //执行一个job，这个job需要实现Runnable
    void  execute(Job job);
    //关闭线程
    void shutdown();
    //增加工作者线程
    void  addWorkers(int num);
    //减少工作者线程
    void removeWorkers(int num);

    //得到正在等待执行的任务数量
    int getJobSize();
}

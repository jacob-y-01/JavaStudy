package ThreadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
// ************************************
//      线程工厂，每一个线程都是从这里创建的
// ************************************
public class MyThreadFactor implements ThreadFactory {

    //***********************
    //     构造方法设定名字
    //***********************
    public MyThreadFactor(String Prefix) {
        ThreadName=Prefix;
    }
    // 原子类

    // 让线程有原子性
    private AtomicInteger threadIdx = new AtomicInteger(0);
    private String ThreadName;
    @Override


    public Thread newThread(Runnable r) {
        Thread thread =new Thread(r);
        thread.setName(ThreadName+threadIdx.getAndIncrement());
        return thread;
    }
}

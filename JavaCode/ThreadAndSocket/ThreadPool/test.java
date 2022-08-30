import ThreadPool.MyThreadFactor;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class test {


    public static void connect() {

    }

    public static void main(String[] args) throws IOException {

        //*****************************************
        //            newCachedThreadPool
        //   1. 空闲时间超过60s，自动回收线程
        //   2. 线程池的大小上限为Integer.MAX_VALUE，可看作无限大
        //   3. 可以缓存
        //*****************************************

//        ExecutorService CThread = Executors.newCachedThreadPool(new MyThreadFactor("线程"));
//        for (int i = 0; i < 10; i++) {
//            CThread.submit(() ->
//            {
//                // 在这里重写run方法
//                // 通过runnable接口，匿名内部类，重写
//                for (int j = 0; j < 100; j++) {
//                    System.out.println(Thread.currentThread().getName() + "   " + j);
//                }
//            });
//        }

        //*****************************************
        //             newFixedThreadPool
        //      1. 指定线程池大小
        //      2. 可设置最大并行执行数
        //      3. 超出最大并行数量的线程将在
        //      LinkedBlockingQueue阻塞队列
        //*****************************************

//        ExecutorService FThread = Executors.newFixedThreadPool(10, new MyThreadFactor("线程"));
//        for (int i = 0; i < 11; i++) {
//            // 提交11个线程，必定有一个线程被阻塞
//            // 等待某一进程输出完毕后，被阻塞的线程才被替换任务执行
//            FThread.submit(() -> {
//                        for (int j = 0; j < 100; j++) {
//                            System.out.println(Thread.currentThread().getName() + "\t" + j);
//                        }
//                    }
//            );
//        }


        //*****************************************
        //         newScheduledThreadPool
        //      同：newFixedThreadPool
        //      支持定时性，周期性的任务
        //*****************************************
        ScheduledExecutorService SThread =
                Executors.newScheduledThreadPool(1, new MyThreadFactor("线程"));

        SThread.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"延时10秒");
            }
            // 延时10秒后执行     延时单位
        },10, TimeUnit.SECONDS);

        SThread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 这里的延时是首次执行的时候，延时10秒后执行
                System.out.println(Thread.currentThread().getName()+"延时10秒"+"此后，每次间隔五秒");
            }
            // 延时10秒后执行,5秒后重复     延时单位
        },10,5, TimeUnit.SECONDS);


        //*****************************************
        //       newSingleThreadExecutor
        //1. 它只有一个线程，通过：FIFO，LIFO，优先级，来决定执行顺序
        //2. 剩余的线程全部在LinkedBlockingQueue等待，等待
        //*****************************************

        ExecutorService SingleThread=Executors.newSingleThreadExecutor(new MyThreadFactor("单线程"));
        for (int i=0;i<10;i++)
        {

            SingleThread.submit(()->
            {
                for (int j=0;j<10;j++)
                {
                    System.out.println(Thread.currentThread().getName()+j);
                }
            });
        }









        // 与客户端建立长连接
        // 如何响应请求？
        // 过滤
        // 传输数据（什么格式？）

    }
}
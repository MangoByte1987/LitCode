import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadPool {
    private ConcurrentLinkedQueue<Runnable> runnableTasks ;
    private List<ThreadPoolThread> workerThreads;
    private AtomicBoolean startPool;

    private SimpleThreadPool(int noOfThreads){
        this.runnableTasks = new ConcurrentLinkedQueue<>();
        this.startPool = new AtomicBoolean(true);
        this.workerThreads = new ArrayList<>();
        for(int i = 0; i<noOfThreads;i++){
            ThreadPoolThread t = new ThreadPoolThread("ThreadPoolThread",startPool,runnableTasks);
            t.start();
            workerThreads.add(t);
        }
    }

    public static SimpleThreadPool getInstance(){
        return new SimpleThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public SimpleThreadPool getInstance(int noOfThreads){
        return new SimpleThreadPool(noOfThreads);
    }

    public void execute(Runnable runnable){
        if(startPool.get()) {
            runnableTasks.add(runnable);
        }else{
            System.out.println("ThreadPool is not running");
        }
    }

    public void stop(){
        startPool.set(false);
    }

    public void terminate(){
        runnableTasks.clear();
        stop();
    }

    public void awaitTermination(){
        if(startPool.get()){
            System.out.println("Thread pool is still running");
            return;
        }
        while(true){
            boolean flag= true;
            for(Thread thread : workerThreads){
                if(thread.isAlive()){
                    flag = false;
                    return;
                }
            }
            if(flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void awaitTermination(long timeout) throws TimeoutException {
        if(startPool.get()){
            System.out.println("Thread pool is still runnig");
            return;
        }
        Long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < timeout){
            boolean flag= true;
            for(Thread thread : workerThreads){
                if(thread.isAlive()){
                    flag = false;
                    return;
                }
            }
            if(flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
    }

    private class ThreadPoolThread extends Thread {
        private AtomicBoolean startPool;
        private ConcurrentLinkedQueue<Runnable> runnaleTasks;
        public ThreadPoolThread(String name,AtomicBoolean startPool,ConcurrentLinkedQueue<Runnable> runnaleTasks){
            super(name);
            this.runnaleTasks=runnaleTasks;
            this.startPool = startPool;
        }
        @Override
        public void run(){
            try{
                while(startPool.get() || !runnaleTasks.isEmpty()){
                    Runnable runnable;
                    while((runnable = runnaleTasks.poll()) != null){
                            runnable.run();;
                    }
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static  class SimpleThreadPoolTest {
        @Test
        public static void testSimpleThreadPool() {
            int runnableCnt = 10;
            final AtomicInteger count = new AtomicInteger(0);
            SimpleThreadPool simpleThreadPool = getInstance();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    count.incrementAndGet();
                }
            };

            for (int i = 0; i < runnableCnt; i++) {
                simpleThreadPool.execute(r);
            }
            simpleThreadPool.stop();
            simpleThreadPool.awaitTermination();
            Assert.assertEquals("Runnables executed should be same as runnables sent to threadpool", runnableCnt, count.get());
        }
    }

    public static void main(String [] args){
        SimpleThreadPoolTest.testSimpleThreadPool();
    }
}

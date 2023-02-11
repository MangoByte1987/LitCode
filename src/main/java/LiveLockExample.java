import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockExample {
    static int cnt1 = 0;
    static int cnt2=0;
    private static final Lock lock1 = new ReentrantLock(true);
    private static final Lock lock2 = new ReentrantLock(true);
    public  static void main (String [] args){
        Thread a = new Thread(LiveLockExample::doA,"Thread A");
        Thread b = new Thread(LiveLockExample::doB,"Thread A");

        a.start();
        b.start();
    }


    private static void doA() {
        cnt1++;
        try{
            while(!lock1.tryLock() && cnt1 <10){
                cnt1++;
                System.out.println(Thread.currentThread().getName() + " waiting for lock 1");
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread().getName() + " Holds lock 1");
            try {
                while (!lock2.tryLock() && cnt2< 10) {
                    cnt2++;
                    System.out.println(Thread.currentThread().getName() + " waiting for lock 2");
                }
                System.out.println(Thread.currentThread().getName() + " Holds lock 2");
                try {
                    System.out.println(Thread.currentThread().getName() + " critical section of doA");
                } finally {
                    lock2.unlock();
                    System.out.println(Thread.currentThread().getName() + " does not hold lock 2 anymore");
                }
            }finally {
                lock1.unlock();
                System.out.println(Thread.currentThread().getName() + " does not hold lock 1 anymore");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doB() {

        try{
            while(!lock2.tryLock() && cnt2<10){
                cnt2++;
                System.out.println(Thread.currentThread().getName() + " waiting for lock 2");
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread().getName() + " Holds lock 2");
            try {
                while (!lock1.tryLock() && cnt1<10) {
                    cnt1++;
                    System.out.println(Thread.currentThread().getName() + " waiting for lock 1");
                }
                System.out.println(Thread.currentThread().getName() + " Holds lock 1");
                try {
                    System.out.println(Thread.currentThread().getName() + " critical section of doB");
                } finally {
                    lock1.unlock();
                    System.out.println(Thread.currentThread().getName() + " does not hold lock 1 anymore");
                }
            }finally {
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + " does not hold lock 2 anymore");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

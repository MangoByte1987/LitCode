public class ThreadingDemo extends Thread{
    public void run(){
        System.out.println("Thread is running : " + currentThread());

    }
    public static void main(String [] args){
       /* ThreadingDemo t = new ThreadingDemo();
        t.start();
        ThreadRunnalbe tr = new ThreadRunnalbe();
        Thread t1 = new Thread(tr);
        t1.start();*/
        Thread t = Thread.currentThread();
        System.out.println(t);
    }
    static class ThreadRunnalbe implements Runnable {
        public void run() {
            System.out.println("Thread is running using runnable : " + currentThread());
        }
    }
}


package First;

import java.util.concurrent.*;

public class TheMain {
    public static void main(String[] args) {
/*
        Ticket t = new Ticket();
        Thread t1 = new Thread(t,"票窗口1");
        Thread t2 = new Thread(t,"票窗口2");
        Thread t3 = new Thread(t,"票窗口3");
        t3.start();
        t1.start();
        t2.start();*/

        Ticket t1 = new Ticket("Station 1");
        ExecutorService exec = new ThreadPoolExecutor(2,5,5,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        exec.execute(t1);
        exec.execute(t1);
        exec.execute(t1);
        exec.shutdown();
        }
}

package First;

import java.util.Date;

public class Ticket implements Runnable{
    private static int tickets = 10000;
    private String nameStation;
    public Ticket(String name) {
        nameStation = name;
    }
    public void run() {
        //如果synchronized (Ticket.class) { }加在此处,则【一个】Thread会执行完循环,直到tickets <= 0.其他Thread不会执行循环体。
            while (tickets > 0) {
                /*如果synchronized (Ticket.class) { }加在此处，则允许多个Thread进入循环，但是因为加锁的缘故，改变tickets只会由Thread【1】完成*/
                /*因为tickets是static的原因，只有一个副本,下一个执行等候在While的Thread【2】得到的值是被【1】修改后的，因此保证了Thread【2】处理正确的值*/
                /*添加if(tickets > 0) {}的原因是:如果Thread【1】执行到了tickets = 1,并使tickets--，由于其他线程已在锁之前等候，若不判断tickets的值,则会执
                行一次循环体，使tickets为负值*/
                synchronized (Ticket.class) {
                    if(tickets > 0) {
                        System.out.println("由" + Thread.currentThread().getName() + "售出第" + tickets + "张票,时间:" + (new Date().getTime()));
                        tickets--;
                        if (tickets == 0) {
                            System.out.println("DONE.");
                        }
                    }
                }
            }
    }
}

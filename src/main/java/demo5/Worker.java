package demo5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    private Random random = new Random(42);
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public  void stage1(){
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }
    public  void stage2(){
        synchronized(lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }
    public void process(){
        for (int i=0;i<1000;i++) {
            stage1();
            stage2();
        }
    }

    public void main() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Runnable r = new Runnable() {
            public void run() {
                process();
            }
        };

        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();

        t1.join();
        t2.join();
        long endTime   = System.currentTimeMillis();
        System.out.println("it took " +(endTime-startTime));
        System.out.println(list1.size());
        System.out.println(list2.size());
    }

    public static void main(String[] args) throws InterruptedException {

        new Worker().main();


    }
}

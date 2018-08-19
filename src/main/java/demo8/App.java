package demo8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Runner implements Runnable {
    private ReentrantLock lock;
    private Condition condition;
    private String name;

    public Runner(ReentrantLock lock, Condition condition,String name) {
        this.lock      = lock;
        this.condition = condition;
        this.name      = name;
    }

    public void run() {
      for(int i=0;true;i++) {
          lock.lock();
          condition.signal();

          try {
              App.count = App.count +1;
              System.out.println(App.count +"="+ name);
              condition.await();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

      }
    }
}
class Runner2 implements Runnable {
    private Object obj;
    private String name;

    public Runner2(Object obj,String name) {
        this.obj      = obj;
        this.name      = name;
    }

    public void run() {
        for(int i=0;true;i++) {
           synchronized (obj){
            obj.notifyAll();
               try {
                   System.out.println(name);
                   obj.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }

        }
    }
}
public class App {
     static int count = 0;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ExecutorService service = Executors.newFixedThreadPool(3);
        //service.submit(new Runner(lock,condition,"ping"));
        //service.submit(new Runner(lock,condition,"pong"));
        Object obj = new Object();
        service.submit(new Runner2(obj,"ping"));
        service.submit(new Runner2(obj,"pong"));
    }
}

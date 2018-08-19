package demo11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 class Runner implements Runnable{
    private String mssage;

     public Runner(String mssage) {
         this.mssage = mssage;
     }
     private void play(){
         synchronized (Runner.class){
             Runner.class.notifyAll();
             System.out.println(this.mssage);
             try {
                 Runner.class.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
     public void run() {
      while(true){
          play();
      }

     }
}
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runner("ping"));
        executorService.submit(new Runner("pong"));

    }
}

package demo1;

class Runner extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println("hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
         Thread t1 = new Runner();
         t1.start();
         Thread t2 = new Runner();
         t2.start();
         t1.join();
         t2.join();

    }
}

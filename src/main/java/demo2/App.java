package demo2;

class Runner implements Runnable{
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
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runner());
        t1.start();
        Thread t2 = new Thread(new Runner());
        t2.start();
        t1.join();
        t2.join();
    }

}

package demo3;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
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
        };
        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r1);
        t2.start();
        t1.join();
        t2.join();
    }
}

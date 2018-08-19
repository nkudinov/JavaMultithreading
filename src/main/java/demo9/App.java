package demo9;
class Runner implements Runnable{
    private Object obj;

    public void run() {
        synchronized (obj){
            System.out.println("println");
        }
    }

    public Runner(Object obj) {
        this.obj = obj;
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        synchronized(o){
            Thread t = new Thread(new Runner(o));
            t.start();
            t.join();
        }
    }
}

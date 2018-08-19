package demo10;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class BlockingQueue<A> {
  private List<A> list;
  private int limit;
  private Object lock;

    public BlockingQueue(int limit) {
        this.list = new ArrayList<A>(limit);
        this.lock = new Object();
        this.limit = limit;
    }
    public void put(A a) throws InterruptedException {
        synchronized (lock){
            while(list.size()==limit){
                lock.wait();
            }
            list.add(a);
            lock.notifyAll();
        }
    }
    public A take() throws InterruptedException {
        A ret;
        synchronized (lock){
            while(list.size()==0){
                lock.wait();
            }
            ret = list.get(list.size()-1);
        }
        return ret;
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> q = new BlockingQueue<String>(10);
        q.put("1");
        System.out.println(q.take());
    }
}

package demo4;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigInteger;
import java.util.Scanner;

class Processor extends Thread{
    public  boolean running = true;
    public BigInteger counter = BigInteger.valueOf(1);
    public void run() {
        while (running){
               counter = counter.add(counter);
        }
    }
    public void shoutdown(){
        running = false;
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        processor.start();
        System.out.println("Please enter any key t exit...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        processor.shoutdown();
        processor.join();
    }
}

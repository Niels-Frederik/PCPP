package exercises01;
import java.util.concurrent.locks.ReentrantLock;

class Printer {

    ReentrantLock lock = new ReentrantLock();

    public static void main (String args []){
        Printer p = new Printer();
        Thread t1 = new Thread(() -> { p.printForever(); });
        Thread t2 = new Thread(() -> { p.printForever(); });
        t1.start(); t2.start();
        try { t1.join(); t2.join();}
        catch (InterruptedException e) {}
        //p.print();
    }

    public void print() {
        lock.lock();
        System.out.print("-");
        try {
            Thread.sleep(50);
        } catch (InterruptedException exn) { }
        System.out.print("|");
        lock.unlock();
    }

    public void printForever(){
        while (true){
            print();
        }
    }
}



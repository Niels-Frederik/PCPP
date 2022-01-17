package exercises02;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorReadWrite {

    private int readerCount;
    private boolean anyWriter;

    public MonitorReadWrite(){
        readerCount = 0;
        anyWriter = false;
    }

    public synchronized void readLock(){
        try {
            while (anyWriter) {
                this.wait();
            }
            readerCount++;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readUnlock() {
        readerCount--;
        if(readerCount == 0) this.notifyAll();
    }

    public synchronized void writeLock() {
        try {
            while(anyWriter){
                this.wait();
            }
            anyWriter = true;
            while(readerCount > 0){
                this.wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeUnlock() {
        try {
            anyWriter = false;
            this.notifyAll();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

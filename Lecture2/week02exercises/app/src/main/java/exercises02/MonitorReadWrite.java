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
        catch(Exception e){
        }
    }

    public synchronized void readUnlock() {
        try {
            readerCount--;
            if(readerCount == 0) this.notifyAll();
        }
        catch (Exception e){
        }
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
        catch (Exception e){
        }
    }

    public synchronized void writeUnlock() {
        try {
            anyWriter = false;
            this.notifyAll();
        }
        catch (Exception e){}
    }
}
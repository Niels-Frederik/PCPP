// For week 7
// raup@itu.dk * 10/10/2021
package exercises07;

import java.util.concurrent.atomic.AtomicReference;

class ReadWriteCASLock implements SimpleRWTryLockInterface {

    public static void main(String[] args) {
	//TODO execute tests (7.2.5 & 7.2.6)
    }

    AtomicReference<ReaderList> Readers;
    AtomicReference<Writer> WriterLock;
    // TODO: Add necessary field(s) for the class

    public boolean readerTryLock() {
	// TODO 7.2.3
	return true;
    }
    
    // Challenging 7.2.7: You may add new methods
	
    public void readerUnlock() {
	// TODO 7.2.4
    }
    
    public boolean writerTryLock() {
        //Will not necesarily work as readers can still exist
        //TODO fix that it cannot aquire if readers exists
        return (WriterLock.compareAndSet(null, new Writer(Thread.currentThread())));
    }

    public void writerUnlock() throws Exception {
        var currentThreadHoldsLock = WriterLock.get().thread == Thread.currentThread();
        if (!currentThreadHoldsLock) throw new Exception("Writer does not hold lock");
        WriterLock.compareAndSet(WriterLock.get(), new Writer(null));
    }

    private static abstract class Holders { }

    private static class ReaderList extends Holders {
	private final Thread thread;
	private final ReaderList next;

    public ReaderList(Thread t)
    {
        thread = t;
        next = null;
    }

    public boolean Contains(Thread threadToFind)
    {
        if (threadToFind == thread) return true;
        else if (next == null) return false;
        else return Contains(next.thread);
    }

    public boolean Remove(Thread threadToFind)
    {
        if (threadToFind == thread) return true;
        else if (next == null) return false;
        else return Contains(next.thread);
    }

    private static class Writer extends Holders {
	public final Thread thread;

    public Writer(Thread t)
    {
        thread = t;
    }
    }
}

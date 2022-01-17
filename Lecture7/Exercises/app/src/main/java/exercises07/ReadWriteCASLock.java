// For week 7
// raup@itu.dk * 10/10/2021
package exercises07;

import java.io.Writer;
import java.util.concurrent.atomic.AtomicReference;

class ReadWriteCASLock implements SimpleRWTryLockInterface {
    AtomicReference<Holders> Holders = new AtomicReference<>();

    public static void main(String[] args) {
        ReadWriteCASLock rw = new ReadWriteCASLock();
        rw.SequentialTest();
        //rw.SequentialTest2();
        //rw.MultithreadingTest();
    }

    public boolean readerTryLock() {
        boolean changed = false;
        do {
            if (Holders.get() instanceof Writer) return false;
            if (Holders.get() == null) changed = Holders.compareAndSet(null, new ReaderList(Thread.currentThread()));
            else
            {
                if (((ReaderList) Holders.get()).Contains(Thread.currentThread())) return true;
                changed = Holders.compareAndSet(Holders.get(), new ReaderList(Thread.currentThread(), ((ReaderList) Holders.get())));
            }
        }
        while (!changed);
        return true;
    }

    public void readerUnlock() throws Exception {
        if (Holders.get() instanceof Writer) throw new Exception("Writer has the lock");
        if (!(((ReaderList) Holders.get()).Contains(Thread.currentThread()))) throw new Exception("Reader does not hold lock");
        Holders.compareAndSet(Holders.get(), ((ReaderList) Holders.get()).Remove(Thread.currentThread()));
    }

    public boolean writerTryLock() {
        if (Holders.get() instanceof ReaderList) return false;
        return Holders.compareAndSet(null, new Writer(Thread.currentThread()));
    }

    public void writerUnlock() throws Exception {
        if (Holders.get() instanceof ReaderList) throw new Exception("A reader has the lock");
        var currentThreadHoldsLock = Holders.get().thread == Thread.currentThread();
        if (!currentThreadHoldsLock) throw new Exception("Writer does not hold lock");
        Holders.compareAndSet(Holders.get(), null);
    }

    private static abstract class Holders {
        final Thread thread;

        Holders(Thread t) {
            thread = t;
        }
    }

    private static class ReaderList extends Holders {
        private final ReaderList next;

        public ReaderList(Thread t) {
            super(t);
            next = null;
        }

        public ReaderList(Thread t, ReaderList next) {
            super(t);
            this.next = next;
        }

        public boolean Contains(Thread threadToFind) {
            if (threadToFind == thread) return true;
            else if (next == null) return false;
            else return next.Contains(threadToFind);
        }

        public ReaderList Remove(Thread threadToRemove) {
            var currentHolder = this;
            if ((currentHolder.next == null && threadToRemove == currentHolder.thread)) return null;
            ReaderList newList = null;
            while (currentHolder != null) {
                if (currentHolder.thread == threadToRemove)
                {
                    currentHolder = currentHolder.next;
                    continue;
                }
                newList = new ReaderList(currentHolder.thread, newList);
                currentHolder = currentHolder.next;
            }
            return newList;
        }
    }

    private static class Writer extends Holders {

        public Writer(Thread t) {
            super(t);
        }
    }

    public void SequentialTest()
    {
        WriterLockLogging();
        ReaderLockLogging();
        ReaderUnlockLogging();
        WriterUnlockLogging();
        ReaderLockLogging();
        WriterLockLogging();
        WriterUnlockLogging();
        ReaderUnlockLogging();
    }

    public void SequentialTest2()
    {
        ReaderLockLogging();
        ReaderUnlockLogging();
        ReaderLockLogging();
        ReaderUnlockLogging();
        WriterLockLogging();
        WriterUnlockLogging();
        ReaderLockLogging();
        WriterUnlockLogging();
        ReaderUnlockLogging();
        WriterLockLogging();
        WriterUnlockLogging();
    }

    public void MultithreadingTest()
    {
        //Start a writer
        for (int i = 0; i < 4 ; i++) {
            Runnable runnable = () ->
            {
                if (WriterLockLogging()) {

                    //simulate some work
                    try {
                        Thread.sleep(3000);
                        System.out.println("Writer thread " + Thread.currentThread() + "did some work");
                        WriterUnlockLogging();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
           Thread t = new Thread(runnable);
           t.start();
        }

        for(int i = 0; i < 4 ; i++)
        {
            Runnable runnable = () ->
            {
                if (ReaderLockLogging()) {

                    try {
                        Thread.sleep(3000);
                        System.out.println("Reader thread " + Thread.currentThread() + "did some work");
                        ReaderUnlockLogging();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    public boolean WriterLockLogging()
    {
        if (this.writerTryLock())
        {
            System.out.println("Writer thread " + Thread.currentThread() + " acquired lock");
            return true;
        }
        else
        {
            System.out.println("Writer thread " + Thread.currentThread() + " failed to acquire lock");
            return false;
        }
    }

    public boolean WriterUnlockLogging()
    {
        try {
            this.writerUnlock();
            System.out.println("Writer thread " + Thread.currentThread() + " released lock");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Writer thread " + Thread.currentThread() + " failed to release lock");
            return false;
        }
    }

    public boolean ReaderLockLogging()
    {
        if (this.readerTryLock())
        {
            System.out.println("Reader thread " + Thread.currentThread() + " acquired lock");
            return true;
        }
        else
        {
            System.out.println("Reader thread " + Thread.currentThread() + " failed to acquire lock");
            return false;
        }
    }

    public boolean ReaderUnlockLogging()
    {
        try {
            this.readerUnlock();
            System.out.println("Reader thread " + Thread.currentThread() + " released lock");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Reader thread " + Thread.currentThread() + " failed to release lock");
            return false;
        }
    }
}

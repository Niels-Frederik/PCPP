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
        //TODO execute tests (7.2.5 & 7.2.6)
    }

    public boolean readerTryLock() {
        //System.out.println("Reader try lock");
        if (Holders.get() instanceof Writer) return false;
        var NoneExists = Holders.compareAndSet(null, new ReaderList(Thread.currentThread()));
        if (NoneExists || ((ReaderList) Holders.get()).Contains(Thread.currentThread())) return true;
        return Holders.compareAndSet(Holders.get(), new ReaderList(Thread.currentThread(), (ReaderList) Holders.get()));
        //TODO add loop to try
    }

    public void readerUnlock() throws Exception {
        //System.out.println("Reader try unlock");
        var currentThreadHoldsLock = ((ReaderList) Holders.get()).Contains(Thread.currentThread());
        if (!currentThreadHoldsLock) throw new Exception("Writer does not hold lock");
        ((ReaderList) Holders.get()).Remove(Thread.currentThread());
        //TODO add loop
    }

    public boolean writerTryLock() {
        //System.out.println("Writer try lock");
        if (Holders.get() instanceof ReaderList) return false;
        return Holders.compareAndSet(null, new Writer(Thread.currentThread()));
    }

    public void writerUnlock() throws Exception {
        //System.out.println("Writer try unlock");
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
            else return Contains(next.thread);
        }

        public ReaderList Remove(Thread threadToRemove) {
            var currentHolder = this;
            if (currentHolder.next == null) return null;
            var prevList = currentHolder;
            while (currentHolder.next != null) {
                if (currentHolder.thread == threadToRemove) currentHolder = currentHolder.next;
                currentHolder = currentHolder.next;
                prevList = new ReaderList(currentHolder.thread, prevList);
            }
            return prevList;
        }
    }

        private static class Writer extends Holders {

            public Writer(Thread t) {
                super(t);
            }
        }

    public void SequentialTest()
    {
        //writer locks
        WriterLockLogging();

        //Reader tries to lock
        ReaderLockLogging();

        //Reader tries to unlock
        ReaderUnlockLogging();

        //Writer unlocks
        WriterUnlockLogging();

        //Reader tries to lock
        ReaderLockLogging();

        //Writer tries to lock
        WriterLockLogging();

        //Writer tries to unlock
        WriterUnlockLogging();

        //Reader unlocks
        ReaderUnlockLogging();

    }

    public void WriterLockLogging()
    {
        if (this.writerTryLock()) System.out.println("Writer acquired lock");
        else System.out.println("Writer failed to acquire lock");
    }

    public void WriterUnlockLogging()
    {
        try {
            this.writerUnlock();
            System.out.println("Writer released lock");
        }
        catch (Exception e)
        {
            System.out.println("Writer failed to release lock");
        }
    }

    public void ReaderLockLogging()
    {
        if (this.writerTryLock()) System.out.println("Reader acquired lock");
        else System.out.println("Reader failed to acquire lock");
    }

    public void ReaderUnlockLogging()
    {
        try {
            this.readerUnlock();
            System.out.println("Reader released lock");
        }
        catch (Exception e)
        {
            System.out.println("Reader failed to release lock");
        }
    }
}

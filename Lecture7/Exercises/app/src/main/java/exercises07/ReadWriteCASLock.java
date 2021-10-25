// For week 7
// raup@itu.dk * 10/10/2021
package exercises07;

import java.io.Writer;
import java.util.concurrent.atomic.AtomicReference;

class ReadWriteCASLock implements SimpleRWTryLockInterface {

    public static void main(String[] args) {
        //TODO execute tests (7.2.5 & 7.2.6)
    }

    AtomicReference<Holders> Holders;

    public boolean readerTryLock() {
        if (Holders.get() instanceof Writer) return false;
        var NoneExists = Holders.compareAndSet(null, new ReaderList(Thread.currentThread()));
        if (NoneExists || ((ReaderList) Holders.get()).Contains(Thread.currentThread())) return true;
        return Holders.compareAndSet((ReaderList) Holders.get(), new ReaderList(Thread.currentThread(), (ReaderList) Holders.get()));
        //TODO add loop to try
    }

    public void readerUnlock() throws Exception {
        var currentThreadHoldsLock = ((ReaderList) Holders.get()).Contains(Thread.currentThread());
        if (!currentThreadHoldsLock) throw new Exception("Writer does not hold lock");
        ((ReaderList) Holders.get()).Remove(Thread.currentThread());
        //TODO add loop
    }

    public boolean writerTryLock() {
        return Holders.compareAndSet(null, new Writer(Thread.currentThread()));
    }

    public void writerUnlock() throws Exception {
        var currentThreadHoldsLock = Holders.get().thread == Thread.currentThread();
        if (!currentThreadHoldsLock) throw new Exception("Writer does not hold lock");
        Holders.compareAndSet(Holders.get(), null);
    }

    private static abstract class Holders {
        final Thread thread;
        Holders(Thread t)
        {
            thread = t;
        }

        Holders(Thread a, Thread b, Thread c) {
            if (a == b) thread = c;
            else thread = a;
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

        public ReaderList(ReaderList rl, Thread exclusion) {
            super(rl.thread, exclusion, rl.next.thread);
            if (rl.thread == exclusion) {
                next = rl.next;
            }
            if (next.next)
        }

        public boolean Contains(Thread threadToFind) {
            if (threadToFind == thread) return true;
            else if (next == null) return false;
            else return Contains(next.thread);
        }


        public boolean Remove(ReaderList rl) {




            if (threadToFind == thread) {
                new ReaderList(prev, new ReaderList())
            } else if (next == null) return false;
            else return next.Remove(threadToFind);
        }
        //Set the entire reference to null if it is the only element
    }

        private static class Writer extends Holders {

            public Writer(Thread t) {
                super(t);
            }
        }
    }
}

package exercises04;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Person {
    private final long id;
    private volatile String name;
    private volatile int zip;
    private volatile String address;
    private static long nextId = 0;
    private static final Object staticLock = new Object();

    public Person()
    {
        synchronized (staticLock)
        {
            id = nextId++;
        }
    }

    public Person(long id) {
        synchronized (staticLock) {
            if (nextId == 0) {
                this.id = id;
                nextId = id + 1;
            } else this.id = nextId++;
        }
    }

    public synchronized void changeZipAndAddress(int zip, String address)
    {
        this.zip = zip;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }
}

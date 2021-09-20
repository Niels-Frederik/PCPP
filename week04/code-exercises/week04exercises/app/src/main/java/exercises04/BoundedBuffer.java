package exercises04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BoundedBuffer implements BoundedBufferInteface{

    private final Queue<Object> queue;
    private final int maxSize;
    private final Semaphore producers;
    private final Semaphore consumers;
    private final Semaphore consumerLock;
    private final Semaphore producerLock;

    public BoundedBuffer(int maxSize)
    {
        queue = new LinkedList<>();
        this.maxSize = maxSize;
        producers = new Semaphore(maxSize);
        consumers = new Semaphore(0);
        producerLock = new Semaphore(1);
        consumerLock = new Semaphore(1);
    }

    @Override
    public Object take() throws Exception {

        try
        {
            consumers.acquire();
        }
        catch (Exception e)
        {
            consumers.release();
            return null;
        }
        try
        {
            consumerLock.acquire();
        }
        catch (Exception e)
        {
            consumerLock.release();
            return null;
        }

        var element = queue.poll();
        consumerLock.release();
        producers.release();
        return element;
    }

    @Override
    public void insert(Object elem) throws Exception {
        try
        {
            producers.acquire();
        }
        catch (Exception e)
        {
            producers.release();
            return;
        }
        try
        {
            producerLock.acquire();
        }
        catch (Exception e)
        {
            producerLock.release();
            return;
        }

        queue.add(elem);
        producerLock.release();
        consumers.release();
    }

    //public BoundedBuffer(int maxSize)
    //{
    //    queue = new LinkedList<>();
    //    this.maxSize = maxSize;
    //    producers = new Semaphore(maxSize);
    //    consumers = new Semaphore(0);
    //}

    //@Override
    //public Object take() throws Exception {
    //    consumers.acquire();
    //    var element = queue.poll();
    //    producers.release();
    //    return element;
    //}

    //@Override
    //public void insert(Object elem) throws Exception {
    //    producers.acquire();
    //    queue.add(elem);
    //    consumers.release();
    //}
}

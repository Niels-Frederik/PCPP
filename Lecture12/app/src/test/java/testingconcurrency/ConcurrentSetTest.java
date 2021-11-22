package testingconcurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.CyclicBarrier;

import static org.junit.jupiter.api.Assertions.assertTrue;
// TODO: Very likely you need to expand the list of imports

public class ConcurrentSetTest {

    // Variable with set under test
    private ConcurrentIntegerSet set;
    private CyclicBarrier barrier;

    // TODO: Very likely you should add more variables here

    @BeforeEach
    public void initialize() {
        // init set
        //set = new ConcurrentIntegerSetBuggy();
        //set = new ConcurrentIntegerSetFixed();
        set = new ConcurrentIntegerSetLibrary();
        barrier = new CyclicBarrier(10+1);
        // set = new ConcurrentIntegerSetSync();
        // set = new ConcurrentIntegerSetLibrary();
    }

    @RepeatedTest(1000)
    @DisplayName("Exercise 1")
    public void Exercise1() {
        for (int i = 0; i < 10 ; i++)
        {
            new Thread(() ->
            {
                try { barrier.await(); } catch (Exception e) {}
                for (int j = 0; j < 100; j++)
                {
                    set.add(j);
                }
                try { barrier.await(); } catch (Exception e) {}
            }).start();
        }
        try { barrier.await(); } catch (Exception e) {}
        try { barrier.await(); } catch (Exception e) {}

        assertTrue(set.size()==100);
    }

    @RepeatedTest(1000)
    @DisplayName("Exercise 2")
    public void Exercise2() {
        for (int i = 0; i < 100 ; i++) set.add(i);

        for (int i = 0; i < 10 ; i++)
        {
            new Thread(() ->
            {
                try { barrier.await(); } catch (Exception e) {}
                for (int j = 0; j < 100; j++)
                {
                    set.remove(j);
                }
                try { barrier.await(); } catch (Exception e) {}
            }).start();
        }
        try { barrier.await(); barrier.await();} catch (Exception e) {}
        //try { barrier.await(); } catch (Exception e) {}

        assertTrue(set.size()>=0);
    }

    // TODO: Define your tests below
}

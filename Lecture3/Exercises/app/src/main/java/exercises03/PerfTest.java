package exercises03;

public class PerfTest {
    private volatile int vCtr;
    private int ctr;

    public int vInc () {
        return ++vCtr;
    }
    public int inc () {
        return ++ctr;
    }
}

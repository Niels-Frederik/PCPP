// For week 7
// raup@itu.dk * 10/10/2021
package exercises07;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

interface Histogram {
    public void increment(int bin);
    public int getCount(int bin);
    public int getSpan();
    public int getAndClear(int bin);
}

class CasHistogram implements Histogram
{
    private AtomicInteger[] counts;
    private AtomicInteger total = new AtomicInteger(0);

    public CasHistogram(int span) {
        this.counts = new AtomicInteger[span];
        Arrays.fill(counts, new AtomicInteger(0));
    }

    @Override
    public void increment(int bin) {
        int oldValue;
        do {
            oldValue = counts[bin].get();
        } while (!counts[bin].compareAndSet(oldValue, oldValue+1));
        do {
            oldValue = total.get();
        } while (!total.compareAndSet(oldValue, oldValue+1));
    }

    @Override
    public int getCount(int bin) {
        return counts[bin].get();
    }

    @Override
    public int getSpan() {
        return counts.length;
    }

    @Override
    public int getAndClear(int bin) {
        var oldValue = counts[bin].get();
        do {
            oldValue = counts[bin].get();
        } while (!counts[bin].compareAndSet(oldValue, 0));
        return oldValue;
    }
}
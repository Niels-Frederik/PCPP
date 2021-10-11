package exercises06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntToDoubleFunction;

public class HistogramCountPrimes {
    public static void main(String[] args) { new HistogramCountPrimes(); }

    public HistogramCountPrimes() {
        //Mark7("Test", i -> TestHistogram());
        TestHistogram();
    }

    public static int primeFactors(int n)
    {
        int count = 0;
        while (n%2==0)
        {
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            while (n%i == 0)
            {
                n /= i;
            }
        }

        if (n > 2) count++;
        return count;
    }

    private static void TestHistogram() {
        Histogram2 hist = new Histogram2(30);
        int range = 5000000;
        int amountOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(amountOfThreads);
        List<CompletableFuture> futures = new ArrayList<>();
        final int perThread = range/amountOfThreads;
        for (int t=0; t<amountOfThreads; t++) {
            final int from = perThread * t;
            final int to = (t+1==amountOfThreads) ? range : perThread * (t+1);
            futures.add(CompletableFuture.runAsync(() -> {
                for (int i = from; i < to; i++) {
                        var s = primeFactors(i);
                        hist.increment(s);
                }
            }, executor));
        }
        var allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        try {
            allFutures.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.out.println("Total: " + hist.getTotal());
            System.out.println("Span: " + hist.getSpan());
            System.out.println("Count: " + hist.getCount(3));
        }
    }
}

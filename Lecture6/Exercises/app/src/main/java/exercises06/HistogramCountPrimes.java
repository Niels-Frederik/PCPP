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

        for(int i = 2; i< n; i++) {
            while(n%i == 0) {
                count++;
                n = n/i;
            }
        }
        if(n>2) count++;

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
            //System.out.println(from);
            //System.out.println(to);
            futures.add(CompletableFuture.runAsync(() -> {
                for (int i = from; i < to; i++) {
                        var s = primeFactors(i);
                        //System.out.println(s);
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
            for(int i = 0; i < 9; i++)
            {
                System.out.println(i + " : " + hist.getCount(i));
            }
        }
    }
}

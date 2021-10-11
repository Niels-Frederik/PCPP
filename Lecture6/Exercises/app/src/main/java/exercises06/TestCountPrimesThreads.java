package exercises06;
// Counting primes, using multiple threads for better performance.
// (Much simplified from CountprimesMany.java)
// sestoft@itu.dk * 2014-08-31, 2015-09-15
// modified rikj@itu.dk 2017-09-20
// modified jst@itu.dk 2021-09-24
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntToDoubleFunction;

public class TestCountPrimesThreads{
  public static void main(String[] args) { new TestCountPrimesThreads(); }

  public TestCountPrimesThreads() {
    final int range = 100_000;
    Mark7("countSequential", i -> countSequential(range));
    for (int c=1; c<=32; c++) {
    final int threadCount = c;
      Mark7(String.format("countParallelN %2d", threadCount),
            i -> countParallelN(range, threadCount));

      Mark7(String.format("countParallelNLocal %2d", threadCount), 
            i -> countParallelNLocal(range, threadCount));
    }
  }

  private static boolean isPrime(int n) {
    int k = 2;
    while (k * k <= n && n % k != 0)
      k++;
    return n >= 2 && k * k > n;
  }

  private static long isPrimeRange(int from, int to) {
    long count = 0;
    for (int i = from; i <= to; i++) {
      int k = 2;
      while (k * k <= i && i % k != 0)
        k++;
      if (i >= 2 && k * k > i) count++;
    }
    return count;
  }

  // Sequential solution
  private static long countSequential(int range) {
    long count = 0;
    final int from = 0, to = range;
    for (int i=from; i<to; i++)
      if (isPrime(i)) 
        count++;
    return count;
  }

  // General parallel solution, using multiple threads
  private static long countParallelN(int range, int threadCount) {
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    final int perThread = range / threadCount;
    final LongCounter lc = new LongCounter();
    List<CompletableFuture> futures = new ArrayList<>();
    for (int t=0; t<threadCount; t++) {
        final int from = perThread * t;
        final int to = (t+1==threadCount) ? range : perThread * (t+1);
        futures.add(CompletableFuture.runAsync(() -> {
          for (int i = from; i < to; i++) {
            if (isPrime(i))
              lc.increment();
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
      return lc.get();
    }
  }

  // General parallel solution, using multiple threads
  private static long countParallelNLocal(int range, int threadCount) {
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    List<CompletableFuture> futures = new ArrayList<>();
    final int perThread = range / threadCount;
    for (int t = 0; t < threadCount; t++) {
      final int from = perThread * t;
      final int to = (t + 1 == threadCount) ? range : perThread * (t + 1);
      futures.add(CompletableFuture.runAsync(() -> isPrimeRange(from, to), executor));
    }
    var allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    try {
      allFutures.get();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
    return 0;
  }

  // --- Benchmarking infrastructure ---

  public static double Mark7(String msg, IntToDoubleFunction f) {
    int n = 10, count = 1, totalCount = 0;
    double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
    do { 
      count *= 2;
      st = sst = 0.0;
      for (int j=0; j<n; j++) {
        Timer t = new Timer();
        for (int i=0; i<count; i++) 
          dummy += f.applyAsDouble(i);
        runningTime = t.check();
        double time = runningTime * 1e9 / count; 
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f ns %10.2f %10d%n", msg, mean, sdev, count);
    return dummy / totalCount;
  }
}


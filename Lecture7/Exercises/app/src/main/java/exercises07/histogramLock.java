package exercises07;

class HistogramLock implements Histogram{
  private final int[] counts;
  private volatile int total=0;

  public HistogramLock(int span) {
    this.counts = new int[span];
  }

  public synchronized void increment(int bin) {
    counts[bin] = counts[bin] + 1;
    total++;
  }

  public synchronized int getCount(int bin) {
    return counts[bin];
  }

  public synchronized float getPercentage(int bin){
    return getCount(bin) / getTotal() * 100;
  }

  public int getSpan() {
    return counts.length;
  }

  public int getAndClear(int bin) {
    return 0;
  }

  public int getTotal(){
    return total;
  }
}



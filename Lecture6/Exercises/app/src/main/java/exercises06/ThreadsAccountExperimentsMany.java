package exercises06;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadsAccountExperimentsMany {

  static final int N = 10; 
  static final int NO_TRANSACTION=5;
  static final int NO_THREADS = 10;
  static final Account[] accounts = new Account[N];
  static final Thread[] threads = new Thread[NO_THREADS];
  static Random rnd = new Random();
  public static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  
  public static void main(String[] args){ new ThreadsAccountExperimentsMany(); }

  public ThreadsAccountExperimentsMany(){
    for( int i = 0; i < N; i++){
      accounts[i] = new Account(i);
    }
    for( int i = 0; i<NO_THREADS; i++){
      try{ (threads[i] = new Thread( () -> doNTransactions(NO_TRANSACTION) )).start();}
      catch(Error ex){
        System.out.println("At i = " + i + " I got error: " + ex);
        System.exit(0);
      }
    }
    for( int i = 0; i<NO_THREADS; i++){
      try {threads[i].join();} catch(Exception dummy){};
    }
  }
  
  private static void doNTransactions(int noTransactions){
    List<CompletableFuture> futures = new ArrayList<>();
    for(int i = 0; i<noTransactions; i++){
      long amount = rnd.nextInt(5000)+100;
      int source = rnd.nextInt(N);
      int target = (source + rnd.nextInt(N-2)+1) % N; // make sure target <> source
      futures.add(doTransaction(new Transaction(amount, accounts[source], accounts[target])));
    }
    CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    try {
      allFutures.get();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
  }
  
  private static CompletableFuture doTransaction(Transaction t){
    CompletableFuture<?> future = CompletableFuture.runAsync(t, executor);
    return future;
  }
  
  static class Transaction implements Runnable{
    final Account source, target;
    final long amount;
    Transaction(long amount, Account source, Account target){
      this.amount = amount;
      this.source = source;
      this.target = target;
    }
    
    public void transfer(){
      Account min = accounts[Math.min(source.id, target.id)];
      Account max = accounts[Math.max(source.id, target.id)];
      synchronized(min){
        synchronized(max){
      //Account min = accounts[source.id];
      //Account max = accounts[target.id];
      //synchronized(min){
      //  synchronized(max){
          source.withdraw(amount);
          try{Thread.sleep(50);} catch(Exception e){}; // Simulate transaction time
          target.deposit(amount);
        }
      }
    }
    
    public String toString(){
      return "Transfer " + amount + " from " + source.id + " to " + target.id;
    }

    @Override
    public void run() {
      transfer();
    }
  }

  static class Account{
    // should have transaction history, owners, account-type, and 100 other real things
    public final int id;
    private long balance = 0;
    Account( int id ){ this.id = id;}
    public void deposit(long sum){ balance += sum; } 
    public void withdraw(long sum){ balance -= sum; }
    public long getBalance(){ return balance; }
  }

}
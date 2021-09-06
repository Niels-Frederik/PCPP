## Exercise 2.1

### 1
See the MonitorReadWrite.java file for the implementation, and run the TestMonitorReadWrite.java for a test of this.

### 2
Our solution is fair towards writer threads, as we ensure weak fairness. This is done by blocking new reader threads from starting as soon as a writer thread enters the entry locking section. As soon as this happens, the amount of reader threads will decrement, as no new threads are started, and the writer thread will eventually reach the critical section.

## Exercise 2.2

### 1

Running the example, we observed that the *main* thread entered an infinite loop. This is possible due to the way variables are stored in the cpu caches. Due to the structure of a CPU, where cores have seperate L1 and L2 cahces, it is not possible for a thread on one core to access the values of the variables stored in a cache of another core.

### 2
See MutableInteger.java

As the synchronization keyword forces the methods to flush the value from cache into main memory, the other thread can now read the correct value, and the program can terminate.

### 3
No, t would no always terminate. If the *get()* method is not synchronized, whenever *get()* is called, it will attempt to read the value from its own cache, even though the other thread may have performed a *set()*, and thereby flushed the new value to main memory.

### 4
See MutableInteger.java

Thread *t* will always terminate in this case. As we force the variable to be stored/flushed in main memory, the other tread will always read the correct value. It does however not prevent race conditions, but this is not a problem here.

## Exercise 2.3

### 1

Results from running:
- 1082715.000000
- 1112797.000000
- 1174106.000000

This shows definite rae conditions

### 2

Race conditions can still appear due to the mixing of static and non-static synchronized methods. This is because they use a seperate lock, and the non-atomic operation of increment will cause a race condition. The static method locks the entire class, whereas the non-static method locks the object, and they can both freely increment while the other method does so aswell. This produces a race condition



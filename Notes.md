# Lecture 1
## Goetz - Chapter 1
### Section 1.1 - Introduction
**Processes** : Isolated, independently executing pro-
grams to which the operating system allocates resources such as memory, ﬁle
handles, and security credentials.

processes could communicate with one another through a variety of coarse-grained communication mechanisms: sockets, signal handlers, shared memory, semaphores, and ﬁles.

Motivation for executing programs simultaneously:
- **Resource utilization** (sometimes programs have to wait for resources, and this wait time can be efficient to let another program run meanwhile)
-  **Fairness** (Programs may have equal claims to the machines resources, so it is preferable to let them share, rather than letting one program run to completion)
- **Convenience** (Easier to write multiple programs that perform a single task, and let them communicate, rather than one program performing all the tasks)

**Threads** were motivated by the same 3 factors. Threads allow multiple streams of program control flow to coexist within a process. They share process-wide resources, but each thread has its own program counter, stack and local variables. They are often called **lightweight processes** , and is more often used than processes in modern OS as units of scheduling. Since threads share variables, coordination is key.

### Section 1.2 - Benefits of threads

Benefits of threads:
- Easier writable and maintainable code (simpler code)
- Improving responsiveness (GUI)
- Improving speed (servers)
- Utilizing the modern CPU (not wasting resources)
- Simplicity of modeling (A thread does only one thing) -> Easier to test, maintain, understand, less error-prone
- No blocking for ex. multiple socket connections

### Section 1.3 - Risks of threads
**Thread safety** -> lack of this can cause problems, ex. two threads accessing the same shared value and trying to increment it. This can produce different results on each run due to the scheduler, and is a **Race condition** . This simple example can be solved by making the method synchronized ('public synchronized int...').

**Liveness failure** -> When a program enters a state where it is unable to make forward progress, ie. deadlock

**Performance hazards** -> service time, responsiveness, throughput, resource consumption, or scalability. Threads carry runtime overhead. **Context switches** where the scheduler suspends a thread temporarily so another thread can run have significant cost: saving and restoring execution context, loss of locality, and CPU time spent scheduling threads instead of running them.


## Goetz - Chapter 2
Writing thread-safe code is, at its core, about managing access to *state*, and in particular to *shared, mutable state*. Informally, an object’s state in its data, stored in state variables such as instance or static ﬁelds. An object’s state encompasses any data that can affect its externally visible behavior. shared means that multiple threads can access it, and mutable means it can be changed at runtime. Making an object thread safe means coordinating access to its mutable state.

The primary synchronization technique in java is the "synchronization" keyword.

The less code that has access to a particular variable, the easier it is to ensure that all of it uses theproper synchronization.

### Section 2.1 - What is thread safety
A class is thread-safe if it behaves correctly when accessed from multiple
threads, regardless of the scheduling or interleaving of the execution of
those threads by the runtime environment, and with no additional syn-
chronization or other coordination on the part of the calling code.


### Section 2.2 - Atomicity
An atomic action executes a single indivisible operation. '++count' is not atomic, as it is actually 3 operations: read, modify, write.

**Race conditions** -> the correctness of a computation depends on the relative timing or interleaving of multiple threads by the runtime. Can often happen in these *check-then-act* scenarios. This can also be a problem in *lazy-initialization* idioms, and can create multiple objects.

**Compound actions** -> Check-then-act and read-modify-write are called compount actions. These must be executed atomically to remain thread safe.

One solution to a thread safe counter could be the ' java.util.concurrent.atomic ' library. This proviced atomic variables and objects like AtomicLong, which can be incremented thread safely.

### Section 2.3 - Locking
Java has built in **intrinsic locks** with the synchronized block. A synchronized block has two parts: a reference to an object that will serve as the lock, and ablock of code to be guarded by that lock.A synchronized method is a shorthand for a synchronized block that spans an entire method body, and whose lock is
the object on which the method is being invoked.

'
synchronized (lock) {
    // Access or modify shared state guarded by lock
}
'

Every Java object can implicitly act as a lock for purposes of synchronization;
these built-in locks are called intrinsic locks or monitor locks. The lock is automatically acquired by the executing thread before entering a synchronized block and automatically released when control exits the synchronized block

Intrinsic locks in Java act as mutexes (or mutual exclusion locks), which means
that at most one thread may own the lock. When thread A attempts to acquire a
lock held by thread B, A must wait, or block, until B releases it. If B never releases the lock, A waits forever.

**Reentrancy** -> if a thread requests a lock that is already held by another thread, the requesting thread blocks. But because intrinsic locks are reentrant, if a thread tries to acquire a lock that it already holds, the request succeeds. Reentrancy means that locks are acquired on a per-thread rather than per-invocation basis. For each lock, an acquisition count and owning thread exists. When the count is 0, the resource is available, when a thread aquires it, the counter is incremented. When the thread exits the synchronization block, the count is decremented. When the count reaches 0, the lock is released. This is to facilitate synchronized methods/classes calling other synchronized methods/clases, that would otherwise deadlock.

### Section 2.4 - Guarding state with locks
For each mutable state variable that may be accessed by more than one
thread, all accesses to that variable must be performed with the same
lock held. In this case, we say that the variable is guarded by that lock.

It is often not enough to simply wrap something in a synchronization block. If multiple threads attempt to access something, they must share the same lock. A lock does not prevent another thread from accessing the object, it prevents another thread from aquirering the same lock.

A common locking convention is to encapsulate all mutable state within an
object and to protect it from concurrent access by synchronizing any code path
that accesses mutable state using the object’s intrinsic lock.

Not all data needs to be guarded by locks—only mutable data that will be
accessed from multiple threads.

When a class has invariants that involve more than
one state variable, there is an additional requirement: each variable participating in the invariant must be guarded by the same lock. This allows you to access or update them in a single atomic operation, preserving the invariant

## Goetz - Chapter 16
### Section 16.1.3 - Java memory model
The JMM is defined as actions, which includes read, writes, locks, unlocks etc. The JMM defines a partial ordering called *Happens-before* on all actions within the program. There must be a happens-before relation between thread A and B. Without such a relation, the JVM is free to reorder them as it pleases.

The rules for happens-before:
- Program order rule (each action in a thread happens before every action in that thread that comes later in the program order)
- Monitor lock rule (an unlock on a monitor lock happens before every subsequent lock on that same monitor lock)
- Volatile variable rule (A write to a volatile field happens before subsequent reads of same field)
- Thread start rule (a call to Thread.start happens before every action in the thread started)
- Thread termination rule (Any action in a thread happens-before any other thread detects that thread has terminated, either by successfully return from Thredd.join or by Thread.isAlive returning false)
- Interruption rule (A thread calling interrupt on another thread happens-before the interrupted thread detects the interrupt (either by having InterruptedException thrown, or invoking isInterrupted or interrupted))
- Finalizer rule (The end of a constructor for an object happens before the start of the finalizer for that object)
- Transitivity (if a happens before b, and b happens before c, then a happens before c)

## Herlihy - Chapter 1
### Section 1.2.1
**Coordination protocol** -> mutually compatible procedures for deciding what to do

**mutual exclusion** -> 2 pets cannot be in the yard at the same time

**deadlock freedom** -> liveness property... will not encounter deadlock

**starvation freedom** -> (lockout-freedom), will it eventually succeed, and not starve

**waiting** - > waiting for access to something that is not properly used

### Section 1.2.2
Two kinds of communication occur naturally in concurrent systems:
- Transient (requires both parties to participate at the same time
- Persistent (allows for participating at different times)

Mututal exclusion requires persistent communication.

In modern operating systems, one common way for one thread to get the attention
of another is to send it an interrupt. More precisely, thread A interrupts thread B by setting a bit at a location periodically checked by B. Sooner or later, B notices the bit has been set and reacts. After reacting, B typically resets the bit (A cannot reset the bit). Even though interrupts cannot solve the mutual exclusion problem, they can still be very useful. For example, interrupt communication is the basis of the Java language’s wait() and notifyAll() calls.


## Herlihy - Chapter 2
### Section 2.1 - Time and events
Threads share a common time (not necesarrily a common clock). A thread is a state machine and its state transitions are called events. Events are instantaneous and occur in a single instant of time. Distinct events occur at distinct times. A thread a produces a sequence of events: a0,a1.... One event precedes another (a0 -> a1) if it occurs at an earlier time.

Let a0 and a1 be events such that a0 → a1 . An interval (a0 , a1 ) is the duration between a0 and a1 . Interval IA = (a0 , a1 ) precedes IB = (b0 , b1 ), written IA → IB ,if a1 → b0 (that is, if the final event of IA precedes the starting event of IB ). The → relation is a partial order on intervals. Intervals that are unrelated by → are said to be concurrent. We also say that an event a precedes an interval I = (b0 , b1 ), written a → I , if a → b0 , and that I precedes a, written I → a, if b1 → a.

### Section 2.2 - Critical Sections
The actions we want to ensure are atomic can be put into a critical section. Often denoted by a lock (locking, performing the critical section, unlocking). A lock can be held, busy or free.

### Section 2.3 - Two-thread solution
LockOne class:
We keep a boolean variable for each thread to indicate whether or not they have aquired the lock.

LockTwo class:
A single variable that indicates which thread has aquired the lock

The Peterson lock:
Starvation-free lock. Combines the two mentioned methods.

### Section 2.4 - Notes on deadlock
deadlock can arise in programs that use multiple Peterson locks (or
any other lock implementation). For example, suppose that threads A and B share
locks 0 and 1 , and that A acquires 0 and B acquires 1 . If A then tries to acquire 1 and B tries to acquire 0 , the threads deadlock because each one waits for the other to release its lock.

This narrower notion of deadlock is distinguished from livelock, in which two
or more threads actively prevent each other from making progress by taking steps that subvert steps taken by other threads. When the system is livelocked rather than deadlocked, there is some way to schedule the threads so that the system can make progress (but also some way to schedule them so that there is no progress)

### Section 2.6 - Fairness
Starvation-freedom ensures that everyone will enter the critical section, but makes no promises to how long it takes before.Ideally (and very informally), if A calls lock() before B, then A should enter
the critical section before B. That is, the lock should be “first-come-first-served.” However, with the tools we have introduced so far, we cannot determine which thread called lock() first.

To define fairness, we split the lock() method into a doorway section and a waiting section, where the doorway section always completes in a bounded number of steps (the waiting section may take an unbounded number of steps). That is, there is a fixed limit on the number of steps a thread may take after invoking lock() before it completes the doorway section.

A section of code that is guaranteed to complete in a bounded number of steps
is said to be bounded wait-free. A lock is first-come-first-served if its lock() method can be split into a bounded wait-free doorway section followed by a waiting section so that whenever thread A finishes its doorway before thread B starts its doorway, A cannot be overtaken by B.

## Herlihy - Chapter 7
### Section 7.1

## Concurrency PDF
Concurrency programming is a set of concepts and abstractions used for writing software with multiple independent streams of statements/instructions.

# Lecture 2

# Lecture 3

# Lecture 4

# Lecture 5

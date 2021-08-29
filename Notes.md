# Lecture 1
## Chapter 1
### Section 1.1 - Introduction
** Processes ** : Isolated, independently executing pro-
grams to which the operating system allocates resources such as memory, ﬁle
handles, and security credentials.

processes could communicate with one another through a variety of coarse-grained communication mechanisms: sockets, signal handlers, shared memory, semaphores, and ﬁles.

Motivation for executing programs simultaneously:
- ** Resource utilization ** (sometimes programs have to wait for resources, and this wait time can be efficient to let another program run meanwhile)
-  ** Fairness ** (Programs may have equal claims to the machines resources, so it is preferable to let them share, rather than letting one program run to completion)
- ** Convenience ** (Easier to write multiple programs that perform a single task, and let them communicate, rather than one program performing all the tasks)

** Threads ** were motivated by the same 3 factors. Threads allow multiple streams of program control flow to coexist within a process. They share process-wide resources, but each thread has its own program counter, stack and local variables. They are often called ** lightweight processes ** , and is more often used than processes in modern OS as units of scheduling. Since threads share variables, coordination is key.

### Section 1.2 - Benefits of threads

Benefits of threads:
- Easier writable and maintainable code (simpler code)
- Improving responsiveness (GUI)
- Improving speed (servers)
- Utilizing the modern CPU (not wasting resources)
- Simplicity of modeling (A thread does only one thing) -> Easier to test, maintain, understand, less error-prone
- No blocking for ex. multiple socket connections

### Section 1.3 - Risks of threads
** Thread safety ** -> lack of this can cause problems, ex. two threads accessing the same shared value and trying to increment it. This can produce different results on each run due to the scheduler, and is a ** Race condition ** . This simple example can be solved by making the method synchronized ('public synchronized int...').

** Liveness failure ** -> When a program enters a state where it is unable to make forward progress, ie. deadlock

** Performance hazards ** -> service time, responsiveness, throughput, resource consumption, or scalability. Threads carry runtime overhead. ** Context switches ** where the scheduler suspends a thread temporarily so another thread can run have significant cost: saving and restoring execution context, loss of locality, and CPU time spent scheduling threads instead of running them.


## Chapter 2
### Section 2.1
### Section 2.2
### Section 2.3
### Section 2.4
### Section 2.5

# Lecture 2

# Lecture 3

# Lecture 4

# Lecture 5

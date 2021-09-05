## Exercise 1.1

### 1
A couple of runs produces the following outputs:
Count is 18294107 and should be 20000000
Count is 19665577 and should be 20000000
Count is 19688226 and should be 20000000
Count is 19624850 and should be 20000000

### 2
When the count is lowered, i.e. to 100, we reduce the probability of race conditions happening. The lower amount of non-atomic operations (where the race condition can occur), and fewer possible interlevings, the lower amount of probability for this happening.

It is still not guaranteed that the output is 200 everytime, as we have not solved the problem of race conditions, we have simply reduced the likelyhood slightly.

### 3
This sbould not make any difference, as count++ is simply syntactic sugar, and is translated into exactly the same machine code as count = count + 1, which is still a non atomic operation.

### 4
We can ensure that a correct interleving always appears by locking the critical section. This ensures mutual exclusion, as no 2 threads can ever execute the non atomic operation at the same time.

## Exercise 1.2

### 1
See the Printer.java file

### 2
An interleving like this can occur when the scheduler lets thread B execute the print statement before thread A finishes the code execution. That is, thread B reaches the first print statement before thread A executes the sleep statement and second print function:

Thread A -> prints "-"
Thread B -> prints "-"
Thread A -> prints "|"
Thread B -> prints "|"

### 3
See the Printer.java file

We have implemented a renentrant lock, which always ensures the correct output. This is due to its ability to ensure mutual exlusion in the critical section where the threads execute the print statements.

## Exercise 1.3

### 1
See the CounterThreads2Covid.java file

### 2
The solution works by implementing a reentrant lock in the critical section of the code, which includes both the check on the counter as well as the increment operation. It is important that the check for the value of the counter is located within this critical section, as it could otherwise result in a race condition due to lack of atomicity. If both threads read the value of the counter at the same time, where it is 14999, they both assume it can be incremented, and the value will reach 15001, even though the increment operation in itself is locked.

## Exercise 1.4
### 1
**Resource utilization** defined by goetz can be somewhat compared to **exploitation** from the notes. Both are aimed at utilizing the resources availbale and ensuring as high performance as possible. This is especially prevelent in async operations, where the program waits for some external answer/input/output. In this wait time, the resources can be utilized by another thread. Furthermore, resource utilization also sshared similarities with **Inherent** due to its emphasis on input/output where some wait time can appear. In this time, it is beneficial to run other tasks.

**Convenience** and **Hidden** also shared some similarities. Convenience is aimed at simplifying the structure of the code, which can also somewhat be seen in the hidden factor. Here, simply assuming that the programs run independently and not take the aspect of multithreading/concurrency into consideration also greatly simplifies the structure of the code. Furthermore, **Hidden** also has some relation to **Fairness** in the form of an assumption and dependency. Writing a *hidden* program (assuming sole ownership of resources) will only work if fairness exists, as the program would otherwise not run smoothly.

### 2

**Inherent**
- Some UI waits for a response from an API. Therefore a seperate UI thread exists from the thread calling the API, as the UI would otherwise freeze while waiting for the response.
- An API needs to access a database and retrieve information
- A program writes to a file

**Exploitation**
- Client/Server architecture where the server needs to handle multiple connected clients simultaneously
- Using both a mouse and keyboard simultaneously

**Hidden**
- Using multiple programs on the computer simultaneously without having to take resource sharing into consideration (spotify and firefox)
- Anything we do on the computer occurs simultaneously with loads of background processes (fan cooling, wifi connection, etc)



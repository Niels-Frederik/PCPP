## Exercise 1.1

### 1.1
A couple of runs produces the following outputs:
Count is 18294107 and should be 20000000
Count is 19665577 and should be 20000000
Count is 19688226 and should be 20000000
Count is 19624850 and should be 20000000

### 1.2
When the count is lowered, i.e. to 100, we reduce the probability of race conditions happening. The lower amount of non-atomic operations (where the race condition can occur), and fewer possible interlevings, the lower amount of probability for this happening.

It is still not guaranteed that the output is 200 everytime, as we have not solved the problem of race conditions, we have simply reduced the likelyhood slightly.

### 1.3
This sbould not make any difference, as count++ is simply syntactic sugar, and is translated into exactly the same machine code as count = count + 1, which is still a non atomic operation.

### 1.4
We can ensure that a correct interleving always appears by locking the critical section. This ensures mutual exclusion, as no 2 threads can ever execute the non atomic operation at the same time.

##Exercise 1.2

### 1.2



## Exercise 4.1

### 1.2
We can argue that our implementation is thread safe due to various reasons. First off, all of the fields in the bounded buffer class are declared as private and not escaped through any methods. They are therefore bound to the class and cannot be accessed from outside. Using a linked list as our data structure for this buffer imposes some problem. Even if we can ensure that there is only always x amount of elements in the linked list, the linked list itself is still not thread safe. Even though we allow x amount of producers, they can still not add elements to the linked list simultanously, as this could result in race conditions. Therefore, we not only need to ensure x amount of producers and consumers, we also still have to "lock" the linked list before adding or removing elements. We therefore chose to implement multiple semaphores. Two of the semaphores ensures that there is only x amount of elements adding or taking from the list, while two other semaphores ensures the locking of the list while adding or polling elements.

### 1.3
It could potentially be implemented with a cyclic barrier, but it would be quite a bit more cumbersome. One could potentially only allow a consumer to start once a producer has finished, but when the aspect of maxsize comes into the picture, it becomes more difficult.

## Exercise 4.2

### 1.1
See the Person class

### 1.2
Our Person class is thread safe due to the following reasons:
* Every field is private, primitive, immutable or final (or a combination) and therefore cannot be escaped through methods
* The constructor without parameters lock a static variable, and thereby ensures mutual exlusion for accessing the static field of nextId
* The constructor with a parameter also locks the static variable, and therefore ensures the same mutual exlusion as the other constructor
* The changeZipAndAddress method is synchronized

There can never be a reference to a partially created object because:
* All fields are final, volatile or static, thereby ensuring flushing and no reordering by the JIT compiler
* Both constructors are synchronized

### 1.3
We have created a very primitive test for this where 1000 threads are started, and each insert the id of the person they create into a concurrentHashMap. We can then assert that the size of the hashMap is 1000 after execution.

### 1.4
Absence of evidence is not a proof. Just because we have run the code and gotten the correct result, we can not base a conclusion only on this. We can however analyze the classes and reason for their thread safety

# 9.2

A observable is created with "A", "AB" and "ABC".
Then each of them are flatmapped to the method getLengthWithDelay, which waits a random amount of time before returning 
a new observable consisting of the length of the parameter parsed.
Afterwards the name of the thread executing this is printed.
The subscribeOn is used to determine which thread is used to execute the task.

Next up the subscribe is called on the new observables generated from the getLengthWithDelay. 
This prints the name of the threads and the length value from the observable.
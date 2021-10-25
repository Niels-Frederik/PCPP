#Exercise 7.1
### 1)
Increment & getAndClear are threadsafe because we only change the value of the element in counts if the value has not changed since we checked it last. That is, no other thread has accessed and changed that part of the data structure in the meantime.

getCount and getSpan can simply return the value as we do not attempt to make any changes to it

### 2)
Count CAS histogram  1         30642353,0 ns  117485,71         16
Count CAS histogram  2         20025410,4 ns  102198,27         16
Count CAS histogram  3         16759796,9 ns  495637,79         16
Count CAS histogram  4         16047285,0 ns  754121,16         32
Count CAS histogram  5         17394222,3 ns  999329,54         16
Count CAS histogram  6         17200957,7 ns  416409,65         16
Count CAS histogram  7         16574685,6 ns  371567,00         16
Count Lock histogram  1        30464986,8 ns  278873,14         16
Count Lock histogram  2        20865324,7 ns  110653,85         16
Count Lock histogram  3        18366412,5 ns  518087,92         16
Count Lock histogram  4        17336226,7 ns  311663,12         16
Count Lock histogram  5        18546174,9 ns  447063,78         16
Count Lock histogram  6        18540853,5 ns  688147,75         16
Count Lock histogram  7        18989283,9 ns  587921,31         16

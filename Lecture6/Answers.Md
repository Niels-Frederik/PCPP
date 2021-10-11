#Exercise 6.1

##Exercise 1)

##Exercise 2)
**Running with the min/max:**\
Transfer 4787 from 9 to 5\
Transfer 1355 from 7 to 4\
Transfer 4445 from 9 to 2\
Transfer 4870 from 0 to 1\
Transfer 1902 from 1 to 5\
Transfer 1498 from 0 to 1\
Transfer 4457 from 5 to 1\
Transfer 255 from 2 to 5\
Transfer 350 from 9 to 7\
Transfer 2009 from 6 to 4\
Transfer 2027 from 5 to 6\
Transfer 4444 from 8 to 6\
Transfer 4904 from 3 to 0\
Transfer 4461 from 4 to 7\
Transfer 4644 from 4 to 7\
Transfer 5075 from 6 to 8\
Transfer 3486 from 2 to 4\
Transfer 3012 from 4 to 2\
Transfer 843 from 2 to 4\
Transfer 3163 from 0 to 4\
Transfer 973 from 3 to 6\
Transfer 2511 from 2 to 9\
Transfer 2153 from 7 to 3\
Transfer 3428 from 5 to 3\
Transfer 1832 from 1 to 3\
Transfer 3190 from 2 to 0\
Transfer 2394 from 8 to 2\
Transfer 632 from 5 to 0\
Transfer 970 from 8 to 5\
Transfer 1653 from 7 to 8\
Transfer 1543 from 4 to 8\
Transfer 1182 from 2 to 6\
Transfer 3273 from 1 to 2\
Transfer 2381 from 9 to 1\
Transfer 3726 from 9 to 2\
Transfer 4848 from 4 to 8\
Transfer 2192 from 9 to 6\
Transfer 2847 from 7 to 3\
Transfer 2414 from 3 to 1\
Transfer 1633 from 0 to 3\
Transfer 4658 from 9 to 7\
Transfer 3948 from 4 to 7\
Transfer 281 from 6 to 4\
Transfer 3720 from 7 to 3\
Transfer 2023 from 5 to 6\
Transfer 4912 from 1 to 2\
Transfer 889 from 8 to 5\
Transfer 3781 from 0 to 3\
Transfer 3732 from 8 to 0\
Transfer 1835 from 1 to 6

**Running without the min/max:**\
We reach a deadlock


##Exercise 3)
See the ThreadsAccountExperimentsMany.java file

##Exercise 4)
See the ThreadsAccountExperimentsMany.java file

#Exercise 6.2

##Exercise 1)
* countSequential                10373874,1 ns  715770,46         32\
* countParallelN  1              12487659,7 ns 2607290,02         32\
* countParallelNLocal  1         10078938,0 ns  535238,60         32\
* countParallelN  2               6732416,6 ns  418419,51         64\
* countParallelNLocal  2          6977078,2 ns 1036340,52         64\
* countParallelN  3               6242185,4 ns  845649,07         64\
* countParallelNLocal  3          5491647,8 ns  355624,91         64\
* countParallelN  4               5284956,6 ns  273741,29         64\
* countParallelNLocal  4          4765294,5 ns  167574,39         64\
* countParallelN  5               5731465,6 ns  429800,27         64\
* countParallelNLocal  5          5510047,8 ns  666609,83         64\
* countParallelN  6               5527117,7 ns  210428,66         64\
* countParallelNLocal  6          5190993,9 ns  379712,38         64\
* countParallelN  7               6658339,2 ns  828251,57         64\
* countParallelNLocal  7          6906593,0 ns 1288305,48         64\
* countParallelN  8               7287019,4 ns 1107514,29         64\
* countParallelNLocal  8          6938347,0 ns  473519,07         64\
* countParallelN  9               6361329,2 ns  641596,38         64\
* countParallelNLocal  9          6700115,7 ns  877085,04         64\
* countParallelN 10               6722212,4 ns  592421,90         64\
* countParallelNLocal 10          6400537,4 ns 1139272,94         64\
* countParallelN 11               8200713,2 ns 1076513,84         32\
* countParallelNLocal 11          5858684,2 ns  719735,84         64\
* countParallelN 12               7227542,6 ns 1710255,35         32\
* countParallelNLocal 12          6717398,2 ns 2338867,39         64\
* countParallelN 13               7635666,4 ns 2144937,67         32\
* countParallelNLocal 13          5401680,0 ns  499544,51         64\
* countParallelN 14               5611006,6 ns  190470,89         64\
* countParallelNLocal 14          5903696,6 ns  730252,76         64\
* countParallelN 15               5759339,1 ns  210674,53         64\
* countParallelNLocal 15          5274874,7 ns  112102,16         64

These results seems quite plausible. In almost all cases the countParallelNLocal is fastest. 
The ultimately fastest one being the one using 4 threads, which is also the amount of threads on this computer. 

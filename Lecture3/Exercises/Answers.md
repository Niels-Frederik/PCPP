# Exercise 3.1

## Linux Computer
* OS:   Linux; 5.13.13-arch1-1; amd64
* JVM:  OpenJDK; 16.0.2
* CPU:  Intel i5-7360U dual-core @ 3.600GHz

**Mark 1**\
0.005 s     0.2ns

**Mark 2**\
24.8 ns

**Mark 3**\
25.2 ns\
24.1 ns\
24.1 ns\
24.0 ns\
24.1 ns\
24.3 ns\
24.1 ns\
24.7 ns\
24.3 ns\
24.2 ns

**Mark 4**\
25.1 ns +/-  0.575

**Mark 5**\
360.1 ns +/-   499.95          2\
239.8 ns +/-    96.45          4\
230.7 ns +/-   116.42          8\
246.0 ns +/-   194.18         16\
195.5 ns +/-    71.51         32\
58.7 ns +/-     9.56         64\
83.5 ns +/-    91.94        128\
38.8 ns +/-     6.43        256\
52.2 ns +/-     2.96        512\
36.6 ns +/-     6.15       1024\
39.2 ns +/-     7.96       2048\
49.3 ns +/-     7.02       4096\
32.6 ns +/-     1.06       8192\
32.0 ns +/-     0.42      16384\
30.8 ns +/-     0.96      32768\
31.8 ns +/-     1.40      65536\
28.6 ns +/-     1.89     131072\
25.5 ns +/-     0.22     262144\
25.4 ns +/-     0.08     524288\
25.5 ns +/-     0.04    1048576\
25.4 ns +/-     0.04    2097152\
25.6 ns +/-     0.26    4194304\
25.2 ns +/-     0.31    8388608\
24.9 ns +/-     0.45   16777216


**Mark 6**\
multiply                            623.4 ns     904.36          2\
multiply                            507.4 ns     142.53          4\
multiply                            439.0 ns     115.97          8\
multiply                            602.8 ns     617.46         16\
multiply                            275.6 ns     147.71         32\
multiply                             76.2 ns      19.99         64\
multiply                             93.9 ns      91.50        128\
multiply                             79.5 ns      22.79        256\
multiply                             70.3 ns      12.99        512\
multiply                             42.5 ns       3.12       1024\
multiply                             55.2 ns       8.20       2048\
multiply                             58.2 ns       2.89       4096\
multiply                             35.0 ns       9.23       8192\
multiply                             31.1 ns       3.16      16384\
multiply                             29.5 ns       0.75      32768\
multiply                             29.3 ns       3.70      65536\
multiply                             26.1 ns       0.29     131072\
multiply                             25.5 ns       0.05     262144\
multiply                             25.5 ns       0.03     524288\
multiply                             25.4 ns       0.02    1048576\
multiply                             26.7 ns       2.18    2097152\
multiply                             25.6 ns       0.27    4194304\
multiply                             25.5 ns       0.17    8388608\
multiply                             25.3 ns       0.36   16777216


**Mark 7**\
pow                                  20.1 ns       0.25   16777216\
exp                                  22.2 ns       0.07   16777216\
log                                  12.6 ns       0.17   33554432\
sin                                  14.8 ns       0.09   33554432\
cos                                  14.8 ns       0.08   16777216\
tan                                  20.4 ns       0.05   16777216\
asin                                 79.4 ns       0.33    4194304\
acos                                 80.2 ns       6.70    4194304\
atan                                 21.2 ns       0.56   16777216

## Mac OS Computer
* OS:   Mac OS X; 11.3.1; x86\_64
* JVM:  Homebrew; 16.0.2
* CPU:  2 GHz Quad-Core Intel Core i5

**Mark1**\
 0.009 s     0.4ns

**Mark2**\
  25.8 ns

**Mark3**\
  25.8 ns\
  26.1 ns\
  26.2 ns\
  26.2 ns\
  26.1 ns\
  26.0 ns\
  25.9 ns\
  25.8 ns\
  25.8 ns\
  25.9 ns

**Mark4**\
  26.2 ns +/-  0.126

**Mark5**\
 479.9 ns +/-   796.14          2\
 192.2 ns +/-   121.38          4\
 169.1 ns +/-    81.93          8\
 132.0 ns +/-    39.21         16\
 164.5 ns +/-   111.87         32\
 110.9 ns +/-    82.59         64\
  97.8 ns +/-   104.24        128\
  38.6 ns +/-     3.01        256\
  41.7 ns +/-    11.75        512\
  37.7 ns +/-     0.96       1024\
  42.5 ns +/-    10.01       2048\
  34.4 ns +/-     6.10       4096\
  30.8 ns +/-     6.21       8192\
  27.6 ns +/-     0.86      16384\
  27.6 ns +/-     1.52      32768\
  28.5 ns +/-     1.25      65536\
  27.2 ns +/-     1.66     131072\
  26.0 ns +/-     0.36     262144\
  25.9 ns +/-     0.26     524288\
  26.4 ns +/-     0.63    1048576\
  26.9 ns +/-     0.52    2097152\
  26.4 ns +/-     0.22    4194304\
  26.1 ns +/-     0.50    8388608\
  25.6 ns +/-     0.38   16777216

**Mark6**\
multiply                            592.5 ns    1044.02         2\
multiply                            312.6 ns      93.15          4\
multiply                            243.3 ns      73.04          8\
multiply                            222.2 ns      35.06         16\
multiply                            352.9 ns     129.53         32\
multiply                            435.9 ns     427.43         64\
multiply                             82.3 ns     103.36        128\
multiply                             55.2 ns      17.61        256\
multiply                             53.6 ns      25.39        512\
multiply                             29.6 ns       1.26       1024\
multiply                             29.2 ns       0.24       2048\
multiply                             29.6 ns       1.42       4096\
multiply                             31.7 ns       7.84       8192\
multiply                             27.2 ns       0.40      16384\
multiply                             26.5 ns       0.86      32768\
multiply                             26.9 ns       0.90      65536\
multiply                             26.0 ns       0.58     131072\
multiply                             26.3 ns       0.82     262144\
multiply                             26.7 ns       0.50     524288\
multiply                             26.5 ns       0.17    1048576\
multiply                             26.3 ns       0.29    2097152\
multiply                             26.0 ns       0.24    4194304\
multiply                             26.0 ns       0.18    8388608\
multiply                             26.2 ns       0.19   16777216

**MathMarks**\
pow                                  17.5 ns       0.03   16777216\
exp                                  23.2 ns       0.06   16777216\
log                                  10.5 ns       0.29   33554432\
sin                                  13.1 ns       0.08   33554432\
cos                                  13.1 ns       0.03   33554432\
tan                                  19.9 ns       0.19   16777216\
asin                                 70.8 ns       0.35    4194304\
acos                                 71.1 ns       0.62    4194304\
atan                                 16.1 ns       0.13   16777216


## Reflections on results
Both the computers are very consistent in all the benchmark results, and no significant deviations appear. Also, both computers are quite a bit newer and faster than the one used in the original benchmarking note, as to why our benchmarking times are similarly faster.


# Exercise 3.2
Running the TestTimeThreads.java file with mark 6 benchmarking was overall quite consistent. We saw the most deviations when working with threads, but for every case, the time and deviation decreases as more runs are executed.

* OS:   Linux; 5.13.13-arch1-1; amd64
* JVM:  OpenJDK; 16.0.2
* CPU:  Intel i5-7360U dual-core @ 3.600GHz

Mark 7 measurements\
hashCode()                            2.5 ns       0.00  134217728\
Point creation                       46.0 ns       0.08    8388608\
Thread's work                      5033.5 ns       3.61      65536\
Thread create                       714.1 ns      20.01     524288\
Thread create start               47382.5 ns     643.14       8192\
Thread create start join          65170.1 ns    1723.31       4096\
ai value = 1556420000\
Uncontended lock                     18.5 ns       0.59   16777216

Once again, the results seems quite plausible. It is however surprising to see how expensive working with threads are, as also mentioned in the lecture. This also shows when one compares *Thread create start join* to adding *Thread create start* and *Thread's work* together. One would think adding these together would be almost similar to the *Thread create start join*, as they do almost the same thing, except for the *join* statement. This once agains shows that the *join* perhaps takes up to ~10000 ns, which seems quite expensive.

# Exercise 3.3

* OS:   Linux; 5.13.13-arch1-1; amd64
* JVM:  OpenJDK; 16.0.2
* CPU:  Intel i5-7360U dual-core @ 3.600GHz

countSequential                 9698759.1 ns   14116.04         32\
countParallelN       1         10002328.4 ns   28952.04         32\
countParallelN       2          6380891.9 ns   77744.60         64\
countParallelN       3          5805581.3 ns  148521.31         64\
countParallelN       4          4798510.4 ns   80615.74         64\
countParallelN       5          6021166.0 ns   83174.70         64\
countParallelN       6          5638661.3 ns  111762.78         64\
countParallelN       7          5527542.9 ns  109499.94         64\
countParallelN       8          5144621.3 ns  117388.64         64\
countParallelN       9          5578986.5 ns   87590.56         64\
countParallelN      10          5439609.9 ns   72558.39         64\
countParallelN      11          5430008.3 ns   96336.00         64\
countParallelN      12          5233352.8 ns   82519.96         64\
countParallelN      13          5538459.4 ns   72783.33         64\
countParallelN      14          5382282.9 ns   96712.97         64\
countParallelN      15          5445761.9 ns   60628.32         64\
countParallelN      16          5325259.4 ns  112031.88         64\
countParallelN      17          5508092.7 ns   91982.85         64\
countParallelN      18          5455139.6 ns   98405.80         64\
countParallelN      19          5488704.5 ns   76864.11         64\
countParallelN      20          5458026.5 ns  108049.78         64\
countParallelN      21          5578868.9 ns   86111.49         64\
countParallelN      22          5567245.5 ns   98915.52         64\
countParallelN      23          5598749.1 ns   88741.85         64\
countParallelN      24          5564911.2 ns  108190.61         64\
countParallelN      25          5659818.2 ns   77267.22         64\
countParallelN      26          5675699.5 ns   52022.09         64\
countParallelN      27          5732960.9 ns   57269.96         64\
countParallelN      28          5735107.6 ns   77598.28         64\
countParallelN      29          5845223.8 ns  118591.86         64\
countParallelN      30          5892271.4 ns  143233.33         64\
countParallelN      31          5842189.6 ns   80175.11         64\
countParallelN      32          5856378.5 ns   93126.39         64

These results are not too surprising. We see that the sequential run compared to a single thread is faster. This is obvious due to the overhead of starting a thread, and no benefit of only running a single thread, which is basically still sequential. There is an obvious relation between the number of cores on the computer, and the amount of threads resulting in the fastest run. This computer has 2 cores, whereas each core has 2 threads, resulting in a total of 4 threads. We clearly see that this is also the amount of threads resulting in the fastest run. Similarly, we ran the benchmarking on a computer with 4 cores (8 threads), which showed the run with 8 threads being the fastest.\

**Now run with Atomic instead of LongCounter:**\

countSequential                 9710341.4 ns    5500.31         32\
countParallelN       1          9790495.7 ns   21875.12         32\
countParallelN       2          6108850.7 ns   32501.41         64\
countParallelN       3          5640938.8 ns   55569.32         64\
countParallelN       4          4771347.2 ns   66433.57         64\
countParallelN       5          5991824.7 ns  173886.14         64\
countParallelN       6          5547773.7 ns   83674.88         64\
countParallelN       7          5404716.2 ns   99552.45         64\
countParallelN       8          5117197.4 ns   87545.55         64\
countParallelN       9          5518046.6 ns   86576.02         64\
countParallelN      10          5346832.9 ns   94617.74         64\
countParallelN      11          5409551.9 ns  100128.34         64\
countParallelN      12          5184759.4 ns  141011.90         64\
countParallelN      13          5520124.7 ns  121888.19         64\
countParallelN      14          5337653.0 ns   64777.42         64\
countParallelN      15          5411667.9 ns   68977.52         64\
countParallelN      16          5297900.8 ns   99985.11         64\
countParallelN      17          5403663.7 ns   92797.80         64\
countParallelN      18          5374412.0 ns   72491.51         64\
countParallelN      19          5427830.2 ns  103381.29         64\
countParallelN      20          5413129.6 ns   94061.47         64\
countParallelN      21          5537246.0 ns   78252.20         64\
countParallelN      22          5485207.0 ns   96755.28         64\
countParallelN      23          5522314.4 ns   68114.00         64\
countParallelN      24          5503594.3 ns   78977.43         64\
countParallelN      25          5584465.6 ns   65579.98         64\
countParallelN      26          5623278.7 ns   85105.81         64\
countParallelN      27          5674698.8 ns   95675.05         64\
countParallelN      28          5676935.1 ns   82274.18         64\
countParallelN      29          5798469.1 ns  216589.48         64\
countParallelN      30          5768937.6 ns   78781.82         64\
countParallelN      31          5792538.9 ns   63395.71         64\
countParallelN      32          5809054.7 ns   83929.24         64

In general, the results are quite similar, but we see a slight improvement when using the Atomic variable instead of the LongCounter. One should definately use the built in functions when these are available, as they are probably faster than one could implement it themselves. They are made by java developers for a reason.\

# Exercise 3.4

* OS:   Linux; 5.13.13-arch1-1; amd64
* JVM:  N/A; 16.0.2
* CPU:  null; 4 "cores"
* Date: 2021-09-13T14:38:06+0200

volatile int                          7.0 ns       0.00   67108864\
int                                   1.4 ns       0.00  268435456

We clearly see that using the normal int is way faster than the volatile int. The result for a normal int seems very realistic, as these types of operations normally lie within 1-2 ns. It also makes senste that the volatile int is slower, as the variable is flushed to main memory on every access. This is compared to a normal variable which can be kept in the CPU cache for faster access. The difference in time seems very plausible.

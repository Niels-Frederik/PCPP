# Exercise 3.1

## Linux Computer
* OS:   Linux; 5.13.13-arch1-1; amd64
* JVM:  OpenJDK; 16.0.2
* CPU:  Intel i5-7360U (4) @ 3.600GHz

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

**Mark7**\
multiply                             26.2 ns       0.11   16777216

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

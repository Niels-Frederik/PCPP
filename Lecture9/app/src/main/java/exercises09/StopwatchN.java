package exercises09;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class StopwatchN {

    public static void main(String[] args) { new exercises09.StopwatchN(9); }

    public StopwatchN(int n) {
        final JFrame f= new JFrame("Stopwatch");
        //final stopwatchUI myUI1= new stopwatchUI(0, f);
        final List<stopwatchUI> stopwatches = new ArrayList<stopwatchUI>();
        for(int i = 0; i < n; i++){ stopwatches.add(new stopwatchUI(220*i, f));}
        f.setBounds(0, 0, 220*n, 220);
        f.setLayout(null);
        f.setVisible(true);


        // Background Thread simulating a clock ticking every 1 seconde
        new Thread() {
            private int seconds= 0;

            @Override
            public void run() {
                try {
                    while ( true ) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        for (stopwatchUI watch: stopwatches) { watch.updateTime(); }
                    }
                } catch (InterruptedException e) { System.out.println(e.toString());   }
            }
        }.start();
    }
}

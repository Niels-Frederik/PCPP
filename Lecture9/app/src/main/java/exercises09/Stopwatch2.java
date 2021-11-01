package exercises09;
import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Stopwatch2 {

    public static void main(String[] args) { new exercises09.Stopwatch2(); }

    public Stopwatch2() {
        final JFrame f= new JFrame("Stopwatch");
        final stopwatchUI myUI1= new stopwatchUI(0, f);
        final stopwatchUI myUI2= new stopwatchUI(220, f);
        f.setBounds(0, 0, 440, 220);
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
                        myUI1.updateTime();
                        myUI2.updateTime();
                    }
                } catch (java.lang.InterruptedException e) { System.out.println(e.toString());   }
            }
        }.start();
    }
}

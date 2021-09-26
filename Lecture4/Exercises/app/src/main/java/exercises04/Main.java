package exercises04;

import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main (String[] args)
    {

        var set = new ConcurrentHashMap<Integer, Integer>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                Person p = new Person();
                set.put((int) p.getId(), 1);
            }).start();
        }

        try {
            Thread.sleep(10000);
            System.out.println(set.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

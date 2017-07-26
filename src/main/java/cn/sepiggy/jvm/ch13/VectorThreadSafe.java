package cn.sepiggy.jvm.ch13;

import java.util.Vector;

// p388
public class VectorThreadSafe {

    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 1000; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        try {
                            vector.remove(i);
                        } catch (Exception e) {
                            System.out.println(e);
                            return;
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        try {
                            System.out.println(vector.get(i));
                        } catch (Exception e) {
                            System.out.println(e);
                            return;
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() > 100) ;

        }
    }


}

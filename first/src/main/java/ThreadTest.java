/**
 * Created by Sergey.Tsaturov on 29.06.2017.
 */
public class ThreadTest implements Runnable {
    final static private Object shared=new Object();
    private int type;
    public ThreadTest(int i) {
        type=i;
    }
    public void run() {
        if (type==1 || type==2) {
            synchronized (shared) {
                try {
                    shared.wait();
                } catch (InterruptedException e) {}
                System.out.println("Thread "+type+
                        " after wait()");
            }
        } else {
            synchronized (shared) {
                shared.notifyAll();
                System.out.println("Thread "+type+
                        " after notifyAll()");
            }
        }
    }
    public static void main(String s[]) {
        ThreadTest w1 = new ThreadTest(1);
        new Thread(w1).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        ThreadTest w2 = new ThreadTest(2);
        new Thread(w2).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        ThreadTest w3 = new ThreadTest(3);
        new Thread(w3).start();
    }
}
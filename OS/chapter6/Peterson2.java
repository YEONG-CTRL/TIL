package OS.chapter6;

import java.util.concurrent.atomic.AtomicBoolean;

public class Peterson2 {
    static int count = 0;
    static int turn = 0;
    static AtomicBoolean[] flag; // AtomicBoolean 배열인 flag
    static { // static생성자: 클래스가 로드될때 초기화
        flag = new AtomicBoolean[2];
        for (int i = 0; i < flag.length; i++)
            flag[i] = new AtomicBoolean();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Producer());
        Thread t2 = new Thread(new Consumer());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Peterson2.count);
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 100000; k++) {
                /* entry section */
                flag[0].set(true);
                turn = 1;
                while (flag[1].get() && turn == 1) // flag[1]은 atomic variable
                    // 얘를 get,set할때는 절대로 context switch가 일어나지 않는다
                    ;
                /* critical section */
                count++;
                /* exit section */
                flag[0].set(false);
                /* remainder section */
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 100000; k++) {
                /* entry section */
                flag[1].set(true);
                turn = 0;
                while (flag[0].get() && turn == 0) // 얘도 마찬가지
                    ;
                /* critical section */
                count--;
                /* exit section */
                flag[1].set(false);
                /* remainder section */
            }
        }
    }
}
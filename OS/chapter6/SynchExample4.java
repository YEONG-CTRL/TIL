package OS.chapter6;

public class SynchExample4 {
    static class Counter {
        public static int count = 0;

        public void increment() {
            synchronized (this) { // 자기 객체 instance의 모니터락을 획득.
                Counter.count++; // 5개의 쓰레드가 5개의 counter를 갖고 있다보니
                // 각각의 this가 다름, 따라서 자기혼자서들만 동기화되기 때문에
                // 제대로된 결과가 안나옴
                // 5번예제에서 이를 보완
            }
        }
    }

    static class MyRunnable implements Runnable {
        Counter counter;

        public MyRunnable(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                counter.increment();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyRunnable(new Counter())); // Counter 인스턴스를 5개 생성
            // 쓰레드가 갖고 있는 Runnable의 counter 객체가 5개 생긴것
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++)
            threads[i].join();
        System.out.println("counter = " + Counter.count);
    }
}
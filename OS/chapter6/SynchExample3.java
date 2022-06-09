package OS.chapter6;

public class SynchExample3 {
    static class Counter {
        private static Object object = new Object();
        public static int count = 0;

        public static void increment() {
            synchronized (object) { // 특정 객체를 가지고 모니터 락을 획득하겠다
                count++; // 메소드 안에서, 이 라인만 synchornized를 써줌
            }
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                Counter.increment();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[5]; // 쓰레드 다섯개
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyRunnable()); // MyRunnable 쓰레드를 생성하여
            threads[i].start(); // run을 concurrent하게 실행
        }
        for (int i = 0; i < threads.length; i++)
            threads[i].join(); // 메인 쓰레드 대기
        System.out.println("counter = " + Counter.count);
    }
}
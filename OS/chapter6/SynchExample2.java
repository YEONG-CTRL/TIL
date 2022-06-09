package OS.chapter6;

public class SynchExample2 {
    static class Counter {
        public static int count = 0;

        synchronized public static void increment() { // Critical Section // 메소드 전체를 묶어준 경우
            count++; // 메소드 말고 특정 블락만 임계영역으로 잡아주는 것은 3번예제
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
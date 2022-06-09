package OS.chapter6;

public class SynchExample5 {
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
        Counter counter = new Counter(); // counter인스턴스 하나 만들고
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyRunnable(counter)); // 그것을 파라미터로 넘겨주면
            // 쓰레드는 다섯개지만, 쓰레드가 각각의 counter를 갖는게 아니라, 같은 값을 가짐
            // 다섯개의 쓰레드가 같은 static variable인 count에 접근함
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++)
            threads[i].join();
        System.out.println("counter = " + Counter.count);
    }
}
package OS.chapter7;

public class BoundedBuffer {
    public static void main(String[] args) {
        CashBox cashBox = new CashBox(1);
        Thread[] producers = new Thread[1];
        Thread[] consumers = new Thread[5];
        // Create threads of producers
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(new ProdRunner(cashBox));
            producers[i].start();
        }
        // Create threads of consumers
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new ConsRunner(cashBox));
            consumers[i].start();
        }
    }
}

class ProdRunner implements Runnable {
    CashBox cashBox; // 버퍼

    public ProdRunner(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 500));
                int money = ((int) (1 + Math.random() * 9)) * 10000;
                cashBox.give(money); // 돈 넣음
            }
        } catch (InterruptedException e) {
        }
    }
}

class ConsRunner implements Runnable {
    CashBox cashBox;

    public ConsRunner(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 500));
                int money = cashBox.take(); // 돈가져감
            }
        } catch (InterruptedException e) {
        }
    }
}

class CashBox {
    private int[] buffer;// 버퍼
    private int count, in, out;

    public CashBox(int bufferSize) {
        buffer = new int[bufferSize];
        count = in = out = 0;
    }

    synchronized public void give(int money) {
        while (count == buffer.length) {
            try {
                wait(); // wait호출시 쓰레드가 모니터락을 획득할때까지 엔트리큐에 대기
            } catch (InterruptedException e) {
            }
        }

        // CS
        buffer[in] = money;
        in = (in + 1) % buffer.length;
        count++;
        System.out.printf("여기있다, 용돈: %d원\n", money);
        notify(); // 끝났다고 signal
    }
    // 이 둘이 CashBox의 인스턴스에 대해 동기화돼야 함

    synchronized public int take() throws InterruptedException {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        int money = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        System.out.printf("고마워유, 용돈: %d원\n", money);
        notify();
        return money;
    }
}

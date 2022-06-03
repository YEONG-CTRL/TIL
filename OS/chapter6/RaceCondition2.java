package OS.chapter6;

public class RaceCondition2 {
    public static void main(String[] args) throws Exception {
        RunnableTwo run1 = new RunnableTwo();
        RunnableTwo run2 = new RunnableTwo();
        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Result: " + RunnableTwo.count);
    }
}

class RunnableTwo implements Runnable {
    static int count = 0;
    // static으로 선언함으로써, run1이 가르키는 변수와 run2가 가르키는 객체의 참조변수가, count라는 "클래스 변수"를 공유함

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++)
            count++;
    }
}
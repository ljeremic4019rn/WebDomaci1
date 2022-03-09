import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class GlobalHolder {
    public static volatile CyclicBarrier assistantBarrier = new CyclicBarrier(1);
    public static volatile Semaphore assistantSemaphore = new Semaphore(1);
    public static volatile CyclicBarrier professorBarrier = new CyclicBarrier(2);
    public static volatile Semaphore professorSemaphore = new Semaphore(2);

    public static int totalMark;
    public static long startTime;
    public static final String markLock = "markLock";


    public static class markInfoHolder {
         long start;
         int work;
         int mark;

        public markInfoHolder(long start, int work, int mark) {
            this.start = start;
            this.work = work;
            this.mark = mark;
        }
    }

    long start = 0;
    int work = 0;
    int mark = 0;

    public markInfoHolder getMark() throws InterruptedException {
        Random tempo = new Random();
        start = System.currentTimeMillis() - start;
        work = (int) (tempo.nextDouble(0.5,1.0) * 1000);
        Thread.sleep(work) ;
        mark = new Random().nextInt(6) + 5;
        synchronized (markLock) {
            totalMark += mark;
        }
        return new markInfoHolder(start, work, mark);
    }
}

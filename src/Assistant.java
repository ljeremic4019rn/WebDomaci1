import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Assistant implements Runnable{

    public String treadName;
    public int arrivalTime;

    Assistant(String name, int arrival){
        treadName = name;
        arrivalTime = arrival;
    }

    @Override
    public void run() {
        long startTime = 0;
        int work = 0;
        int mark = 0;

        try {
            Student.assistantBarrier.await();
            Random tempo = new Random();
            
            startTime = System.currentTimeMillis() - Main.startTime;
            work = (int) (tempo.nextDouble(0.5,1.0) * 1000);
            Thread.sleep(work) ;
            
            mark = new Random().nextInt(6) + 5;
            synchronized (Main.markLock) {
                Main.totalMark += mark;
            }
            System.out.println("Thread: " + treadName + " Arrival: " + arrivalTime + " Asistent: " + Thread.currentThread().getName() +
                    " TTC: " + work + " : " + startTime + " Score: " + mark);

        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Stopped: " + treadName + " Arrival: " + arrivalTime + " Asistent: " + Thread.currentThread().getName() +
                    " TTC: " + work + ": " + startTime + " Score: " + mark);
        }
    }
}

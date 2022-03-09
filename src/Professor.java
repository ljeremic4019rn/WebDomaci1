import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Professor implements Runnable{

    public String treadName;
    public int arrivalTime;

    Professor(String name, int arrival){
        treadName = name;
        arrivalTime = arrival;
    }

    @Override
    public void run() {
        GlobalHolder.markInfoHolder markInfoHolder;

        try {
            Main.globalHolder.professorBarrier.await();
            markInfoHolder = Main.globalHolder.getMark();

            System.out.println("Thread: " + treadName + " Arrival: " + arrivalTime + " Prof: " + Thread.currentThread().getName() +
                    " TTC: " + markInfoHolder.work + ": " + markInfoHolder.start + " Score: " + markInfoHolder.mark);

        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Stopped: " + treadName + " Arrival: " + arrivalTime + " Prof: " + Thread.currentThread().getName());
        }
    }
}

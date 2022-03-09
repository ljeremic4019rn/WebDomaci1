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
        GlobalHolder.markInfoHolder markInfoHolder;

        try {
            Main.globalHolder.assistantBarrier.await();
            markInfoHolder = Main.globalHolder.getMark();

            System.out.println("Thread: " + treadName + " Arrival: " + arrivalTime + " Asist: " + Thread.currentThread().getName() +
                    " TTC: " + markInfoHolder.work + " : " + markInfoHolder.start + " Score: " + markInfoHolder.mark);
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Stopped: " + treadName + " Arrival: " + arrivalTime + " Asist: " + Thread.currentThread().getName());
        }
    }
}

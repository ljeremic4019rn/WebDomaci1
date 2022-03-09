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
        long startTime = 0;
        int work = 0;
        int mark = 0;

        try {
            Student.professorBarrier.await();//cekaj da se oslobodi
            Random tempo = new Random();

            startTime = System.currentTimeMillis() - Main.startTime;//granica za trajanje
            work = (int) (tempo.nextDouble(0.5,1.0) * 1000);//koliko ce dugo trajati akcija
            Thread.sleep(work);

            mark = new Random().nextInt(6) + 5;
            synchronized (Main.markLock) {//dodamo ocenu u globalnu
                Main.totalMark += mark;
            }
            System.out.println("Thread: " + treadName + " Arrival: " + arrivalTime + " Prof: " + Thread.currentThread().getName() +
                    " TTC: " + work + ": " + startTime + " Score: " + mark);

        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("PREKINUT: " + treadName + " Arrival: " + arrivalTime + " Prof: " + Thread.currentThread().getName() +
                    " TTC: " + work + ": " + startTime + " Score: " + mark);
        }
    }
}

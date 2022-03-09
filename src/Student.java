import java.sql.Time;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Student implements Runnable {

    @Override
    public void run() {
        Thread professorThread = null;
        Thread assistantThread = null;

        Random random = new Random();
        int arrivalTime = (int) (random.nextDouble() * 1000);

        //Thread.sleep(arrivalTime);//student dolazi u vreme na intervalu 0-1

        try {
            Thread arrive = new Thread(new Timer(arrivalTime));
            arrive.start();
            arrive.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (random.nextInt(100) % 2 == 1) {//50% sansa da ode kod jednog ili drugog
            try {
                Main.globalHolder.assistantSemaphore.acquire();
                assistantThread = new Thread(new Assistant(Thread.currentThread().getName(), arrivalTime));
                assistantThread.start();
                assistantThread.join();
                Main.globalHolder.assistantSemaphore.release();
            }catch (InterruptedException e){
                if(assistantThread != null) assistantThread.interrupt();
                System.out.println("Student did not make it in time");
            }
        } else {
            try {
                Main.globalHolder.professorSemaphore.acquire();
                professorThread = new Thread(new Professor(Thread.currentThread().getName(), arrivalTime));
                professorThread.start();
                professorThread.join();
                Main.globalHolder.professorSemaphore.release();
            }catch (InterruptedException e){
                if(professorThread != null) professorThread.interrupt();
                System.out.println("Student did not make it in time");
            }
        }
    }
}

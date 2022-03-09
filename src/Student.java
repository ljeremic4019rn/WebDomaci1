import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Student implements Runnable {

    public static volatile CyclicBarrier assistantBarrier = new CyclicBarrier(1);
    public static volatile Semaphore assistantSemaphore = new Semaphore(1);

    public static volatile CyclicBarrier professorBarrier = new CyclicBarrier(2);
    public static volatile Semaphore professorSemaphore = new Semaphore(2);


    @Override
    public void run() {
        Thread professorThread = null;
        Thread assistantThread = null;

        Random random = new Random();
        int arrivalTime = (int) (random.nextDouble() * 1000);

        try {
            Thread.sleep(arrivalTime);//student dolazi u vreme na intervalu 0-1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (random.nextInt(11) < 5) {//50% sansa da ode kod jednog ili drugog
            try {
                assistantSemaphore.acquire();
                assistantThread = new Thread(new Assistant(Thread.currentThread().getName(), arrivalTime));
                assistantThread.start();
                assistantThread.join();
                assistantSemaphore.release();
            }catch (InterruptedException e){
                if(assistantThread != null) assistantThread.interrupt();
                System.out.println("Student did not make it in time");
            }
        } else {
            try {
                professorSemaphore.acquire();
                professorThread = new Thread(new Professor(Thread.currentThread().getName(), arrivalTime));
                professorThread.start();
                professorThread.join();
                professorSemaphore.release();
            }catch (InterruptedException e){
                if(professorThread != null) professorThread.interrupt();
                System.out.println("Student did not make it in time");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int totalMark;
    public static long startTime;
    public static final String markLock = "markLock";

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> students = new ArrayList<>();
        int studentNumber;
        int studentNumTmp;

        System.out.println("Input student number");
        Scanner scanner = new Scanner(System.in);
        studentNumber = scanner.nextInt();
        studentNumTmp = studentNumber;
        startTime = System.currentTimeMillis();

        Thread timerThread = new Thread(new Timer());
        timerThread.start();//da bi znali kada se zavrsilo 5 sec

        while(studentNumber > 0) {
            students.add(new Thread(new Student()));
            students.get(students.size() - 1).start();
            studentNumber--;
        }

        timerThread.join();//ceka ovde dok ne prodje 5 sec (pritom idu student threadovi)

        for(Thread student: students){
            if(student.isAlive()) {
                studentNumTmp--;
                student.interrupt();
            }
        }

        System.out.println("Average mark: " + (totalMark / ((float) studentNumTmp)) );
    }
}

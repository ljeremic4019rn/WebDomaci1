import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static GlobalHolder globalHolder = new GlobalHolder();

    public static void main(String[] args) throws InterruptedException {
        int studentNumber;
        int finishedStudentNum = 0;
        Map<Integer, Thread> students = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input student number");
        studentNumber = scanner.nextInt();
        globalHolder.startTime = System.currentTimeMillis();

        Thread timerThread = new Thread(new Timer(5000));
        timerThread.start();//da bi znali kada se zavrsilo 5 sec

        for (int i = 0; i < studentNumber; i++) {
            students.put(i, new Thread(new Student()));
            students.get(i).start();
        }

        timerThread.join();//ceka ovde dok ne prodje 5 sec (pritom idu student threadovi)

        for (int i = 0; i < studentNumber; i++) {
            if (students.get(i).isAlive()){
                students.get(i).interrupt();
            }
            finishedStudentNum++;
        }

        System.out.println("Average mark: " + (GlobalHolder.totalMark / ((float) finishedStudentNum)) );
    }
}

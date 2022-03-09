public class Timer implements Runnable {
    private int sleepTime;

    public Timer(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

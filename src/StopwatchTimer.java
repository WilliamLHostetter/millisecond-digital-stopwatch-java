
public class StopwatchTimer implements Runnable {

    private Thread thread;
    private boolean running = false;
    private boolean paused = false;
    private StopwatchPanel stopwatchPanel;
    private long summedTime = 0;
    
    public StopwatchTimer(StopwatchPanel stopwatchPanel) {
        this.stopwatchPanel = stopwatchPanel;
    }
    
    public void startTimer() {
        running = true;
        paused = false;
        // start the thread up
        thread = new Thread(this);
        // setDaemon(true) so the thread will end when the main thread is finished
        thread.setDaemon(true);
        thread.start();
    }
    
    public void pauseTimer() {
        // just pause it
        paused = true;
    }
    
    public void stopTimer() {
        // completely stop the timer
        running = false;
        paused = false;
    }
    
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        // keep showing the difference in time until we are either paused or not running anymore
        while(running && !paused) {
            stopwatchPanel.update(summedTime + (System.currentTimeMillis() - startTime));
        }
        // if we just want to pause the timer dont throw away the change in time, instead store it
        if(paused)
            summedTime += System.currentTimeMillis() - startTime;
        else 
            summedTime = 0;
    }
    
}
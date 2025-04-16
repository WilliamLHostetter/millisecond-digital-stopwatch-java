/* 
A digital stopwatch with millisecond resolution and GUI display built using Java 
Swing. There are two threads in this application: one for the UI in 
StopwatchPanel.java that will update the text in the stopwatch GUI and one for 
the computation of the time in StopwatchTimer.java.
*/
public class Main {
    public static void main(String[] args) throws Exception {
        new StopwatchPanel();
    }
}

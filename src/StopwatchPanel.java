import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopwatchPanel implements ActionListener{
    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    long milliseconds = 0;
    long seconds = 0;
    long minutes = 0;
    long hours = 0;
    boolean started = false;
    String milliseconds_string = String.format("%03d", milliseconds);
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    private StopwatchTimer timer;
    
    StopwatchPanel(){

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);
        // timeLabel.setBounds(50,100,300,100);
        timeLabel.setBounds(50,25,300,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));

        timeLabel.setForeground(new Color(0x00FF00));
        // timeLabel.setForeground(new Color(0xDD010F)); 
        timeLabel.setBackground(Color.black);

        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        
        // startButton.setBounds(100,200,100,50);
        startButton.setBounds(100,125,100,50);
        startButton.setFont(new Font("Verdana",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        
        // resetButton.setBounds(200,200,100,50);
        resetButton.setBounds(200,125,100,50);
        resetButton.setFont(new Font("Verdana",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(420,420);
        frame.setSize(420,240);
        frame.setLayout(null);
        frame.setVisible(true);
        
        timer = new StopwatchTimer(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton) {
            if(started==false) {
                started=true;
                startButton.setText("STOP");
                start();
            } else {
                started=false;
                startButton.setText("START");
                stop();
            }
        }
        if(e.getSource()==resetButton) {
            started=false;
            startButton.setText("START");
            reset();
        }
    }

    void start() {
        timer.startTimer();
    }

    void stop() {
        timer.pauseTimer();
    }
    
    void reset() {
        timer.stopTimer();
        seconds = 0;
        minutes = 0;
        hours = 0;
        milliseconds = 0;
        milliseconds_string = String.format("%03d", milliseconds);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);
    }

    public void update(long dT){
        // convert dT in milliseconds into other forms
        hours = (dT/3600000);
        minutes = (dT/60000) % 60;
        seconds = (dT/1000) % 60;
        milliseconds = dT % 1000;
        milliseconds_string = String.format("%03d", milliseconds);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);
    }
    
}


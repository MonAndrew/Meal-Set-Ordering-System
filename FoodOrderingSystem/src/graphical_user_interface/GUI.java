package graphical_user_interface;

import java.awt.Color;
import javax.swing.*;

public class GUI {
    private JFrame frame;
    private JButton button,button2;
    private JPanel panelMain;

    private int frameWidth;
    private int frameHeight;

    public GUI(int frameWidth,int frameHeight){
        frame = new JFrame();
        panelMain = new JPanel();
        button = new JButton("Testing");
        button2 = new JButton("Testing 2");
        
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }
    
    public void setupGUI(){
        frame.setSize(this.frameWidth,this.frameHeight);

        panelMain.setLayout(null);
        panelMain.setBackground(Color.magenta);

        button.setBounds(10,10,100,40);
        button2.setBounds((button.getX() + 10 + button.getWidth()),10,100,40);

        //button.addActionListener();

        panelMain.add(button);
        panelMain.add(button2);

        frame.add(panelMain);

        //important best for last
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

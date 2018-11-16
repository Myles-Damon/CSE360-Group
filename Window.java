import javax.swing.*;    // to use JFrame and JButton

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame{

    private static void init(){

        final int APP_WIDTH = 1100;
        final int APP_HEIGHT = 600;

        ArrayList<Activity> actList;
        actList = new ArrayList<Activity>();

        JFrame frame = new JFrame("Network Explorer");
        JButton quit = new JButton("Quit");

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {

                if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    frame.dispose();
                    frame.setVisible(false);
                }
            }
        });

        MainPanel mainP = new MainPanel(actList);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.add(mainP);
        panel.add(quit);

        Container content = frame.getContentPane();
        content.add(panel);

        frame.setSize(APP_WIDTH, APP_HEIGHT);

        Dimension minDim = new Dimension(700, 600);
        frame.setMinimumSize(minDim);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String [] args){
        init();

    } //end of main


}

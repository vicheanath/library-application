package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StarterPagePanel extends JPanel{

    public StarterPagePanel(){
        super();
        init();
    }

    private void init(){
//      design landing page have image
        JPanel panel = new JPanel(new BorderLayout());
        JLabel icon = new JLabel();
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to MIU Library System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomePanel.setForeground(LColor.PRIMARY_COLOR);

        welcomePanel.add(welcomeLabel);
        panel.add(welcomePanel,BorderLayout.NORTH);

        String currDirectory = System.getProperty("user.dir");

        ImageIcon image = new ImageIcon(currDirectory+"/ui/logo.png");
        image.setImage(image.getImage().getScaledInstance(500, 500, 0));
        icon.setIcon(image);
        panel.add(icon,BorderLayout.CENTER);
        add(panel);
    }
}

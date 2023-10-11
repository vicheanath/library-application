package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginApp extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public LoginApp() {
        setTitle("Login Page");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel homePanel = createHomePanel();

        cardPanel.add(loginPanel, "login");
        cardPanel.add(homePanel, "home");

        add(cardPanel);

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // Perform authentication logic here
                // For simplicity, let's assume login is successful
                if (authenticate(username, new String(password))) {
                    cardLayout.show(cardPanel, "home");
                } else {
                    JOptionPane.showMessageDialog(LoginApp.this, "Invalid credentials. Try again.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        return panel;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Home Panel!");

        panel.add(welcomeLabel);

        return panel;
    }

    private boolean authenticate(String username, String password) {
        // Implement your authentication logic here
        // For simplicity, always return true
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginApp();
            }
        });
    }
}

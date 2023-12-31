package dataaccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarySystem extends JFrame {

    private JPanel leftPanel;
    private JSplitPane splitPane;

    public LibrarySystem() {
        // Perform login
        if (!performLogin()) {
            JOptionPane.showMessageDialog(this, "Authentication failed. Exiting.");
            System.exit(0);
        }

        setTitle("Library System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createLeftPanel();
        createSplitPane();

        add(splitPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean performLogin() {
        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField userText = new JTextField(20);
        JPasswordField passwordText = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        panel.setLayout(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Check username and password (dummy check for demo purposes)
            String username = userText.getText();
            char[] password = passwordText.getPassword();

            // Replace the following condition with your actual authentication logic
            if ("admin".equals(username) && "adminpass".equals(new String(password))) {
                return true; // Authentication successful
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                return false; // Authentication failed
            }
        } else {
            return false; // User clicked Cancel
        }
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Add Member button click
                JOptionPane.showMessageDialog(null, "Add Member Clicked");
            }
        });

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Add Book button click
                JOptionPane.showMessageDialog(null, "Add Book Clicked");
            }
        });

        leftPanel.add(addMemberButton);
        leftPanel.add(addBookButton);
    }

    private void createSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, new JPanel());
        splitPane.setDividerLocation(150);
        splitPane.setEnabled(false); // Disable resizing
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibrarySystem();
            }
        });
    }
}

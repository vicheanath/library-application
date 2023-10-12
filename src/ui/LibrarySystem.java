package ui;

import javax.swing.*;
import business.*;
import dataaccess.Auth;
import librarysystem.AddNewBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


class LMenu {
    String name;
    List<Auth> roles;
    JPanel panel;
    LMenu(String name, JPanel panel, List<Auth> roles ) {
        this.name = name;
        this.roles = roles;
        this.panel = panel;
    }
}

public class LibrarySystem extends JFrame {

    private JPanel leftPanel;
    private JSplitPane splitPane;

    public List<LMenu> menus = List.of(
            new LMenu("+ Add copy to collection", new AddCopyBookToCollectionPanel(),List.of(Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu("List All Books",new ListAllBooksPanel(), List.of(Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu("+ Add New Book", new AddNewBookPanel() ,List.of(Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu("List All Members", new ListAllMemberPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu("🧑‍ Add Member", new AddMemberPanel(), List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu("Users", new ListUserPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu("Check Out Book", new CheckoutBookPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu("Check OverDue Book", new ListOverDueBookPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu("List Check Record", new ListCheckOutRecordEntryPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN))
    );
    public LibrarySystem() {
        // Perform login
        if (!performLogin()) {
            dispose();
            new LibrarySystem();
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
        JTextField userText = new JTextField(10);
        userText.setMaximumSize(new Dimension(150, 30));

        JPasswordField passwordText = new JPasswordField(10);
        passwordText.setMaximumSize(new Dimension(150, 30));
//        userText.setText("101");
//        passwordText.setText("xyz");

        panel.setLayout(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            String username = userText.getText();
            char[] password = passwordText.getPassword();

            if (username.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(null, "Username or password cannot be empty");
                return false;
            }

            try {
                SystemController dataAccessFacade = new SystemController();
                dataAccessFacade.login(username, new String(password));
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return false;
            }
        } else {
            return false; // User clicked Cancel
        }
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        JLabel label = new JLabel("Library System");


        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(150, 50));
        label.setForeground(Color.BLUE);

        leftPanel.add(label);


        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        for (LMenu menu : menus) {
            if (menu.roles.contains(SystemController.currentAuth)) {
                JButton button = new JButton(menu.name);
                button.setMaximumSize(new Dimension(150, 40));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setAlignmentY(Component.CENTER_ALIGNMENT);
                button.setFont(new Font("Arial", Font.PLAIN, 14));
                button.setForeground(Color.BLACK);
                button.setBackground(Color.WHITE);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showPanel(menu.panel);
                    }
                });
                leftPanel.add(button);
            }
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.setMaximumSize(new Dimension(150, 40));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemController.currentAuth = null;
                dispose();
                new LibrarySystem();
            }
        });

        leftPanel.add(logoutButton);

    }

    private void createSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, new JPanel());
        splitPane.setDividerLocation(160);
        splitPane.setEnabled(false); // Disable resizing
    }

    private void showPanel(JPanel panelToShow) {
        splitPane.setRightComponent(panelToShow);
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





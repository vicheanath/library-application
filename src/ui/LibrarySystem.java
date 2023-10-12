package ui;

import javax.swing.*;

import business.SystemController;
import dataaccess.Auth;

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
            new LMenu("List All Books",new ListAllBooksPanel(), List.of(Auth.ADMIN, Auth.LIBRARIAN, Auth.BOTH)),
            new LMenu("Add New Book", new AddNewBookPanel() ,List.of(Auth.ADMIN, Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu("Add Member", new AddMemberPanel(), List.of(Auth.ADMIN, Auth.BOTH)),
            new LMenu("List All Members", new ListAllBooksPanel(),List.of(Auth.ADMIN, Auth.LIBRARIAN, Auth.BOTH))
    );
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
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // JButton listAllBooksButton = new JButton("List All Books" + SystemController.currentAuth);
        // JButton listAllMember = new JButton("List All Members");
        // JButton addNewBookButton = new JButton("Add New Book");
        // JButton addMemberButton = new JButton("Add Member");

        for (LMenu menu : menus) {
            if (menu.roles.contains(SystemController.currentAuth)) {
                JButton button = new JButton(menu.name);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showPanel(menu.panel);
                    }
                });
                leftPanel.add(button);
            }
        }


        // listAllBooksButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         showPanel(new ListAllBooksPanel());
        //     }
        // });

        
        // addNewBookButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         showPanel(new AddNewBookPanel());
        //     }
        // });

        
        // addMemberButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         showPanel(new AddMemberPanel());
        //     }
        // });

        
        // listAllMember.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         showPanel(new ListAllMemberPanel());
        //     }
        // });

        // leftPanel.add(listAllBooksButton);
        // leftPanel.add(addNewBookButton);
        // leftPanel.add(addMemberButton);
        // leftPanel.add(listAllMember);
    }

    private void createSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, new JPanel());
        splitPane.setDividerLocation(150);
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





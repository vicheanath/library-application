package ui;

import javax.swing.*;
import business.*;
import dataaccess.Auth;
import librarysystem.AddNewBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;


class GroupMenu {
    String name;
    List<LMenu> menus;
    GroupMenu(String name, List<LMenu> menus) {
        this.name = name;
        this.menus = menus;
    }
}

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

    public static final String LIST_ALL_BOOKS = "List All Books";
    public static final String ADD_NEW_BOOK = "Add New Book";
    public static final String CHECK_OUT_BOOK = "Check Out Book";
    public static final String CHECK_OVERDUE_BOOK = "Check OverDue Book";
    public static final String LIST_CHECK_RECORD = "List Check Record";
    public static final String ADD_COPY_TO_COLLECTION = "Add copy to collection";
    public static final String LIST_ALL_MEMBERS = "List All Members";
    public static final String EDIT_MEMBER = "Edit member";
    public static final String ADD_MEMBER = "Add Member";
    public static final String LIST_USERS = "Users";
    public List<LMenu> bookMenu = List.of(
            new LMenu(LIST_ALL_BOOKS,new ListAllBooksPanel(), List.of(Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu(ADD_NEW_BOOK, new AddNewBookPanel() ,List.of(Auth.BOTH, Auth.LIBRARIAN)),
            new LMenu(CHECK_OUT_BOOK, new CheckoutBookPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu(CHECK_OVERDUE_BOOK, new ListOverDueBookPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu(LIST_CHECK_RECORD, new ListCheckOutRecordEntryPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN)),
            new LMenu(ADD_COPY_TO_COLLECTION, new AddCopyBookToCollectionPanel(),List.of(Auth.BOTH, Auth.LIBRARIAN))
    );

    public List<LMenu> memberMenu = List.of(
            new LMenu(EDIT_MEMBER, new EditMemberPanel(),List.of(Auth.ADMIN, Auth.LIBRARIAN, Auth.BOTH)),
            new LMenu(ADD_MEMBER, new AddMemberPanel(), List.of(Auth.BOTH,Auth.LIBRARIAN))
    );

    public List<LMenu> userMenu = List.of(
            new LMenu(LIST_USERS, new ListUserPanel(),List.of(Auth.BOTH,Auth.LIBRARIAN))
    );

    public  List<GroupMenu> systemMenu = List.of(
            new GroupMenu("Book",bookMenu),
            new GroupMenu("Member",memberMenu),
            new GroupMenu("User",userMenu)
    );



    public LibrarySystem() {
        // Perform login
        if (!performLogin()) {
            dispose();
            new LibrarySystem();
        }

        setTitle("Library System");
        setSize(800, 700);
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
        userText.setText("101");
        passwordText.setText("xyz");

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


        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(150, 50));
        label.setForeground(LColor.PRIMARY_COLOR);

        leftPanel.add(label);


        leftPanel.setBackground(LColor.TERTIARY_COLOR);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            for (GroupMenu groupMenu : systemMenu) {
                JLabel groupLabel = new JLabel(groupMenu.name);
                groupLabel.setFont(new Font("Arial", Font.BOLD, 14));
                groupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                groupLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                groupLabel.setMaximumSize(new Dimension(150, 30));
                groupLabel.setForeground(LColor.PRIMARY_COLOR);
                leftPanel.add(groupLabel);

                for (LMenu m : groupMenu.menus) {
                    if (m.roles.contains(SystemController.currentAuth)) {
                        JButton button = new JButton(m.name);
                        buttonStyle(button);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                switch (m.name) {
                                    case LIST_ALL_BOOKS:
                                        ((ListAllBooksPanel) m.panel).initialize();
                                        break;
                                    case ADD_NEW_BOOK:
                                        ((AddNewBookPanel) m.panel).initialize();
                                        break;
                                    case CHECK_OUT_BOOK:
                                        ((CheckoutBookPanel) m.panel).initialize();
                                        break;
                                    case CHECK_OVERDUE_BOOK:
                                        ((ListOverDueBookPanel) m.panel).initialize();
                                        break;
                                    case LIST_CHECK_RECORD:
                                        ((ListCheckOutRecordEntryPanel) m.panel).initialize();
                                        break;
                                    case ADD_COPY_TO_COLLECTION:
                                        ((AddCopyBookToCollectionPanel) m.panel).initialize();
                                        break;
                                    case LIST_ALL_MEMBERS:
                                        ((ListAllMemberPanel) m.panel).initialize();
                                        break;
                                    case EDIT_MEMBER:
                                        ((EditMemberPanel) m.panel).initialize();
                                        break;
                                    case ADD_MEMBER:
                                        ((AddMemberPanel) m.panel).initialize();
                                        break;
                                    case LIST_USERS:
                                        ((ListUserPanel) m.panel).initialize();
                                        break;
                                }

                                showPanel(m.panel);
                            }
                        });
                        leftPanel.add(button);
                    }
                }

        }

        JButton logoutButton = new JButton("Logout");
        buttonStyle(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                SystemController.currentAuth = null;
                dispose();
                new LibrarySystem();
            }
        });

        leftPanel.add(logoutButton);

    }

    public static void buttonStyle(JButton button) {
        button.setMaximumSize(new Dimension(190, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setBackground(LColor.ACCENT_COLOR);
        button.setForeground(LColor.PRIMARY_COLOR);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(LColor.PRIMARY_COLOR);
                button.setForeground(LColor.ACCENT_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(LColor.ACCENT_COLOR);
                button.setForeground(LColor.PRIMARY_COLOR);
            }
        });
    }

    private void createSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, new JPanel());
        splitPane.setDividerLocation(200);
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





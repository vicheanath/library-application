        package librarysystem;

        import business.SystemController;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        public class AddNewMember extends JFrame implements LibWindow {
            public static final AddNewMember INSTANCE = new AddNewMember();
            JFrame frame;
            private boolean isInitialized = false;
            @Override
            public void setVisible(boolean b){
                frame.setVisible(b);
            }
            @Override
            public void init() {
                frame = new JFrame("New member information");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(createMemberInfoPanel());
                isInitialized(true);
                frame.pack();
            }
            @Override
            public boolean isInitialized() {
                return isInitialized;
            }

            @Override
            public void isInitialized(boolean val) {
                isInitialized = val;
            }

            private JTextField memberIdField;
            private JTextField firstNameField;
            private JTextField lastNameField;
            private JTextField streetField;
            private JTextField cityField;
            private JTextField stateField;
            private JTextField zipField;
            private JTextField phoneField;
            private JButton submitButton;

            private JPanel createMemberInfoPanel() {
                JPanel memberInfoPanel = new JPanel();
                memberInfoPanel.setLayout(new GridLayout(9, 2)); // 9 rows, 2 columns

                // Labels and Text Fields
                memberInfoPanel.add(new JLabel("Member ID:"));
                memberIdField = new JTextField(10);
                memberInfoPanel.add(memberIdField);

                memberInfoPanel.add(new JLabel("First Name:"));
                firstNameField = new JTextField(20);
                memberInfoPanel.add(firstNameField);

                memberInfoPanel.add(new JLabel("Last Name:"));
                lastNameField = new JTextField(20);
                memberInfoPanel.add(lastNameField);

                memberInfoPanel.add(new JLabel("Street:"));
                streetField = new JTextField(30);
                memberInfoPanel.add(streetField);

                memberInfoPanel.add(new JLabel("City:"));
                cityField = new JTextField(20);
                memberInfoPanel.add(cityField);

                memberInfoPanel.add(new JLabel("State:"));
                stateField = new JTextField(2);
                memberInfoPanel.add(stateField);

                memberInfoPanel.add(new JLabel("ZIP:"));
                zipField = new JTextField(10);
                memberInfoPanel.add(zipField);

                memberInfoPanel.add(new JLabel("Telephone Number:"));
                phoneField = new JTextField(12);
                memberInfoPanel.add(phoneField);

                // Submit Button
                submitButton = new JButton("Submit");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SystemController systemController = new SystemController();
                        systemController.AddNewMember(memberIdField.getText(),firstNameField.getText(),
                                lastNameField.getText(),streetField.getText(), cityField.getText(),
                                stateField.getText(),zipField.getText(),phoneField.getText());
                    }
                });
                JButton backButton = new JButton("Back to Main");
                backButton.addActionListener(e -> {
                    LibrarySystem.hideAllWindows();
                    LibrarySystem.INSTANCE.setVisible(true);
                });
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                buttonPanel.add(backButton);
                buttonPanel.add(submitButton);
                memberInfoPanel.add(buttonPanel);
                return memberInfoPanel;
            }
        }

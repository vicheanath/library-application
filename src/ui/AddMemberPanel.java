package ui;

import business.LibraryMember;
import business.SystemController;
import librarysystem.AddNewMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddMemberContainer extends JFrame {

    public AddMemberContainer() {
        setTitle("Add Member Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new AddMemberPanel());
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddMemberContainer());
    }
}

class AddMemberPanel extends JPanel {
    private JTextField memberIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JButton submitButton;

    public AddMemberPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(10, 2));
        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        addField("Member ID:", memberIdField = createNonEditableTextField(10));
        memberIdField.setText(LibraryMember.genId(libraryMembers));
        addField("First Name:", firstNameField = createTextField(20));
        addField("Last Name:", lastNameField = createTextField(20));
        addField("Street:", streetField = createTextField(30));
        addField("City:", cityField = createTextField(20));
        addField("State:", stateField = createTextField(2));
        addField("ZIP:", zipField = createTextField(10));
        addField("Telephone Number:", phoneField = createTextField(12));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewMember();
            }
        });

        add(submitButton);
    }

    private void addField(String label, JTextField textField) {
        add(new JLabel(label));
        add(textField);
    }

    private JTextField createTextField(int columns) {
        return new JTextField(columns);
    }

    private JTextField createNonEditableTextField(int columns) {
        JTextField textField = createTextField(columns);
        textField.setEditable(false);
        return textField;
    }

    private void addNewMember() {
        SystemController systemController = new SystemController();
        systemController.AddNewMember(
                memberIdField.getText(),
                firstNameField.getText(),
                lastNameField.getText(),
                streetField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipField.getText(),
                phoneField.getText()
        );
    }

    public static final AddNewMember INSTANCE = new AddNewMember();
}

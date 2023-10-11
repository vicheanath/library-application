package librarysystem;

import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddNewMember extends JPanel implements LibWindow {
    public static final AddNewMember INSTANCE = new AddNewMember();
    private boolean isInitialized = false;

    @Override
    public void init() {
        JFrame frame = new JFrame("New member information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMemberInfoPanel());
        frame.pack();
        frame.setVisible(true);
        isInitialized = true;
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

        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        // Labels and Text Fields
        memberInfoPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField(10);
        memberIdField.setEditable(false);
        memberIdField.setText(LibraryMember.genId(libraryMembers));
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
        memberInfoPanel.add(submitButton);

        return memberInfoPanel;
    }
}

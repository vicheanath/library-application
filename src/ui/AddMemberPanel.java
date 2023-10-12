package ui;

import business.LibraryMember;
import business.SystemController;
import librarysystem.AddNewMember;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // Implement the UI for adding a new member
        // add(new JLabel("Add Member"));

        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();
        setLayout(new GridLayout(9, 2));
        // Labels and Text Fields
        add(new JLabel("Member ID:"));
        memberIdField = new JTextField(10);
        memberIdField.setEditable(false);
        memberIdField.setText(LibraryMember.genId(libraryMembers));
        add(memberIdField);


        add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        add(lastNameField);

        add(new JLabel("Street:"));
        streetField = new JTextField(30);
        add(streetField);

        add(new JLabel("City:"));
        cityField = new JTextField(20);
        add(cityField);

        add(new JLabel("State:"));
        stateField = new JTextField(2);
        add(stateField);

        add(new JLabel("ZIP:"));
        zipField = new JTextField(10);
        add(zipField);

        add(new JLabel("Telephone Number:"));
        phoneField = new JTextField(12);
        add(phoneField);

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

        add(submitButton);
        
        
    }
    public static final AddNewMember INSTANCE = new AddNewMember();

    
}

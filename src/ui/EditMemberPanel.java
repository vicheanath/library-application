package ui;

import business.LibraryMember;
import business.SystemController;
import librarysystem.AddNewMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;


class EditMemberPanel extends JPanel {
    private JTextField memberIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JButton submitButton;
    private JPanel main = new JPanel(new BorderLayout());
    private JTextField memberId;
    private JPanel memberLookup;
    private JPanel memberData = new JPanel();
    SystemController systemController = new SystemController();
    List<LibraryMember> libraryMembers = systemController.allMembers();
    LibraryMember libraryMember;

    public EditMemberPanel() {
        initializeUI();
    }

    private void initializeUI() {
        defineMemberLookup();
    }

    private void defineMemberLookup(){
        memberLookup = new JPanel(new FlowLayout());
        JLabel jLabel = new JLabel("Please enter member id : ");
        memberId = new JTextField(15);
        JButton jButton = new JButton("Lookup");
        memberLookup.add(jLabel);
        memberLookup.add(memberId);
        memberLookup.add(jButton);
        main.add(memberLookup,BorderLayout.NORTH);
        memberData.removeAll();

        memberData.setLayout(new GridLayout(0,2));
        addField("Member ID:", memberIdField = createTextField(10));
        addField("First Name:", firstNameField = createTextField(20));
        addField("Last Name:", lastNameField = createTextField(20));
        addField("Street:", streetField = createTextField(20));
        addField("City:", cityField = createTextField(20));
        addField("State:", stateField = createTextField(2));
        addField("ZIP:", zipField = createTextField(10));
        addField("Telephone Number:", phoneField = createTextField(12));

        main.add(memberData,BorderLayout.CENTER);
        submitButton = new JButton("Submit");
        add(main);
        add(submitButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineMemberData();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMemberData();
            }
        });
    }
    private void defineMemberData(){
        for(LibraryMember l : libraryMembers){
            if(Objects.equals(l.getMemberId(), memberId.getText())){
                libraryMember = l;
            }
        }
        memberIdField.setText(libraryMember.getMemberId());
        firstNameField.setText(libraryMember.getFirstName());
        lastNameField.setText(libraryMember.getLastName());
        streetField.setText(libraryMember.getAddress().getStreet());
        cityField.setText(libraryMember.getAddress().getCity());
        stateField.setText(libraryMember.getAddress().getState());
        zipField.setText(libraryMember.getAddress().getZip());
        phoneField.setText(libraryMember.getTelephone());
    }
    private void setMemberData(){
        if(!Objects.equals(firstNameField.getText(), libraryMember.getFirstName())){
                    libraryMember.setFirstName(firstNameField.getText());
                }
                if(!Objects.equals(lastNameField.getText(), libraryMember.getLastName())){
                    libraryMember.setLastName(lastNameField.getText());
                }
                if(!Objects.equals(streetField.getText(), libraryMember.getAddress().getStreet())){
                    libraryMember.getAddress().setStreet(streetField.getText());
                }
                if(!Objects.equals(cityField.getText(), libraryMember.getAddress().getCity())){
                    libraryMember.getAddress().setCity(cityField.getText());
                }
                if(!Objects.equals(stateField.getText(), libraryMember.getAddress().getState())){
                    libraryMember.getAddress().setState(stateField.getText());
                }
                if(!Objects.equals(zipField.getText(), libraryMember.getAddress().getZip())){
                    libraryMember.getAddress().setZip(zipField.getText());
                }
                if(!Objects.equals(phoneField.getText(), libraryMember.getTelephone())){
                    libraryMember.setTelephone(phoneField.getText());
                }
    }
    private void addField(String label, JTextField textField){
        memberData.add(new JLabel(label));
        memberData.add(textField);
    }
    private JTextField createTextField(int columns) {
        JTextField jTextField = new JTextField(columns);
        jTextField.setPreferredSize(new Dimension(300,30));
        return jTextField;
    }
    private JTextField createNonEditableTextField(int columns) {
        JTextField textField = createTextField(columns);
        textField.setEditable(false);
        return textField;
    }
}
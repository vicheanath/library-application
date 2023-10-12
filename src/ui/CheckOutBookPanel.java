package ui;

import business.LibraryMember;
import business.SystemController;
import librarysystem.AddNewMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CheckOutBookPanelContainer extends JFrame {

    public CheckOutBookPanelContainer() {
        setTitle("Check Out Book Form");
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

class CheckOutBookPanel extends JPanel {
    private JTextField memberIdField;
    private JTextField isbnField;
    private JButton submitButton;

    public CheckOutBookPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(10, 2));

        addField("Member ID:", memberIdField = createNonEditableTextField(10));

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
        systemController.checkoutBook(isbnField.getText(), memberIdField.getText());
    }

    public static final AddNewMember INSTANCE = new AddNewMember();
}

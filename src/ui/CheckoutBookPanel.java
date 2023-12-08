


package ui;

import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


class CheckoutBookPanel extends JPanel implements IPanel {
    private JTextField memberIdField;
    private JTextField bookIsbnField;

    private JButton submitButton;

    public CheckoutBookPanel() {
        initializeUI();
    }

    private void initializeUI() {
        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        addField("Member ID:", memberIdField = createTextField(10));
        addField("Book ISBN:", bookIsbnField = createTextField(20));


        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCheckOut();
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

    private void performCheckOut() {
        SystemController systemController = new SystemController();
        try {
            RuleSet rules = RuleSetFactory.getRuleSet(this);
            rules.applyRules(this);
            try {
                systemController.checkoutBook(
                        memberIdField.getText(),
                        bookIsbnField.getText()
                );
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }

            JOptionPane.showMessageDialog(this, "Book + " + bookIsbnField.getText() + " has been checked out by " + memberIdField.getText());
            memberIdField.setText("");
            bookIsbnField.setText("");

        } catch(RuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }

    public static final CheckoutBookPanel INSTANCE = new CheckoutBookPanel();


    public String getMemberIdField() {
        return memberIdField.getText();
    }


    public String getBookIsbnField() {
        return bookIsbnField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    @Override
    public void initialize() {

    }
}

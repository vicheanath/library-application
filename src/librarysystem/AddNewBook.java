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

public class AddNewBook extends JPanel implements LibWindow {
    public static final AddNewMember INSTANCE = new AddNewMember();
    private boolean isInitialized = false;



    ////////////

    public static void main(String[] args) {
        AddNewBook addNewBook = new AddNewBook();
        addNewBook.init();
    }
    ///////////


    @Override
    public void init() {
        JFrame frame = new JFrame("New Book information");
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
    private JTextField ISBNField;
    private JTextField BookTitle;
    private JTextField MaxCheckoutLength;
    private JTextField AuthorFirstName;
    private JTextField AuthorLastName;
    private JTextField TephoneNumber;
    private JTextField AuthorBio;


    private JTextField AuthorstreetField;
    private JTextField AuthorcityField;
    private JTextField AuthorstateField;
    private JTextField AuthorzipField;
    private JButton submitButton;

    private JPanel createMemberInfoPanel() {
        JPanel memberInfoPanel = new JPanel();
        memberInfoPanel.setLayout(new GridLayout(20, 5)); // 9 rows, 2 columns

        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        // Labels and Text Fields
        memberInfoPanel.add(new JLabel("ISBNField: "));
        ISBNField = new JTextField(10);
        //ISBNField.setEditable(false);
        //ISBNField.setText(LibraryMember.genId(libraryMembers));
        memberInfoPanel.add(ISBNField);


        memberInfoPanel.add(new JLabel("Book Title:"));
        BookTitle = new JTextField(20);
        memberInfoPanel.add(BookTitle);

        memberInfoPanel.add(new JLabel("Max Checkout Length:"));
        MaxCheckoutLength = new JTextField(20);
        memberInfoPanel.add(MaxCheckoutLength);

        memberInfoPanel.add(new JLabel("Author First Name:"));
        AuthorFirstName = new JTextField(30);
        memberInfoPanel.add(AuthorFirstName);

        memberInfoPanel.add(new JLabel("Author Last Name:"));
        AuthorLastName = new JTextField(20);
        memberInfoPanel.add(AuthorLastName);

        memberInfoPanel.add(new JLabel("Tephone Number:"));
        TephoneNumber = new JTextField(20);
        memberInfoPanel.add(TephoneNumber);

        memberInfoPanel.add(new JLabel("Author Bio:"));
        AuthorBio = new JTextField(2);
        memberInfoPanel.add(AuthorBio);

        memberInfoPanel.add(new JLabel("Author street Field:"));
        AuthorstreetField = new JTextField(10);
        memberInfoPanel.add(AuthorstreetField);

        memberInfoPanel.add(new JLabel("Author city:"));
        AuthorcityField = new JTextField(12);
        memberInfoPanel.add(AuthorcityField);

        memberInfoPanel.add(new JLabel("Author state:"));
        AuthorstateField = new JTextField(12);
        memberInfoPanel.add(AuthorstateField);

        memberInfoPanel.add(new JLabel("Author zip:"));
        AuthorzipField = new JTextField(12);
        memberInfoPanel.add(AuthorzipField);


        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemController systemController = new SystemController();
                systemController.addNewBook(ISBNField.getText(),BookTitle.getText(),
                        MaxCheckoutLength.getText(),AuthorFirstName.getText(), AuthorLastName.getText(),
                        TephoneNumber.getText(),AuthorBio.getText(),AuthorstreetField.getText(), AuthorcityField.getText(),
                        AuthorstateField.getText(),AuthorzipField.getText());
            }
        });
        memberInfoPanel.add(submitButton);

        return memberInfoPanel;
    }
}

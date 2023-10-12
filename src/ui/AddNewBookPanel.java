package ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LibraryMember;
import business.SystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddNewBookPanel extends JPanel {
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
    public AddNewBookPanel() {
        // Implement the UI for adding a new book
         setLayout(new GridLayout(20, 5)); // 9 rows, 2 columns

        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        // Labels and Text Fields
        add(new JLabel("ISBNField: "));
        ISBNField = new JTextField(10);
        //ISBNField.setEditable(false);
        //ISBNField.setText(LibraryMember.genId(libraryMembers));
        add(ISBNField);


        add(new JLabel("Book Title:"));
        BookTitle = new JTextField(20);
        add(BookTitle);

        add(new JLabel("Max Checkout Length:"));
        MaxCheckoutLength = new JTextField(20);
        add(MaxCheckoutLength);

        add(new JLabel("Author First Name:"));
        AuthorFirstName = new JTextField(30);
        add(AuthorFirstName);

        add(new JLabel("Author Last Name:"));
        AuthorLastName = new JTextField(20);
        add(AuthorLastName);

        add(new JLabel("Tephone Number:"));
        TephoneNumber = new JTextField(20);
        add(TephoneNumber);

        add(new JLabel("Author Bio:"));
        AuthorBio = new JTextField(2);
        add(AuthorBio);

        add(new JLabel("Author street Field:"));
        AuthorstreetField = new JTextField(10);
        add(AuthorstreetField);

        add(new JLabel("Author city:"));
        AuthorcityField = new JTextField(12);
        add(AuthorcityField);

        add(new JLabel("Author state:"));
        AuthorstateField = new JTextField(12);
        add(AuthorstateField);

        add(new JLabel("Author zip:"));
        AuthorzipField = new JTextField(12);
        add(AuthorzipField);


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
        add(submitButton);
    }
}
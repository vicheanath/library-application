package ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LibraryMember;
import business.SystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddNewBookPanel extends JPanel implements IPanel{
    private JTextField ISBNField;
    private JTextField bookTitle;
    private JTextField maxCheckoutLength;
    private JTextField authorFirstName;
    private JTextField authorLastName;
    private JTextField telephoneNumber;
    private JTextField authorBio;

    private JTextField authorStreetField;
    private JTextField authorCityField;
    private JTextField authorStateField;
    private JTextField authorZipField;
    private JButton submitButton;

    public AddNewBookPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(20, 2));
        SystemController dataAccessFacade = new SystemController();

        List<LibraryMember> libraryMembers = dataAccessFacade.allMembers();

        addField("ISBN:", ISBNField = createNonEditableTextField(10));
        ISBNField.setText(LibraryMember.genId(libraryMembers));
        addField("Book Title:", bookTitle = createTextField(20));
        addField("Max Checkout Length:", maxCheckoutLength = createTextField(20));
        addField("Author First Name:", authorFirstName = createTextField(30));
        addField("Author Last Name:", authorLastName = createTextField(20));
        addField("Telephone Number:", telephoneNumber = createTextField(10));
        addField("Author Bio:", authorBio = createTextField(10));
        addField("Author Street:", authorStreetField = createTextField(10));
        addField("Author City:", authorCityField = createTextField(10));
        addField("Author State:", authorStateField = createTextField(10));
        addField("Author Zip:", authorZipField = createTextField(10));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewBook();
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

    private void addNewBook() {
        SystemController dataAccessFacade = new SystemController();

        try {
            RuleSet rules = RuleSetFactory.getRuleSet(this);
            rules.applyRules(this);
            dataAccessFacade.addNewBook(ISBNField.getText(), bookTitle.getText(), maxCheckoutLength.getText(),
                    authorFirstName.getText(), authorLastName.getText(), telephoneNumber.getText(), authorBio.getText(),
                    authorStreetField.getText(), authorCityField.getText(), authorStateField.getText(),
                    authorZipField.getText());
        } catch (RuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }
    public String getISBNField() {
        return ISBNField.getText();
    }

    public String getBookTitle() {
        return bookTitle.getText();
    }

    public String getMaxCheckoutLength() {
        return maxCheckoutLength.getText();
    }

    public String getAuthorFirstName() {
        return authorFirstName.getText();
    }

    public String getAuthorLastName() {
        return authorLastName.getText();
    }

    public String getTelephoneNumber() {
        return telephoneNumber.getText();
    }

    public String getAuthorBio() {
        return authorBio.getText();
    }

    public String getAuthorStreetField() {
        return authorStreetField.getText();
    }

    public String getAuthorCityField() {
        return authorCityField.getText();
    }

    public String getAuthorStateField() {
        return authorStateField.getText();
    }

    public String getAuthorZipField() {
        return authorZipField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }


    @Override
    public void initialize() {
        System.out.println("AddNewBookPanel.initialize");
    }
}
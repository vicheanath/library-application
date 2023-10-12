package ui;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCopyBookToCollectionPanel extends JPanel {

    ControllerInterface ci = new SystemController();

    JButton submitButton;
    JButton addToCollection;
    JTextField isbnField;
    JTextField copynumber;
    JTable table;
    DefaultTableModel model; // Declare the model as a class member
    SystemController systemController = new SystemController();
    Book book;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel insideTopPanel;
    JPanel BottomPanel;
    JPanel lookup;
    JLabel jLabel;
    JPanel main = new JPanel(new BorderLayout());
    public AddCopyBookToCollectionPanel() {
        model = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Title", "Authors", "Max Checkout Length", "Is copy available", "Number of copies"});
        defineTopPanel();
        defineTopTable();
        defineBotPanel();

        main.add(topPanel,BorderLayout.NORTH);
        main.add(BottomPanel, BorderLayout.SOUTH);
        add(main);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                book = systemController.getBookById(isbn);
                model.setRowCount(0); // Clear the existing table rows
                String isCopyAvailable = book.isAvailable() ? "Copy is available" : "Copy is not available";
                model.addRow(new Object[]{book.getIsbn(), book.getTitle(), book.getAuthors(), book.getMaxCheckoutLength(), isCopyAvailable, book.getNumCopies()});
                setColumnWidths(table);
            }
        });

        addToCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfCopy = Integer.parseInt(copynumber.getText());
                systemController.addCopyOfBookToCollection(book.getCopy(numberOfCopy).getBook());
            }
        });
    }
    private void defineTopPanel(){
        submitButton = new JButton("Lookup");
        jLabel = new JLabel("Please enter ISBN number : ");
        isbnField = new JTextField(15);
        lookup = new JPanel(new FlowLayout());
        lookup.add(jLabel);
        lookup.add(isbnField);
        lookup.add(submitButton);
        insideTopPanel = new JPanel(new BorderLayout());
        insideTopPanel.add(lookup, BorderLayout.NORTH);
    }
    private void defineTopTable(){
        table = new JTable(model);
        setColumnWidths(table); // Set column widths
        insideTopPanel.add(new JScrollPane(table),BorderLayout.SOUTH);
        topPanel.add(insideTopPanel, BorderLayout.NORTH);
    }
    private void defineBotPanel(){
        addToCollection = new JButton("Add copy to collection");
        jLabel = new JLabel("Please enter number of copy : ");
        copynumber = new JTextField(15);
        BottomPanel = new JPanel(new FlowLayout());
        BottomPanel.add(jLabel);
        BottomPanel.add(copynumber);
        BottomPanel.add(addToCollection);
    }
    private void setColumnWidths(JTable table) {
        // Set the preferred width for each column
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250); // ISBN
        columnModel.getColumn(1).setPreferredWidth(300); // Title
        columnModel.getColumn(2).setPreferredWidth(400); // Authors
        columnModel.getColumn(3).setPreferredWidth(150); // Max Checkout Length
        columnModel.getColumn(4).setPreferredWidth(650); // Is copy available
        columnModel.getColumn(5).setPreferredWidth(450); // Number of copies
    }
}
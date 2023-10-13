package ui;

import business.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListCheckOutRecordEntryPanel extends JPanel implements IPanel {

    ControllerInterface ci = new SystemController();

    JButton submitButton;
    JTextField memberIDField;
    JTable table;
    DefaultTableModel model; // Declare the model as a class member
    SystemController systemController = new SystemController();
    List<CheckOutRecordAllMember> checkoutRecordEntries;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel insideTopPanel;
    JPanel BottomPanel;
    JPanel lookup;
    JLabel jLabel;
    JPanel main = new JPanel(new BorderLayout());
    public ListCheckOutRecordEntryPanel() {
        System.out.println("ListCheckOutRecordEntryPanel+++++");
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Book Copy" , "Due Date" ,"First Name", "Last Name","Member ID"});
        defineTopPanel();
        defineTopTable();
        defineBotPanel();
        getListCheckOutRecord();
        main.add(topPanel,BorderLayout.NORTH);
        main.add(BottomPanel, BorderLayout.SOUTH);
        add(main);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                checkoutRecordEntries = systemController.getCheckOutRecordEntry(memberIDField.getText());
                model.setRowCount(0);
                for (CheckOutRecordAllMember checkoutRecordEntry:checkoutRecordEntries){
                    model.addRow(new Object[]{
                            checkoutRecordEntry.getBookCopy().getBook().getTitle() + " - " + checkoutRecordEntry.getBookCopy().getBook().getIsbn() + " - " + checkoutRecordEntry.getBookCopy().getCopyNum(),
                            checkoutRecordEntry.getDueDate(),
                    });
                }
                setColumnWidths(table);
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void getListCheckOutRecord(){
        checkoutRecordEntries = systemController.getCheckOutRecordEntryAllMembers();
        model.setRowCount(0);
        for (CheckOutRecordAllMember checkoutRecordEntry:checkoutRecordEntries){
            model.addRow(new Object[]{
                    checkoutRecordEntry.getBookCopy().getBook().getTitle(),
                    checkoutRecordEntry.getDueDate(),
                    checkoutRecordEntry.getLibraryMember().getFirstName(),
                    checkoutRecordEntry.getLibraryMember().getLastName(),
                    checkoutRecordEntry.getLibraryMember().getMemberId()
            });
        }
        setColumnWidths(table);
    }
    private void defineTopPanel(){
        submitButton = new JButton("🔍 Search");
        jLabel = new JLabel("Please enter MemberID : ");
        memberIDField = new JTextField(15);
        lookup = new JPanel(new FlowLayout());
        lookup.add(jLabel);
        lookup.add(memberIDField);
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

        BottomPanel = new JPanel(new FlowLayout());
        BottomPanel.add(jLabel);
    }
    private void setColumnWidths(JTable table) {
        // Set the preferred width for each column
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250); // Book Copy
        columnModel.getColumn(1).setPreferredWidth(300); // Due Date
        columnModel.getColumn(2).setPreferredWidth(300); // First Name
        columnModel.getColumn(3).setPreferredWidth(300); // Last Name
        columnModel.getColumn(4).setPreferredWidth(300); // Member ID
    }

    @Override
    public void initialize() {
        getListCheckOutRecord();
    }
}

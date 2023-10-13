package ui;

import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ListAllBooksPanel extends JPanel implements IPanel {
    ControllerInterface ci = new SystemController();

	public static  String[] col = {"ISBN", "Title", "Authors", "Max Checkout Length"};

	public static DefaultTableModel model = new DefaultTableModel(null,col);

	public static JTable table = new JTable(model);
	public static JScrollPane scrollPane = new JScrollPane(table);

    public ListAllBooksPanel() {

    }

	private void getBookTable() {
		List<Book> books = ci.allBooks();
		for (Book book : books																																																																																																																																																																																																																																																																																																																																																																																																																																					) {
			Object[] rowData = new Object[]{
					book.getIsbn(), book.getTitle(),
					book.toStringAllAuther(),

					book.getMaxCheckoutLength()
			};
			model.addRow(rowData);
		}
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		table.setEnabled(false);

		add(scrollPane);
		setColumnWidths(table);
	}

	private void setColumnWidths(JTable table) {
		// Set the preferred width for each column
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(250); // ISBN
		columnModel.getColumn(1).setPreferredWidth(300); // Title
		columnModel.getColumn(2).setPreferredWidth(700); // Authors
		columnModel.getColumn(3).setPreferredWidth(300); // Max Checkout Length
	}

	@Override
	public void initialize() {
		model.setRowCount(0);
		getBookTable();
	}
}
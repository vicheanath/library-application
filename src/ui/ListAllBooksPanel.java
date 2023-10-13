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

class ListAllBooksPanel extends JPanel {
    ControllerInterface ci = new SystemController();

    public ListAllBooksPanel() {
        List<Book> books = ci.allBooks();
		Collections.sort(books, (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn()));
		String[] columnNames = {"ISBN", "Title", "Authors", "Max Checkout Length"};
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

		for (Book book : books																																																																																																																																																																																																																																																																																																																																																																																																																																					) {
			Object[] rowData = new Object[]{
					book.getIsbn(), book.getTitle(),
					book.toStringAllAuther(),

					book.getMaxCheckoutLength()
			};
			model.addRow(rowData);
		}
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setEnabled(false);

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
}
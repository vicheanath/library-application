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
			Object[] rowData = new Object[]{book.getIsbn(), book.getTitle(), book.getAuthors(), book.getMaxCheckoutLength()};
			model.addRow(rowData);
		}
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane);
    }
}
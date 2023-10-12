package ui;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

class ListUserPanel extends JPanel {
    ControllerInterface ci = new SystemController();

    public ListUserPanel() {
        add(new JLabel("List of All Books"));
        List<Book> books = ci.allBooks();
		Collections.sort(books, (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn()));

		String[] columnNames = {"ISBN", "Title", "Authors", "Max Checkout Length"};
		
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

		for (Book book : books) {
			Object[] rowData = new Object[]{book.getIsbn(), book.getTitle(), book.getAuthors(), book.getMaxCheckoutLength()};
			model.addRow(rowData);
		}


		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane);
    }
}
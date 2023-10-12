package ui;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

class ListUserPanel extends JPanel {
    ControllerInterface ci = new SystemController();

    public ListUserPanel() {
        List<User> users = ci.allUsers();

		String[] columnNames = {"Id", "Role","password"};
		
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

		for (User user : users) {
			Object[] rowData = new Object[]{
					user.getId(),
					user.getAuthorization(),
					user.getPassword()
			};
			model.addRow(rowData);
		}


		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane);
    }
}
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
import business.LibraryMember;
import business.SystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ListAllMemberPanel extends JPanel {
    ControllerInterface ci = new SystemController();

    public ListAllMemberPanel() {
        List<LibraryMember> members = ci.allMembers();

		String[] col = {"Member ID", "First Name", "Last Name", "Telephone", "Street", "City", "State", "Zip"};

		DefaultTableModel model = new DefaultTableModel(null,col);

		for (LibraryMember p : members) {
			Object[] row = new Object[]{p.getMemberId(), p.getFirstName(), p.getLastName(), p.getTelephone(), p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip()};
			model.addRow(row);
		}
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane);
    }
}
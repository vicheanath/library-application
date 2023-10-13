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
import business.LibraryMember;
import business.SystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ListAllMemberPanel extends JPanel implements IPanel{
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
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setEnabled(false);
		setColumnWidths(table);
		add(scrollPane);
    }

	private void setColumnWidths(JTable table) {
		// Set the preferred width for each column
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(250); // Member ID
		columnModel.getColumn(1).setPreferredWidth(300); // First Name
		columnModel.getColumn(2).setPreferredWidth(500); // Last Name
		columnModel.getColumn(3).setPreferredWidth(700);  // Telephone
		columnModel.getColumn(4).setPreferredWidth(900);  // Street
		columnModel.getColumn(5).setPreferredWidth(400);  // City
		columnModel.getColumn(6).setPreferredWidth(200);  // State
		columnModel.getColumn(7).setPreferredWidth(300);  // Zip

	}

	@Override
	public void initialize() {

	}
}
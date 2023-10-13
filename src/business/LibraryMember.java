package business;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord record;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;


	}


	public void createRecord(){
		if (record == null){
			this.record = new CheckoutRecord();
		}
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public void checkOut(BookCopy copy, LocalDate checkoutDate, LocalDate toDayPlushCheckoutLength) {
		if (copy.isAvailable()) {
			this.createRecord();
			copy.changeAvailability();
			CheckoutRecordEntry entry = createEntry(copy, checkoutDate, toDayPlushCheckoutLength);
			record.addCheckoutRecordEntry(entry);
		} else {
			System.out.println("Book is not available");
		}
	}

	public CheckoutRecordEntry createEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		return new CheckoutRecordEntry(copy, checkoutDate, dueDate);
	}

	public CheckoutRecord getRecord() {
		return record;
	}

	public static String genId(List<LibraryMember> list){
		if (list == null){
			return "1";
		}
		String lastId =  list.get(0).getMemberId();
		return String.valueOf(Integer.parseInt(lastId) + 1);
	
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}
	private static final long serialVersionUID = -2226197306790714013L;
}

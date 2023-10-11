package business;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public void checkOut(BookCopy copy, LocalDate checkoutDate, LocalDate toDayPlushCheckoutLength) {
		if (copy.isAvailable()) {
			copy.changeAvailability();
			CheckoutRecordEntry entry = createEntry(copy, checkoutDate, toDayPlushCheckoutLength);
			CheckoutRecord record = new CheckoutRecord();
			record.addCheckoutRecordEntry(entry);
		} else {
			System.out.println("Book is not available");
		}
	}

	public CheckoutRecordEntry createEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		return new CheckoutRecordEntry(copy, checkoutDate, dueDate);
	}

	public String genId(List<LibraryMember> list){
		if (list.size() == 0){
			return "1";
		}
		String lastId =  list.get(list.size()-1).getMemberId();
		return String.valueOf(lastId +1);
	
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}
	private static final long serialVersionUID = -2226197306790714013L;
}

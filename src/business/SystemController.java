package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import librarysystem.*;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private static LibWindow[] allWindows = {
			LibrarySystem.INSTANCE,
			LoginWindow.INSTANCE,
			AllMemberIdsWindow.INSTANCE,
			librarysystem.StarterPage.INSTANCE,
			AllBookIdsWindow.INSTANCE,
			librarysystem.AddNewMember.INSTANCE
	};
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		LibrarySystem.hideAllWindows();
		librarysystem.StarterPage.INSTANCE.init(currentAuth);
		Util.centerFrameOnDesktop(librarysystem.StarterPage.INSTANCE);
		librarysystem.StarterPage.INSTANCE.setVisible(true);

		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	@Override
	public void AddNewMember(String memberId, String fname, String lname, String street, String city, String state, String zip, String phone) {
		Address address = new Address(street,city,state,zip);
		System.out.println(memberId);
		LibraryMember member = new LibraryMember(memberId,fname,lname,phone,address);
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		dataAccessFacade.saveNewMember(member);
	}

	@Override
	public List<Book> allBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}
	
	@Override
	public List<LibraryMember> allMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
		return retval;
	}

	public LocalDate todayPlusCheckoutLength(int maxCheckoutLength) {
		return LocalDate.now().plusDays(maxCheckoutLength);
	}

	@Override
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		//TODO: implement checkoutBook
		DataAccess da = new DataAccessFacade();
		// 1. serach member by memberId
		da.searchMember(memberId);
		// 2. search book by isbn
		Book book = da.searchBook(isbn);
		// 3. check if the book is available
		if (book.isAvailable()) {
			// 4. get next available copy book
			BookCopy copy = book.getNextAvailableCopy();
			// 5. get max content checkout
			int maxCheckoutLength = book.getMaxCheckoutLength();
			// 6. checkCoutRecordEntry(copy, maxCheckoutLength)
			LibraryMember member = da.searchMember(memberId);

			member.checkOut(copy, LocalDate.now(), todayPlusCheckoutLength(maxCheckoutLength));
		} else {
			throw new LibrarySystemException("Book is not available");
		}
	}



	public void addNewBook(String isbn, String title, String maxCheckoutLength, String authorFirst,
						   String authorLast, String telephone,String bio,String street, String city, String state, String zip) {
		List<Author> authorsnew = new ArrayList<>();
		Address address = new Address(street, city, state, zip);
		Author author = new Author(authorFirst, authorLast,telephone,address,bio);
		authorsnew.add(author);
		Book book = new Book(isbn,title,Integer.valueOf(maxCheckoutLength),authorsnew);
		System.out.println(book);
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		dataAccessFacade.saveNewBook(book);
	}

}

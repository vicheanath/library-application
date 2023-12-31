package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	public static User currentUser = null;

	
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
		currentUser = map.get(id);
		// LibrarySystem.hideAllWindows();
		// librarysystem.StarterPage.INSTANCE.init(currentAuth);
		// Util.centerFrameOnDesktop(librarysystem.StarterPage.INSTANCE);
		// librarysystem.StarterPage.INSTANCE.setVisible(true);

		
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
	public void addNewMember(String memberId, String fname, String lname, String street, String city, String state, String zip, String phone) {
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
	@Override
	public List<User> allUsers() {
		DataAccess da = new DataAccessFacade();
		List<User> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().values());
		return retval;
	}

	public LocalDate todayPlusCheckoutLength(int maxCheckoutLength) {
		return LocalDate.now().plusDays(maxCheckoutLength);
	}

	/*
	 * checkoutBook
	 * @param memberId
	 * @param isbn
	 */
	@Override
	public void checkoutBook(String memberId, String isbn)  {
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
//			LocalDate date = LocalDate.parse("2023-09-20");
//			LocalDate date12 = LocalDate.parse("2023-10-10");
//
//
//			member.checkOut(copy, date, date12);




			da.saveNewMember(member);
			copy.setAvailable(false);
			Book bookReturned = copy.getBook();
			da.saveNewBook(bookReturned);



		} else {
			throw new NullPointerException("Book is not available");
		}
	}

	@Override
	public Book getBookById(String isbn) {
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		HashMap<String, Book> hashMap= dataAccessFacade.readBooksMap();
		return hashMap.get(isbn);
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


	public LibraryMember getMemberId(String memberId) {
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		HashMap<String, LibraryMember> hashMap= dataAccessFacade.readMemberMap();
		return hashMap.get(memberId);
	}
	public List<CheckOutRecordAllMember>  getCheckOutRecordEntry(String memberId){
		List<CheckOutRecordAllMember>  entries = new ArrayList<CheckOutRecordAllMember>();
		SystemController systemController = new SystemController();
		List<LibraryMember> memberList = systemController.allMembers();

		boolean isMember = false;
		for (LibraryMember member: memberList) {
			if (member.getMemberId().equals(memberId)) {
				isMember = true;
				continue;
			}
		}
		if (!isMember) {
			throw new NullPointerException("Member is not available");
		}

		for (LibraryMember member:memberList){
			if (member.getRecord()==null){
				continue;
			}
			if (member.getMemberId().equals(memberId)){
				List<CheckoutRecordEntry> entry = member.getRecord().getCheckoutRecordEntries();
				for (CheckoutRecordEntry checkoutRecordEntry: entry){
					entries.add(new CheckOutRecordAllMember(member, checkoutRecordEntry.getBookCopy(),checkoutRecordEntry.getDueDate()));
				}
			}
		}
		return entries;
	}



	public List<CheckOutRecordAllMember> getCheckOutRecordEntryAllMembers(){
		List<CheckOutRecordAllMember>  entries = new ArrayList<CheckOutRecordAllMember>();
		SystemController systemController = new SystemController();
		List<LibraryMember> memberList = systemController.allMembers();

		for (LibraryMember member:memberList){
			if (member.getRecord()==null){
				continue;
			}
			List<CheckoutRecordEntry> entry = member.getRecord().getCheckoutRecordEntries();
			for (CheckoutRecordEntry checkoutRecordEntry: entry){
				entries.add(new CheckOutRecordAllMember(member, checkoutRecordEntry.getBookCopy(),checkoutRecordEntry.getDueDate()));
			}

		}
		return entries;
	}


	public List<BookDueDate> getListBookCopyOverdue(String isbn){
       SystemController systemController = new SystemController();
       List<LibraryMember> memberList = systemController.allMembers();

	   List<BookDueDate> bookDueDates = new ArrayList<>();
	   List<Book> bookList = systemController.allBooks();
	   if(!bookList.contains(systemController.getBookById(isbn))){
		   	   	throw new NullPointerException("Book is not available");
	   }

       for (LibraryMember member:memberList){
		   if (member.getRecord()==null){
			   continue;
		   }
		   CheckoutRecord checkoutRecords = member.getRecord();

		   List<CheckoutRecordEntry> checkoutRecordEntries = checkoutRecords.getCheckoutRecordEntries();


           for (CheckoutRecordEntry entry:checkoutRecordEntries) {
			   if (entry.getDueDate().isBefore(LocalDate.now())) {
				   Book book = entry.getBookCopy().getBook();
				   if (book.getIsbn().equals(isbn)) {
					   BookDueDate bookDueDate = new BookDueDate(entry.getBookCopy().getBook().getIsbn(),
							   entry.getBookCopy().getBook().getTitle(),
							   entry.getBookCopy().getCopyNum(),
							   member.getFirstName(),
							   entry.getDueDate().toString());

					   bookDueDates.add(bookDueDate);

				   }

			   }
		   }
       }

	   return  bookDueDates;
   }


	public static void main(String[] args) {
		SystemController systemController = new SystemController();
		/*
		for (LibraryMember member:memberList12){
			System.out.println(member);
		}
		for (Book book:bookList){
			System.out.println(book);
		}
//		systemController.testingCheckout();
		System.out.println(systemController.getListBookCopyOverdue("23-11451"));

//		systemController.whetherBookCopyOverdue12("1008");*/
		for (CheckOutRecordAllMember record :systemController.getCheckOutRecordEntryAllMembers()){
			System.out.println(record);
		}

	}


		public void addCopyOfBookToCollection (Book book){
			DataAccessFacade dataAccessFacade = new DataAccessFacade();
			dataAccessFacade.saveNewBook(book);
		}

}

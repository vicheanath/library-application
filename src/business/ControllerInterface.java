package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addNewMember(String memberId, String fname, String lname, String street,
							 String city, String state, String zip, String phone);

	public List<Book> allBooks();
	public List<LibraryMember> allMembers();

	public List<User> allUsers();
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;

	public Book getBookById(String isbn);

	public void addCopyOfBookToCollection(Book book);
	
}

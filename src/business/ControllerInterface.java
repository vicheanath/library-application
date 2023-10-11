package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();

	public List<Book> allBooks();
	public List<LibraryMember> allMembers();
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	
}

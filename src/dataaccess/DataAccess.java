package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	public LibraryMember searchMember(String memberId) throws NullPointerException;
	public Book searchBook(String isbn) throws NullPointerException;
}

package business;

import java.time.LocalDate;

public class CheckOutRecordAllMember {

    private LibraryMember libraryMember;


    private BookCopy bookCopy;
    private LocalDate DueDate;

    public CheckOutRecordAllMember(LibraryMember libraryMember, BookCopy bookCopy, LocalDate dueDate) {
        this.libraryMember = libraryMember;
        this.bookCopy = bookCopy;
        DueDate = dueDate;
    }

    @Override
    public String toString() {
        return "CheckOutRecordAllMember{" +
                "libraryMember=" + libraryMember +
                ", bookCopy=" + bookCopy +
                ", DueDate=" + DueDate +
                '}';
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }
}

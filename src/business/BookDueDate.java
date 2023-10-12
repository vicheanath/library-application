package business;

public class BookDueDate{
    private String isbn;
    private String title;
    private Integer copyNum;
    private String firstName;
    private String dueDate;

    public BookDueDate(String isbn, String title, Integer copyNum, String firstName, String dueDate) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.copyNum = copyNum;
        this.firstName = firstName;
        this.dueDate = dueDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        	return title;
    }

    public void setTitle(String title) {
        	this.title = title;
    }

    public Integer getCopyNum() {
        	return copyNum;
    }

    public void setCopyNum(Integer copyNum) {
        	this.copyNum = copyNum;
    }

    public String getFirstName() {
        	return firstName;
    }

    public void setFirstName(String firstName) {
        	this.firstName = firstName;
    }

    public String getDueDate() {
        	return dueDate;
    }

    public void setDueDate(String dueDate) {
        	this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "BookDueDate [isbn=" + isbn + ", title=" + title + ", copyNum=" + copyNum + ", firstName=" + firstName
                + ", dueDate=" + dueDate + "]";
    }

}



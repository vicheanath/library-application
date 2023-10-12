package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
        private static final long serialVersionUID = -63976228084869815L;
        private BookCopy bookCopy;
        private LocalDate dueDate;
        private LocalDate returnDate;

        public CheckoutRecordEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
                this.bookCopy = bookCopy;
              
                this.dueDate = dueDate;
        }

        public BookCopy getBookCopy() {
                return bookCopy;
        }

        public void setBookCopy(BookCopy bookCopy) {
                this.bookCopy = bookCopy;
        }

        public LocalDate getDueDate() {
                return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
                this.dueDate = dueDate;
        }

        public LocalDate getReturnDate() {
                return returnDate;
        }

        public void setReturnDate(LocalDate returnDate) {
                this.returnDate = returnDate;
        }

        @Override
        public String toString() {
                return "CheckoutRecordEntry [bookCopy=" + bookCopy + ", dueDate=" + dueDate + ", returnDate=" + returnDate
                                + "]";
        }

        public String getBookCopyId() {
                return bookCopy.getBook().getIsbn() + "-" + bookCopy.getCopyNum();
        }
}

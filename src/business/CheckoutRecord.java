package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = -6323476228084869815L;
    private List<CheckoutRecordEntry> checkoutRecordEntries;
    private LibraryMember libraryMember;

    public CheckoutRecord() {
        this.checkoutRecordEntries = new ArrayList<>();
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }

    public void setCheckoutRecordEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
        this.checkoutRecordEntries = checkoutRecordEntries;
    }
}

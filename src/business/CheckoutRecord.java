package business;

import java.util.List;

public class CheckoutRecord {
    private List<CheckoutRecordEntry> checkoutRecordEntries;

    public CheckoutRecord() {}

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

package ui;

import javax.swing.JComponent;

public class BookRuleSet implements RuleSet {

    private AddNewBookPanel addNewBookPanel;

    @Override
    public void applyRules(JComponent ob) throws RuleException {
        addNewBookPanel = (AddNewBookPanel) ob;
        nonemptyRule();
        idNumericRule();
        zipNumericRule();
        stateRule();
        idNotZipRule();
    }


    private void nonemptyRule() throws RuleException {
        if (addNewBookPanel.getISBNField().trim().isEmpty() ||
                addNewBookPanel.getBookTitle().trim().isEmpty() ||
                addNewBookPanel.getMaxCheckoutLength().trim().isEmpty() ||
                addNewBookPanel.getAuthorFirstName().trim().isEmpty() ||
                addNewBookPanel.getAuthorLastName().trim().isEmpty() ||
                addNewBookPanel.getTelephoneNumber().trim().isEmpty() ||
                addNewBookPanel.getAuthorBio().trim().isEmpty() ||
                addNewBookPanel.getAuthorStreetField().trim().isEmpty() ||
                addNewBookPanel.getAuthorCityField().trim().isEmpty() ||
                addNewBookPanel.getAuthorStateField().trim().isEmpty() ||
                addNewBookPanel.getAuthorZipField().trim().isEmpty()
                ) {
            throw new RuleException("All fields must be non-empty!");
        }
    }

    private void idNumericRule() throws RuleException {
        String val = addNewBookPanel.getMaxCheckoutLength().trim();
        try {
            Integer.parseInt(val);
            //val is numeric
        } catch (NumberFormatException e) {
            throw new RuleException("Max checkout length must be numeric");
        }
    }

    private void zipNumericRule() throws RuleException {
        String val = addNewBookPanel.getAuthorZipField().trim();
        try {
            Integer.parseInt(val);
            //val is numeric
        } catch (NumberFormatException e) {
            throw new RuleException("Zipcode must be numeric");
        }
        if (val.length() != 5) throw new RuleException("Zipcode must have 5 digits");
    }

    private void stateRule() throws RuleException {
        String state = addNewBookPanel.getAuthorStateField().trim();
        if (state.length() != 2) throw new RuleException("State field must have two characters");
        if (!Util.isInRangeAtoZ(state.charAt(0))
                || !Util.isInRangeAtoZ(state.charAt(1))) {
            throw new RuleException("Characters is state field must be in range A-Z");
        }
    }


    private void idNotZipRule() throws RuleException {
        String zip = addNewBookPanel.getAuthorZipField().trim();
        String id = addNewBookPanel.getISBNField().trim();
        if (zip.equals(id)) {
            throw new RuleException("ISBN and Zipcode cannot be the same");
        }
    }


    
}

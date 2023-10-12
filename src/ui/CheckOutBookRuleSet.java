package ui;

import javax.swing.*;

public class CheckOutBookRuleSet implements RuleSet{

    CheckoutBookPanel checkoutBookPanel;

    @Override
    public void applyRules(JComponent ob) throws RuleException  {
        checkoutBookPanel = (CheckoutBookPanel) ob;
        nonemptyRule();
        idNumericRule();
    }

    private void nonemptyRule() throws RuleException {
        if(checkoutBookPanel.getMemberIdField().trim().isEmpty() ||
                checkoutBookPanel.getBookIsbnField().trim().isEmpty()
                ) {
            throw new RuleException("All fields must be non-empty!");
        }
    }

    private void idNumericRule() throws RuleException {
        String val = checkoutBookPanel.getMemberIdField().trim();
        try {
            Integer.parseInt(val);
            //val is numeric
        } catch (NumberFormatException e) {
            throw new RuleException("ID must be numeric");
        }
    }
}

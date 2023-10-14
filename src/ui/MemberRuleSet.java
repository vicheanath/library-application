package ui;

import javax.swing.JComponent;


public class MemberRuleSet implements RuleSet {
    private AddMemberPanel addMemberPanel;

	@Override
	public void applyRules(JComponent ob) throws RuleException {
		addMemberPanel = (AddMemberPanel) ob;
		nonemptyRule();
		idNumericRule();
		zipNumericRule();
		stateRule();
		idNotZipRule();
	}

	private void nonemptyRule() throws RuleException {
		if(addMemberPanel.getMemberIdField().trim().isEmpty() ||
				addMemberPanel.getFirstNameField().trim().isEmpty() ||
				addMemberPanel.getLastNameField().trim().isEmpty() ||
				addMemberPanel.getStreetField().trim().isEmpty() ||
				addMemberPanel.getCityField().trim().isEmpty() ||
				addMemberPanel.getStateField().trim().isEmpty() ||
				addMemberPanel.getZipField().trim().isEmpty() ||
				addMemberPanel.getPhoneField().trim().isEmpty()
				) {
			throw new RuleException("All fields must be non-empty!");
		}
	}

	private void idNumericRule() throws RuleException {
		String val = addMemberPanel.getMemberIdField().trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("ID must be numeric");
		}		
	}

	private void zipNumericRule() throws RuleException {
		String val = addMemberPanel.getZipField().trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("Zipcode must be numeric");
		}
		if(val.length() != 5) throw new RuleException("Zipcode must have 5 digits");
	}

	private void stateRule() throws RuleException {
		String state = addMemberPanel.getStateField().trim();
		if(state.length() != 2) throw new RuleException("State field must have two characters");
		if(!Util.isInRangeAtoZ(state.charAt(0)) 
				|| !Util.isInRangeAtoZ(state.charAt(1))) {
			throw new RuleException("Characters is state field must be in range A-Z");
		}
	}

	private void idNotZipRule() throws RuleException {
		String zip = addMemberPanel.getZipField().trim();
		String id = addMemberPanel.getMemberIdField().trim();
		if(zip.equals(id)) throw new RuleException("ID may not be the same as zipcode");
	}
}

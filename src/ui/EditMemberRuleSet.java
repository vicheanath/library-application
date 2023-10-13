package ui;

import javax.swing.*;


public class EditMemberRuleSet implements RuleSet {
    private EditMemberPanel editMemberPanel;

	@Override
	public void applyRules(JComponent ob) throws RuleException {
		editMemberPanel = (EditMemberPanel) ob;
		nonemptyRule();
		idNumericRule();
		zipNumericRule();
		stateRule();
		idNotZipRule();
	}

	private void nonemptyRule() throws RuleException {
		if(editMemberPanel.getMemberIdField().trim().isEmpty() ||
				editMemberPanel.getFirstNameField().trim().isEmpty() ||
				editMemberPanel.getLastNameField().trim().isEmpty() ||
				editMemberPanel.getStreetField().trim().isEmpty() ||
				editMemberPanel.getCityField().trim().isEmpty() ||
				editMemberPanel.getStateField().trim().isEmpty() ||
				editMemberPanel.getZipField().trim().isEmpty() ||
				editMemberPanel.getPhoneField().trim().isEmpty()
				) {
			throw new RuleException("All fields must be non-empty!");
		}
	}

	private void idNumericRule() throws RuleException {
		String val = editMemberPanel.getMemberIdField().trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("ID must be numeric");
		}		
	}

	private void zipNumericRule() throws RuleException {
		String val = editMemberPanel.getZipField().trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("Zipcode must be numeric");
		}
		if(val.length() != 5) throw new RuleException("Zipcode must have 5 digits");
	}

	private void stateRule() throws RuleException {
		String state = editMemberPanel.getStateField().trim();
		if(state.length() != 2) throw new RuleException("State field must have two characters");
		if(!Util.isInRangeAtoZ(state.charAt(0)) 
				|| !Util.isInRangeAtoZ(state.charAt(1))) {
			throw new RuleException("Characters is state field must be in range A-Z");
		}
	}
	
	

	private void idNotZipRule() throws RuleException {
		String zip = editMemberPanel.getZipField().trim();
		String id = editMemberPanel.getMemberIdField().trim();
		if(zip.equals(id)) throw new RuleException("ID may not be the same as zipcode");
	}
}

package ui;

import javax.swing.JComponent;

public interface RuleSet {
	public void applyRules(JComponent ob) throws RuleException;
}

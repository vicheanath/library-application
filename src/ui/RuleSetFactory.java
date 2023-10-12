package ui;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JComponent;


final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends JComponent>, RuleSet> map = new HashMap<>();
	static {
		map.put(AddMemberPanel.class, new MemberRuleSet());
		map.put(AddNewBookPanel.class, new BookRuleSet());
	}
	public static RuleSet getRuleSet(JComponent c) {
		Class<? extends JComponent> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException(
					"No RuleSet found for this JComponent");
		}
		return map.get(cl);
	}
}

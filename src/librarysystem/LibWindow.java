package librarysystem;

import dataaccess.Auth;

public interface LibWindow {
	void init();
	boolean isInitialized();
	void isInitialized(boolean val);
	void setVisible(boolean b);
}


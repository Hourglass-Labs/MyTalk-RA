/*
 * Filename: PasswordRecoverDisplay.java
 * Package: it.hourglass.myTalk.client.presenter.display
 * Author: Umberto Martinati
 * Date: 2013/08/31
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/09/10  | Approvazione interfaccia
 *  ---------+------------+------------------
 *  0.1    | 2013/08/31  | |Codifica Interfaccia
 * This software is distributed under GNU/GPL 2.0.
 */

package it.hourglass.myTalk.client.presenter.display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public interface PasswordRecoverDisplay {
	HasClickHandlers getRequestButton();
	HasClickHandlers getContineuButton();
	HasClickHandlers getConfirmButton();
	void switchFase2();
	void switchFase3();
	void showErrorFase1(String errore);
	void showErrorFase2(String errore);
	void showErrorFase3(String errore);
	TextBox getConfirmCode();
	TextBox getEmail();
	PasswordTextBox getPassword();
	PasswordTextBox getConfirmPassword();
	AbsolutePanel getView();
    void showLoginError(String error);
}

/*
 * Filename: RegistrationDisplay.java
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
import com.google.gwt.user.client.ui.HasValue;

public interface RegistrationDisplay {
	HasClickHandlers getRegisterButton();
	HasClickHandlers getShowConfirmCodeButton();
	HasClickHandlers getConfirmCodeButton();
	HasValue<String> getUsername();
	HasValue<String> getPassword();
	HasValue<String> getRePassword();
	HasValue<String> getEmail();
	HasValue<String> getName();
	HasValue<String> getLastName();
	HasValue<String> getAddress();
	HasValue<String> getConfirmCode();
	HasValue<String> getConfirmUniqueId();
	String getGender();
	String getBirthDay();
	String getBirthMonth();
	String getBirthYear();
	void setWrongUsername(String err);
	void setWrongPassword(String err);
	void setWrongEmail(String err);
	void setWrongName(String err);
	void setWrongLastName(String err);
	void setWrongDate(String err);
	void setOutcome(String result);
	void setConfirmationOutcome(String result);
	void changeView();
	AbsolutePanel getView();
}

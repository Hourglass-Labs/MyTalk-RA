/*
 * Filename: PersonalDataProfileMangementDisplay.java
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

public interface PersonalDataProfileManagementDisplay {
	public void change();
	public void visualize();
	public void showError(String error);
	public HasValue<String> getNameTextBox();
	public HasValue<String> getLastNameTextBox();
	public HasValue<String> getEmailTextBox();
	public String getSexListBox(); 
	public String getDayListBox();
	public String getMonthListkBox();
	public String getYearListBox();
	public HasValue<String> getpasswordTextBox();
	public HasValue<String> getConfirmPasswordTextBox();
	public HasClickHandlers getChangeButton();
	public HasClickHandlers getConfirmButton();
	public AbsolutePanel getView();
}

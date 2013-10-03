/*
 * Filename: PopupMessageDispaly.java
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
import com.google.gwt.user.client.ui.HasValue;

public interface PopupMessageDisplay {
	HasClickHandlers getSendButton();
	HasClickHandlers getCancellButton();
	HasValue<String> getText();
	void error();
	void remove();
}

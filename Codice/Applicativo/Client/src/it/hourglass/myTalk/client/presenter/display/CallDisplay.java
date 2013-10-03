/*
 * Filename: CallDisplay.java
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
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public interface CallDisplay {
	public  void setCallname(String n);
	public	Label getCalee();
	public  HasClickHandlers getSendButton(); 
	public  HasClickHandlers getCallButton(); 
	public  HasClickHandlers getCloseButton();
	public  Video getLocalVideo();
	public  Video getGuestVideo();
	public  TextArea getChat();
	public  TextBox getByteD();
	public  TextBox getByteU();
	public  TextBox getLenght();
	public  TextBox getcallUserTextBox();
	public  TextBox getTextBoxInvia();
	public  String getStreamRequest();
	public  AbsolutePanel getView();
	public  void nascondi();
	public  void mostra();
}

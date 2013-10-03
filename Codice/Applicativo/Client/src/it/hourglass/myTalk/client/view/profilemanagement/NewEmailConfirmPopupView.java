/*
 * Filename: NewEmailConfirmPopup.java
 * Package: it.hourglass.myTalk.client.view.profilemanagement
 * Author: Sasa Ilievski
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/06/19 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.view.profilemanagement;

import it.hourglass.myTalk.client.presenter.display.NewEmailConfirmPopupDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class NewEmailConfirmPopupView implements NewEmailConfirmPopupDisplay{
	private DialogBox dialogBoxMail;
	private TextBox code;
	private Button confirm;
	private Button cancel;
	private Label errorLabel;
	
	public NewEmailConfirmPopupView()
	{
		dialogBoxMail = new DialogBox();
		dialogBoxMail.setGlassEnabled(true);
		dialogBoxMail.setAutoHideOnHistoryEventsEnabled(true);
		AbsolutePanel layout = new AbsolutePanel();
		layout.getElement().setId("PopUpMail");
		dialogBoxMail.setWidget(layout);
		
		Label codeLabel= new Label ("Inserire codice inviato via email:");
		codeLabel.getElement().setId("TestoCodice");
		layout.add(codeLabel);
		
		code= new TextBox();
		code.getElement().setId("Codice");
		layout.add(code);
		
		cancel= new Button("Annulla");
		cancel.getElement().setId("Annulla");
		layout.add(cancel);
		
		confirm=new Button("Continua");
		confirm.getElement().setId("Continua");
		layout.add(confirm);
		
		errorLabel = new Label ("");
		errorLabel.getElement().setId("TestoCodice");
		layout.add(errorLabel);
		
		dialogBoxMail.center();
		dialogBoxMail.show();
		
	}

	@Override
	public HasClickHandlers getConfirmButton() {
		// TODO Auto-generated method stub
		return confirm;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return cancel;
	}

	@Override
	public HasValue<String> getSecretCode() {
		// TODO Auto-generated method stub
		return code;
	}
	
	@Override
	public void setError(String error){
		errorLabel.setText(error);
	}
	
	public void close(){
		dialogBoxMail.removeFromParent();
	}
}

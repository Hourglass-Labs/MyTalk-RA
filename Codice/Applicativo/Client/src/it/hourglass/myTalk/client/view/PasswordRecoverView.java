/*
 * Filename: PasswordRecoverView.java
 * Package: it.hourglass.myTalk.client.view
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
 *  0.1     | 2013/06/10 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.PasswordRecoverDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordRecoverView implements PasswordRecoverDisplay{
	private AbsolutePanel content;
	private AbsolutePanel fase1;
	private AbsolutePanel fase2;
	private AbsolutePanel fase3;
	private TextBox textBoxEmail;
	private TextBox confirmCode;
	private PasswordTextBox newPassword;
	private PasswordTextBox newPasswordConfirm;
	private Label Email;
	private Label Text1;
	private Label Text2;
	private Label Text3;
	private Label Text4;
	private Button RequestRecover;
	private Button ContinueButton;
	private Button ConfirmButton;
	private Grid grid;
	private Label Error;
	
	public PasswordRecoverView()
	{
		Error=new Label("");
		Error.getElement().setId("Errore");
		content= new AbsolutePanel();
		content.getElement().setId("RecuperoPassword");
		
		fase1= new AbsolutePanel();
		content.add(fase1);
		Text1 = new Label("Gentile Utente, per avviare la procedura di recupero password, ti preghiamo di inserire il tuo indirizzo email associato all'account nel seguente campo.");
		fase1.add(Text1);
		Email = new Label("Email :");
		Email.getElement().setId("Email");
		fase1.add(Email);
		textBoxEmail = new TextBox();
		fase1.add(textBoxEmail);
		RequestRecover = new Button("Richiedi");
		fase1.add(RequestRecover);
		Text2 = new Label("Ti verr\u00E0 inviato al pi\u00F9 presto un codice segreto che dovrai inserire nei prossimi passi.");
		fase1.add(Text2);
		
		
		fase2= new AbsolutePanel();
		Text3 = new Label("Inserire il codice segreto :");
		fase2.add(Text3);
		confirmCode = new TextBox();
		fase2.add(confirmCode);
		ContinueButton = new Button("Continua");
		fase2.add(ContinueButton);

		
		fase3= new AbsolutePanel();
		Text4 = new Label("Compilare i seguenti campi per modificare la password :");
		fase3.add(Text4);
		grid = new Grid(2, 2);
		fase3.add(grid);
		Label lblNuovaPassword = new Label("Nuova Password:");
		grid.setWidget(0, 0, lblNuovaPassword);
		newPassword = new PasswordTextBox();
		grid.setWidget(0, 1, newPassword);
		Label lblRNuovaPassword = new Label("Reinserisci Nuova Password :");
		grid.setWidget(1, 0, lblRNuovaPassword);
		newPasswordConfirm = new PasswordTextBox();
		grid.setWidget(1, 1, newPasswordConfirm);
		ConfirmButton = new Button("Conferma");
		fase3.add(ConfirmButton);

	}
		
	@Override
	public AbsolutePanel getView()
	{
		return content;
	}


	@Override
	public HasClickHandlers getRequestButton() {
		return RequestRecover;
	}


	@Override
	public HasClickHandlers getContineuButton() {
		return ContinueButton;
	}

	@Override
	public HasClickHandlers getConfirmButton() {
		return ConfirmButton;
	}


	@Override
	public void switchFase2() {
		content.clear();
		content.add(fase2);
	}


	@Override
	public void switchFase3() {
		content.clear();
		content.add(fase3);
	}


	@Override
	public void showErrorFase1(String errore) {
		this.Error.setText(errore);
		fase1.add(Error);
		
	}


	@Override
	public void showErrorFase2(String errore) {
		this.Error.setText(errore);
		fase2.add(Error);
		
	}

	@Override
	public void showErrorFase3(String errore) {
		this.Error.setText(errore);
		fase3.add(Error);
		
	}


	@Override
	public TextBox getEmail() {
		return textBoxEmail;
	}


	@Override
	public PasswordTextBox getPassword() {
		return newPassword;
	}



	@Override
	public PasswordTextBox getConfirmPassword() {
		return newPasswordConfirm;
	}



	@Override
	public TextBox getConfirmCode() {
		return confirmCode;
	}

	@Override
	public void showLoginError(String error) {
		this.Error.setText(error);
		content.add(Error);
	}


}
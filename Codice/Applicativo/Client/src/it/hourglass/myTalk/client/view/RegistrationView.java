/*
 * Filename: RegistrationView.java
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
 *  0.1     | 2013/06/2 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;


import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HorizontalPanel;

import it.hourglass.myTalk.client.presenter.display.RegistrationDisplay;

public class RegistrationView implements RegistrationDisplay{
	private AbsolutePanel content;
	private Button registrationButton;
	private Button showConfirmCodeButton;
	private Button confirmCodeButton;
	private TextBox textBoxUsername;
	private PasswordTextBox textBoxPassword;
	private PasswordTextBox textBoxRePassword;
	private TextBox textBoxEmail;
	private TextBox textBoxName;
	private TextBox textBoxLastName;
	private TextBox textBoxAddress;
	private ListBox comboBoxDay;
	private ListBox comboBoxMonth;
	private ListBox comboBoxYear;
	private ListBox comboBoxGender;
	private Label wrongUsername;
	private Label wrongPassword;
	private Label wrongEmail;
	private Label wrongName;
	private Label wrongLastName;
	private Label wrongDate;
	private Label outcome;
	private Label confirmationOutcome;
	private TextBox confirmCode;
	private TextBox confirmUniqueId;

	public RegistrationView()
	{
		content= new AbsolutePanel();
		content.setStyleName("Registrazione");


		Grid grid = new Grid(10, 3);
		content.add(grid);

		Label lblUsername = new Label("Username:");
		grid.setWidget(0, 0, lblUsername);


		textBoxUsername = new TextBox();
		grid.setWidget(0, 1, textBoxUsername);

		wrongUsername = new Label();
		grid.setWidget(0, 2, wrongUsername);


		Label lblPassword = new Label("Password:");
		grid.setWidget(1, 0, lblPassword);
                
        textBoxPassword = new PasswordTextBox();
		grid.setWidget(1, 1, textBoxPassword);

		wrongPassword = new Label();
		grid.setWidget(1, 2, wrongPassword);
                
        Label lblRePassword = new Label("Reinserisci la password:");
		grid.setWidget(2, 0, lblRePassword);

		textBoxRePassword = new PasswordTextBox();
		grid.setWidget(2, 1, textBoxRePassword);


		Label lblEmail = new Label("Email:");
		grid.setWidget(3, 0, lblEmail);

		textBoxEmail = new TextBox();
		grid.setWidget(3, 1, textBoxEmail);

		wrongEmail = new Label();
		grid.setWidget(3, 2, wrongEmail);

		Label lblName = new Label("Nome:");
		grid.setWidget(4, 0, lblName);

		textBoxName = new TextBox();
		grid.setWidget(4, 1, textBoxName);

		wrongName = new Label();
		grid.setWidget(4, 2, wrongName);

		Label lblLastName = new Label("Cognome:");
		grid.setWidget(5, 0, lblLastName);

		textBoxLastName = new TextBox();
		grid.setWidget(5, 1, textBoxLastName);

		wrongLastName = new Label();
		grid.setWidget(5, 2, wrongLastName);
	
		Label lblDataDiNascita = new Label("Data di Nascita:");
		grid.setWidget(6, 0, lblDataDiNascita);

		HorizontalPanel horizontalPanelDataNascita = new HorizontalPanel();
		grid.setWidget(6, 1, horizontalPanelDataNascita);

		comboBoxDay = new ListBox();
		for(int i = 1; i < 10 ; i++)
			comboBoxDay.addItem("0"+String.valueOf(i));
		for(int i = 10; i < 32 ; i++)
			comboBoxDay.addItem(String.valueOf(i));
		horizontalPanelDataNascita.add(comboBoxDay);

		comboBoxMonth = new ListBox();
		for(int i = 1; i < 10 ; i++)
			comboBoxMonth.addItem("0"+String.valueOf(i));
		for(int i = 10; i < 13 ; i++)
			comboBoxMonth.addItem(String.valueOf(i));
		horizontalPanelDataNascita.add(comboBoxMonth);

		comboBoxYear = new ListBox();
		Date today = new Date();
		int year = today.getYear(); //GWT non supporta Calendar
		for(int i = 0; i < 100 ; i++)
		comboBoxYear.addItem(String.valueOf(1900-i+year));
		horizontalPanelDataNascita.add(comboBoxYear);

		wrongDate = new Label();
		grid.setWidget(6, 2, wrongDate);

		Label lblGender = new Label("Sesso");
		grid.setWidget(7, 0, lblGender);

		comboBoxGender = new ListBox();
		comboBoxGender.addItem("M");
		comboBoxGender.addItem("F");
		grid.setWidget(7, 1, comboBoxGender);
		outcome = new Label("");
		grid.setWidget(9, 1, outcome);
		registrationButton = new Button("Registrati");
		registrationButton.setStyleName("BtnRegistra");
		content.add(registrationButton);
		showConfirmCodeButton= new Button("Conferma registrazione");
		showConfirmCodeButton.setStyleName("BtnRegistra");
		content.add(showConfirmCodeButton);


		confirmCode = new TextBox();
		confirmUniqueId = new TextBox();
		confirmCodeButton = new Button("Conferma");


	}
	public AbsolutePanel getView()
	{
		return content;
	}
	@Override
	public HasClickHandlers getRegisterButton() {
		// TODO Auto-generated method stub
		return registrationButton;
	}

        @Override
	public HasValue<String> getUsername() {
		// TODO Auto-generated method stub
		return textBoxUsername;
	}
        
        @Override
	public HasValue<String> getEmail() {
		// TODO Auto-generated method stub
		return textBoxEmail;
	}
        
	@Override
	public HasValue<String> getPassword() {
		// TODO Auto-generated method stub
		return textBoxPassword;
	}
	@Override
	public HasValue<String> getName() {
		// TODO Auto-generated method stub
		return textBoxName;
	}
	@Override
	public HasValue<String> getLastName() {
		// TODO Auto-generated method stub
		return textBoxLastName;
	}
	@Override
	public HasValue<String> getAddress() {
		// TODO Auto-generated method stub
		return textBoxAddress;
	}
	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return comboBoxGender.getValue(comboBoxGender.getSelectedIndex());
	}
	@Override
	public HasValue<String> getRePassword() {
		// TODO Auto-generated method stub
		return textBoxRePassword;
	}
	@Override
	public String getBirthDay() {
		// TODO Auto-generated method stub
		return comboBoxDay.getValue(comboBoxDay.getSelectedIndex());
	}
	@Override
	public String getBirthMonth() {
		// TODO Auto-generated method stub
		return comboBoxMonth.getValue(comboBoxMonth.getSelectedIndex());
	}
	@Override
	public String getBirthYear() {
		// TODO Auto-generated method stub
		return comboBoxYear.getValue(comboBoxYear.getSelectedIndex());
	}

	public void setWrongUsername(String error) {
		wrongUsername.setText(error);
	}
	public void setWrongName(String error) {
		wrongName.setText(error);
	}
	public void setWrongLastName(String error) {
		wrongLastName.setText(error);
	}
	public void setWrongPassword(String error) {
		wrongPassword.setText(error);
	}
	public void setWrongDate(String error) {
		wrongDate.setText(error);
	}
	public void setWrongEmail(String error) {
		wrongEmail.setText(error);
	}
	public void setOutcome(String result){
		outcome.setText(result);
	}
	public void setConfirmationOutcome(String result){
		confirmationOutcome.setText(result);
	}
	
	
	@Override
	public HasClickHandlers getShowConfirmCodeButton() {
		return showConfirmCodeButton;
	}
	@Override
	public HasClickHandlers getConfirmCodeButton() {
		return confirmCodeButton;
	}
	@Override
	public HasValue<String> getConfirmCode() {
		return confirmCode;
	}
	@Override
	public HasValue<String> getConfirmUniqueId() {
		return confirmUniqueId;
	}
	@Override
	
	public void changeView() {
		content.clear();
		Label Text1 = new Label("Gentile Utente, per confermare la registrazione " +
				"inserisci il tuo identificativo e il codice che ti e' " +
				"stato inviato via email al momento della registrazione.");
		content.add(Text1);
		Text1.getElement().setId("TestoConferma");
		Label userLabel=new Label("Username");
		userLabel.getElement().setId("LabelConfermaId");
		content.add(userLabel);
		confirmUniqueId.getElement().setId("ConfermaIdBox");
		content.add(confirmUniqueId);
		Label confirmCodeLabel=new Label("Codice");
		confirmCodeLabel.getElement().setId("LabelConfermaCodice");
		content.add(confirmCodeLabel);
		confirmCode.getElement().setId("ConfermaCodiceBox");
		content.add(confirmCode);		
		content.add(confirmCodeButton);
		confirmationOutcome = new Label("");
		content.add(confirmationOutcome);
				
	}
}

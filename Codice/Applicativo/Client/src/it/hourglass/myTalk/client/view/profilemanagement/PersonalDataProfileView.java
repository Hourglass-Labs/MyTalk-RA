/*
 * Filename: PersonalDataProfileView.java
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
 *  0.1     | 2013/06/15 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view.profilemanagement;

import java.util.Date;

import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.presenter.display.PersonalDataProfileManagementDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class PersonalDataProfileView implements PersonalDataProfileManagementDisplay{
	
	private User user;
	
	private TextBox nameTextBox;
	private TextBox lastNameTextBox;
	private TextBox emailTextBox;
	private ListBox sexListBox; 
	private ListBox dayListBox;
	private ListBox monthListkBox;
	private ListBox yearListBox;
	private PasswordTextBox passwordTextBox;
	private PasswordTextBox confirmPasswordTextBox;
	
	private Label error;
	
	private AbsolutePanel contenent;
	private AbsolutePanel name_LastName_EmailPanel;
	private AbsolutePanel dataName_LastName_EmailPanel;
	private AbsolutePanel sex_BirthDate_PasswordPanel;
	private AbsolutePanel dataSex_BirthDate_PasswordPanel;
	private AbsolutePanel changeButtonPanel;
	
	private Button changeButton;
	private Button confirmButton;
	
	public PersonalDataProfileView()
	{
		this.user=Profile.getUser();
		
		contenent= new AbsolutePanel();
		contenent.getElement().setId("datiPersonali");
		
		contenent.add(new HTML("<h1>DATI PERSONALI</h1>"));
		
		name_LastName_EmailPanel=new AbsolutePanel();
		name_LastName_EmailPanel.getElement().setId("pannello1");
		name_LastName_EmailPanel.setStyleName("Pannello");
		contenent.add(name_LastName_EmailPanel);
		
		Label nameLable= new Label("Nome:");
		nameLable.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(nameLable);
		Label lastNameLabel= new Label("Cognome:");
		lastNameLabel.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(lastNameLabel);
		Label emailLabel= new Label("Email:");
		emailLabel.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(emailLabel);
		
		dataName_LastName_EmailPanel=new AbsolutePanel();
		dataName_LastName_EmailPanel.getElement().setId("pannello2");
		dataName_LastName_EmailPanel.setStyleName("Pannello");
		contenent.add(dataName_LastName_EmailPanel);
		
		sex_BirthDate_PasswordPanel=new AbsolutePanel();
		sex_BirthDate_PasswordPanel.getElement().setId("pannello3");
		sex_BirthDate_PasswordPanel.setStyleName("Pannello");
		contenent.add(sex_BirthDate_PasswordPanel);
		
		Label sexLabel= new Label("Sesso:");
		sexLabel.setStyleName("DatiSX");
		sex_BirthDate_PasswordPanel.add(sexLabel);
		Label dataNascita= new Label("Data di nascita:");
		dataNascita.setStyleName("DatiSX");
		sex_BirthDate_PasswordPanel.add(dataNascita);
		Label passwordLabel= new Label("Password:");
		passwordLabel.setStyleName("DatiSX");
		sex_BirthDate_PasswordPanel.add(passwordLabel);
		Label confirmPasswordLabel= new Label("Ripeti password:");
		confirmPasswordLabel.setStyleName("DatiSX");
		sex_BirthDate_PasswordPanel.add(confirmPasswordLabel);
		
		dataSex_BirthDate_PasswordPanel=new AbsolutePanel();
		dataSex_BirthDate_PasswordPanel.getElement().setId("pannello4");
		dataSex_BirthDate_PasswordPanel.setStyleName("Pannello");
		contenent.add(dataSex_BirthDate_PasswordPanel);
		
		changeButtonPanel=new AbsolutePanel();
		changeButtonPanel.getElement().setId("pannello5");
		
		changeButton=new Button("Modifica");
		changeButton.getElement().setId("Modifica");
		changeButton.setStyleName("Panello5");
		
		confirmButton=new Button("Conferma");
		confirmButton.getElement().setId("Modifica");
		confirmButton.setStyleName("Panello5");
		
		error= new Label();
		error.getElement().setId("Errore");
		changeButtonPanel.add(error);
		
		contenent.add(changeButtonPanel);	
		
		visualizeView();
		
	}
	private void visualizeView(){

		Label visaulName= new Label(user.getName());
		visaulName.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visaulName);
		Label visualLastName= new Label(user.getLastName());
		visualLastName.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visualLastName);
		Label visualEmail= new Label(user.getEmail());
		visualEmail.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visualEmail);
		
		
		Label visualSex= new Label(String.valueOf(user.getGender()));
		visualSex.setStyleName("DatiDX");
		dataSex_BirthDate_PasswordPanel.add(visualSex);
		String birthdate = (DateTimeFormat.getFormat("dd/MM/yyyy")
				.format(user.getBirthdate()));
		Label visualBirthDate = new Label(birthdate);
		visualBirthDate.setStyleName("DatiDX");
		dataSex_BirthDate_PasswordPanel.add(visualBirthDate);
		Label visualPassword= new Label("************");
		visualPassword.setStyleName("DatiDX");
		dataSex_BirthDate_PasswordPanel.add(visualPassword);
		Label visualPasswordConfirm= new Label("************");
		visualPasswordConfirm.setStyleName("DatiDX");
		dataSex_BirthDate_PasswordPanel.add(visualPasswordConfirm);
		
		
		changeButtonPanel.add(changeButton);
		

	}
	
	private void changeView(){
		
		clearLabel();
		
		nameTextBox= new TextBox();
		nameTextBox.setStyleName("DatiD");
		nameTextBox.setText(user.getName());
		dataName_LastName_EmailPanel.add(nameTextBox);
		lastNameTextBox= new TextBox();
		lastNameTextBox.setStyleName("DatiD");
		lastNameTextBox.setText(user.getLastName());
		dataName_LastName_EmailPanel.add(lastNameTextBox);
		emailTextBox= new TextBox();
		emailTextBox.setStyleName("DatiD");
		emailTextBox.setText(user.getEmail());
		dataName_LastName_EmailPanel.add(emailTextBox);
		
		sexListBox = new ListBox();
		sexListBox.addItem("M");
		sexListBox.addItem("F");
		sexListBox.setStyleName("DatiD");
		dataSex_BirthDate_PasswordPanel.add(sexListBox);
		
		HorizontalPanel birthDataPanel = new HorizontalPanel();
		
		String daylbl;
		int setDay = 0;
		String birthday= DateTimeFormat.getFormat("dd")
				.format(user.getBirthdate());
		dayListBox = new ListBox();
		for(int i = 1; i < 10 ; i++){
			daylbl = "0"+String.valueOf(i);
			dayListBox.addItem(daylbl);
			if(daylbl.equals(birthday)) 
				setDay = i-1;
		}
		for(int i = 10; i < 32 ; i++){
			daylbl = String.valueOf(i);
			dayListBox.addItem(daylbl);
			if(daylbl.equals(birthday)) 
				setDay = i-1;
		}
		
		dayListBox.setSelectedIndex(setDay);
		birthDataPanel.add(dayListBox);
		
		String monthlbl;
		int setMonth = 0;
		String birthmonth= DateTimeFormat.getFormat("MM")
				.format(user.getBirthdate());
		monthListkBox = new ListBox();
		for(int i = 1; i < 10 ; i++){
			monthlbl = "0" + String.valueOf(i);
			monthListkBox.addItem(monthlbl);
			if(monthlbl.equals(birthmonth)) 
				setMonth = i-1;
		}
		for(int i = 10; i < 13 ; i++){
			monthlbl = String.valueOf(i);
			monthListkBox.addItem(monthlbl);
			if(monthlbl.equals(birthmonth)) 
				setMonth = i-1;
		}
		monthListkBox.setSelectedIndex(setMonth);
		birthDataPanel.add(monthListkBox);
		
		yearListBox = new ListBox();
		Date today = new Date();
		int year = today.getYear(); //GWT non supporta Calendar
		int setYear = 0;
		String birthyear= DateTimeFormat.getFormat("yyyy")
				.format(user.getBirthdate());
		String yearlbl;
		
		for(int i = 0; i < 100 ; i++){
			yearlbl = String.valueOf(1900-i+year);
			yearListBox.addItem(yearlbl);
			if(yearlbl.equals(birthyear))
				setYear = i;
		}
		
		yearListBox.setSelectedIndex(setYear);
		birthDataPanel.add(yearListBox);
		
		
		birthDataPanel.setStyleName("DatiD");		
		dataSex_BirthDate_PasswordPanel.add(birthDataPanel);
		
		passwordTextBox= new PasswordTextBox();
		passwordTextBox.setStyleName("DatiD");
		String password=user.getPassword();
		passwordTextBox.setText(password);
		dataSex_BirthDate_PasswordPanel.add(passwordTextBox);
		confirmPasswordTextBox= new PasswordTextBox();
		confirmPasswordTextBox.setStyleName("DatiD");
		confirmPasswordTextBox.setText(password);
		dataSex_BirthDate_PasswordPanel.add(confirmPasswordTextBox);
		

		
		changeButtonPanel.add(confirmButton);
	}
	

	public void clearLabel(){
		dataName_LastName_EmailPanel.clear();
		dataSex_BirthDate_PasswordPanel.clear();
		changeButtonPanel.remove(changeButton);
	}
	
	public void clearTextBox(){
		dataName_LastName_EmailPanel.clear();
		dataSex_BirthDate_PasswordPanel.clear();
		changeButtonPanel.remove(confirmButton);
	}
	
	@Override
	public HasValue<String> getNameTextBox() {
		// TODO Auto-generated method stub
		return nameTextBox;
	}
	@Override
	public HasValue<String> getLastNameTextBox() {
		// TODO Auto-generated method stub
		return lastNameTextBox;
	}
	@Override
	public HasValue<String> getEmailTextBox() {
		// TODO Auto-generated method stub
		return emailTextBox;
	}
	@Override
	public String getSexListBox() {
		// TODO Auto-generated method stub
		return sexListBox.getValue(sexListBox.getSelectedIndex());
	}
	@Override
	public String getDayListBox() {
		return dayListBox.getValue(dayListBox.getSelectedIndex());
	}
	@Override
	public String getMonthListkBox() {
		return monthListkBox.getValue(monthListkBox.getSelectedIndex());
	}
	@Override
	public String getYearListBox() {
		return yearListBox.getValue(yearListBox.getSelectedIndex());
	}
	@Override
	public HasValue<String> getpasswordTextBox() {
		return passwordTextBox;
	}
	@Override
	public HasValue<String> getConfirmPasswordTextBox() {
		return confirmPasswordTextBox;
	}
	@Override
	public HasClickHandlers getChangeButton() {
		return changeButton;
	}
	@Override
	public HasClickHandlers getConfirmButton() {
		return confirmButton;
	}
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
	@Override
	public void change() {
		changeView();
		
	}
	@Override
	public void visualize() {
		clearTextBox();
		visualizeView();		
	}
	@Override
	public void showError(String error) {
		this.error.setText(error);
		contenent.add(this.error);	
	}

}

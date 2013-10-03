/*
 * Filename: PersonalDataContactProfileView.java
 * Package: it.hourglass.myTalk.client.view.contactprofile
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

package it.hourglass.myTalk.client.view.contactprofile;

import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.shared.User;


import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

/**
 * Questa classe p uno dei widget della ContactProfileView il quale si occupa di visualizzare le informazioni
 * personali del contatto richiesto.
 * @author Sasa Ilievski
 *
 */
public class PersonalDataContactProfileView implements WidgetPresenter{
	
	private AbsolutePanel contenent;
	private AbsolutePanel name_LastName_EmailPanel;
	private AbsolutePanel dataName_LastName_EmailPanel;
	private AbsolutePanel sex_BirthDate_Panel;
	private AbsolutePanel dataSex_BirthDatePanel;

	/**
	 * Costruttore della classe PersonalDataContactProfileView.
	 * Il costurttore si occupa di inizializzare il pannello principale del widget (contenent) e di inserire
	 * gli altri pannelli che conterranno i dati personali dell'user richiesto. Succesivamente inizializza varie 
	 * Label contenti le informazioni dei dati estesi
	 * @param user: contiene l'user richiesto.
	 */
	public PersonalDataContactProfileView(User user)
	{
		
		contenent= new AbsolutePanel();
		contenent.getElement().setId("datiPersonali");
		
		contenent.add(new HTML("<h1>DATI PERSONALI</h1>"));
		
		name_LastName_EmailPanel=new AbsolutePanel();
		name_LastName_EmailPanel.getElement().setId("pannello1");
		name_LastName_EmailPanel.setStyleName("Pannello");
		contenent.add(name_LastName_EmailPanel);
		
		Label nameLable= new Label("Nome :");
		nameLable.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(nameLable);
		Label lastNameLabel= new Label("Cognome :");
		lastNameLabel.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(lastNameLabel);
		Label emailLabel= new Label("Email :");
		emailLabel.setStyleName("DatiSX");
		name_LastName_EmailPanel.add(emailLabel);
		
		dataName_LastName_EmailPanel=new AbsolutePanel();
		dataName_LastName_EmailPanel.getElement().setId("pannello2");
		dataName_LastName_EmailPanel.setStyleName("Pannello");
		contenent.add(dataName_LastName_EmailPanel);
		
		Label visaulName= new Label(user.getName());
		visaulName.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visaulName);
		Label visualLastName= new Label(user.getLastName());
		visualLastName.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visualLastName);
		Label visualEmail= new Label(user.getEmail());
		visualEmail.setStyleName("DatiDX");
		dataName_LastName_EmailPanel.add(visualEmail);
		
		sex_BirthDate_Panel=new AbsolutePanel();
		sex_BirthDate_Panel.getElement().setId("pannello3");
		sex_BirthDate_Panel.setStyleName("Pannello");
		contenent.add(sex_BirthDate_Panel);
		
		Label sexLabel= new Label("Sesso :");
		sexLabel.setStyleName("DatiSX");
		sex_BirthDate_Panel.add(sexLabel);
		Label dataNascita= new Label("Data di nascita :");
		dataNascita.setStyleName("DatiSX");
		sex_BirthDate_Panel.add(dataNascita);
		
		dataSex_BirthDatePanel=new AbsolutePanel();
		dataSex_BirthDatePanel.getElement().setId("pannello4");
		dataSex_BirthDatePanel.setStyleName("Pannello");
		contenent.add(dataSex_BirthDatePanel);
		
		Label visualSex= new Label(String.valueOf(user.getGender()));
		visualSex.setStyleName("DatiDX");
		dataSex_BirthDatePanel.add(visualSex);
		String birthdate = (DateTimeFormat.getFormat("dd/MM/yyyy")
				.format(user.getBirthdate()));
		Label visualBirthDate=new Label(birthdate);
		visualBirthDate.setStyleName("DatiDX");
		dataSex_BirthDatePanel.add(visualBirthDate);
		
		
	}
	
	/**
	 * Restituisce il widget.
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
}
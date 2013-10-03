/*
 * Filename: ExtendedDataProfileManagementView.java
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
 *  0.1     | 2013/06/17 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view.profilemanagement;


import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.presenter.display.ExtendedDataProfileManagementDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
/**
 * Questa classe è uno dei widget della ProfileManagmentView il quale si occupa di visualizzare le informazioni
 * estese del proprio contatto e di renderle modificabili
 * Questa classe implementa l'inferfaccia Display della classe ExtendedDataProfileManagementPresenter.
 * @author Sasa Ilievski
 *
 */
public class ExtendedDataProfileManagementView implements ExtendedDataProfileManagementDisplay{
	
	private User user;
	
	private AbsolutePanel contenent;
	private AbsolutePanel cityLabelPanel;
	private AbsolutePanel cityPanel;
	private AbsolutePanel HometownLablePanel;
	private AbsolutePanel HometownPanel;
	private AbsolutePanel AltEmailLabelPanel;
	private AbsolutePanel AltEmailPanel;

	private AbsolutePanel descriptionPanel;
	private AbsolutePanel interestsPanel;
	private AbsolutePanel inspirationsPanel;
	private AbsolutePanel descriptionTextPanel;
	private AbsolutePanel interestsTextPanel;
	private AbsolutePanel inspirationsTextPanel;
	
	private AbsolutePanel changeButtonPanel;

	private Label error;
	
	private TextBox cityTextBox;
	private TextBox HometownTextBox;
	private TextBox AltEmailTextBox;
	private TextArea interestsTextArea;
	private TextArea descriptionsTextArea;
	private TextArea inspirationsTextArea;
	
	private Button confirm;
	private Button change;
	
	/**
	 * Costruttore della classe ExtendedDataProfileManagementView.
	 * Si occupa di inizializzare il pannello che conterrà il widget (contenent).Successivamente si occupa
	 * di inizializzare tutti i pannelli. Per ogni dato da visualizzare (tranne interests, descriptions, inspirations) sono presenti due pannelli.
	 *  Un pannello per identificare il dato (a sinistra) e un pannello in cui verrà visualizzato il dato (a destra).
	 * Per i campi dati interests descriptions inspirations invece viene aggiunta una TextArea contenente il
	 * testo corrispettivo.
	 * Successivamente si occupa di aggiungere le Label che identificano il dato e di aggiungere i button.
	 * Infine invoca il metodo Visualize
	 */
	public ExtendedDataProfileManagementView()
	{
		
		this.user=Profile.getUser();
		error=new Label("");
		error.getElement().setId("Errore");
		
		
		contenent= new AbsolutePanel();
		contenent.getElement().setId("datiEstesi");
		
		contenent.add(new HTML("<h1>DATI ESTESI</h1>"));
		
		cityLabelPanel=new AbsolutePanel();
		cityLabelPanel.getElement().setId("pannello1");
		cityLabelPanel.setStyleName("Pannello");
		contenent.add(cityLabelPanel);
		
		Label citta= new Label("Citta di residenza:");
		citta.setStyleName("DatiSX");
		cityLabelPanel.add(citta);
		
		cityPanel=new AbsolutePanel();
		cityPanel.getElement().setId("pannello2");
		cityPanel.setStyleName("Pannello");
		contenent.add(cityPanel);
		
		HometownLablePanel=new AbsolutePanel();
		HometownLablePanel.getElement().setId("pannello3");
		HometownLablePanel.setStyleName("Pannello");
		contenent.add(HometownLablePanel);
		
		Label cittaOrigine= new Label("Citta d'origine:");
		cittaOrigine.setStyleName("DatiSX");
		HometownLablePanel.add(cittaOrigine);
		
		HometownPanel=new AbsolutePanel();
		HometownPanel.getElement().setId("pannello4");
		HometownPanel.setStyleName("Pannello");
		contenent.add(HometownPanel);
		
		AltEmailLabelPanel=new AbsolutePanel();
		AltEmailLabelPanel.getElement().setId("pannello5");
		AltEmailLabelPanel.setStyleName("Pannello");
		contenent.add(AltEmailLabelPanel);
		
		Label recapitiAlt= new Label("Indirizzo email alternativo:");
		recapitiAlt.setStyleName("DatiSX");
		AltEmailLabelPanel.add(recapitiAlt);
		
		AltEmailPanel=new AbsolutePanel();
		AltEmailPanel.getElement().setId("pannello6");
		AltEmailPanel.setStyleName("Pannello");
		contenent.add(AltEmailPanel);
		
		changeButtonPanel=new AbsolutePanel();
		changeButtonPanel.getElement().setId("pannello7");
		changeButtonPanel.setStyleName("Pannello");
		contenent.add(changeButtonPanel);
		
		
		descriptionPanel=new AbsolutePanel();
		descriptionPanel.setStyleName("PannelloDesc");
		Label descriptionLabel= new Label("Descrizione personale:");
		descriptionLabel.getElement().setId("Titolo");
		descriptionPanel.add(descriptionLabel);
		
		descriptionTextPanel= new AbsolutePanel();
		descriptionTextPanel.getElement().setId("TestoDesc");
		descriptionTextPanel.getElement().getStyle().clearOverflow();
		descriptionPanel.add(descriptionTextPanel);
		contenent.add(descriptionPanel);
		
		
		interestsPanel=new AbsolutePanel();
		interestsPanel.setStyleName("PannelloDesc");
		Label interestsLabel= new Label("Interessi :");
		interestsLabel.getElement().setId("Titolo");
		interestsPanel.add(interestsLabel);
		
		interestsTextPanel= new AbsolutePanel();
		interestsTextPanel.getElement().setId("TestoDesc");
		interestsTextPanel.getElement().getStyle().clearOverflow();
		interestsPanel.add(interestsTextPanel);
		contenent.add(interestsPanel);
		
		inspirationsPanel=new AbsolutePanel();
		inspirationsPanel.setStyleName("PannelloDesc");
		Label inspirationsLabel= new Label("Ispirazioni :");
		inspirationsLabel.getElement().setId("Titolo");
		inspirationsPanel.add(inspirationsLabel);
		
		inspirationsTextPanel= new AbsolutePanel();
		inspirationsTextPanel.getElement().setId("TestoDesc");
		inspirationsTextPanel.getElement().getStyle().clearOverflow();
		inspirationsPanel.add(inspirationsTextPanel);
		contenent.add(inspirationsPanel);
		
		change=new Button("Modifica");
		change.getElement().setId("Modifica");
		confirm=new Button("Conferma");
		confirm.getElement().setId("Modifica");
		
		visualize();
		
	}
	
	/**
	 * Questo metodo si occupa di inserire per ogni dato nel suo pannello una label con il
	 * dato corrispettivo.
	 */
	public void visualize(){
		Label cityUser= new Label(user.getCurrentLocation());
		cityUser.setStyleName("DatiDX");
		cityPanel.add(cityUser);
		
		Label HometownUser= new Label(user.getHometown());
		HometownUser.setStyleName("DatiDX");
		HometownPanel.add(HometownUser);
		
		Label AltEmailUser= new Label(user.getAltEmail());
		AltEmailUser.setStyleName("DatiDX");
		AltEmailPanel.add(AltEmailUser);
		
		Label interestsUser = new Label(user.getInterests());
		interestsUser.getElement().setId("contenutoTesto");
		interestsTextPanel.add(interestsUser);
		
		Label descriptionsUser=new Label(user.getDescription());
		descriptionsUser.getElement().setId("contenutoTesto");		
		descriptionTextPanel.add(descriptionsUser);
		
		Label inspirationsUser=new Label(user.getInspirations());
		inspirationsUser.getElement().setId("contenutoTesto");
		inspirationsTextPanel.add(inspirationsUser);
		
		changeButtonPanel.add(change);
	}
	
	/**
	 * Questo metodo si occupa di eliminare il contenuto dei pannelli che contengono i dati.
	 */
	private void clear(){
		cityPanel.clear();
		HometownPanel.clear();
		AltEmailPanel.clear();
		descriptionTextPanel.clear();
		interestsTextPanel.clear();
		inspirationsTextPanel.clear();
		changeButtonPanel.clear();
	}
	
	/**
	 * Questo metodo si occupa di modificare la view dando all'utente la possibilità di editare 
	 * i campi dati del suo profilo. Al posto delle label nei pannelli vengono aggiunti dei TextBox
	 *  e si rende editabile la TextArea di interests, descriptions, inspirations.
	 *  Inoltre cambia il bottone visualizzato da ChangeButton a ConfirmButton
	 */
	public void change(){
		
		clear();
		cityTextBox= new TextBox();
		cityTextBox.setText(user.getCurrentLocation());
		cityTextBox.setStyleName("DatiDX");
		cityPanel.add(cityTextBox);
		
		HometownTextBox= new TextBox();
		HometownTextBox.setText(user.getHometown());
		HometownTextBox.setStyleName("DatiDX");
		HometownPanel.add(HometownTextBox);
		
		AltEmailTextBox= new TextBox();
		AltEmailTextBox.setText(user.getAltEmail());
		AltEmailTextBox.setStyleName("DatiDX");
		AltEmailPanel.add(AltEmailTextBox);
		
		interestsTextArea=new TextArea();
		interestsTextArea.setText(user.getInterests());
		interestsTextArea.getElement().setId("contenutoTestoTB");
		interestsTextPanel.add(interestsTextArea);
		
		descriptionsTextArea=new TextArea();
		descriptionsTextArea.setText(user.getDescription());
		descriptionsTextArea.getElement().setId("contenutoTestoTB");
		descriptionTextPanel.add(descriptionsTextArea);
		
		inspirationsTextArea=new TextArea();

		inspirationsTextArea.setText(user.getInspirations());
		inspirationsTextArea.getElement().setId("contenutoTestoTB");
		inspirationsTextPanel.add(inspirationsTextArea);		
		

		changeButtonPanel.add(confirm);
		
	}

	/**
	 * Questo metodo ritorna la città di residenza.
	 */
	@Override
	public HasValue<String> getCity() {
		return cityTextBox;
	}

	/**
	 * Questo metodo ritorna la città di origine.
	 */
	@Override
	public HasValue<String> getHometown() {
		return HometownTextBox;
	}

	/**
	 * Questo metodo ritorna la mail alternativa.
	 */
	@Override
	public HasValue<String> getAltEmail() {
		return AltEmailTextBox;
	}

	/**
	 * Questo metodo ritorna gli interessi
	 */
	@Override
	public HasValue<String> getInterests() {
		return interestsTextArea;
	}

	/**
	 * Questo metodo ritorna la descrizione dell'utente.
	 */
	@Override
	public HasValue<String> getDescriptions() {
		return descriptionsTextArea;
	}
	
	/**
	 * Questo metodo ritorna le ispirazioni dell'utente .
	 */
	@Override
	public HasValue<String> getInspirations() {
		return inspirationsTextArea;
	}

	/**
	 * Questo metodo ritorna il bottone ChangeButton.
	 */
	@Override
	public HasClickHandlers getChangeButton() {
		return change;
	}

	/**
	 * Questo metodo ritorna il bottone ConfirmButton.
	 */
	@Override
	public HasClickHandlers getConfirmButton() {
		return confirm;
	}

	/**
	 * Questo metodo ritorna il widget.
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
	
	/**
	 * Questo metodo richiede il cambio di view da visualize a change. 
	 */
	@Override
	public void changeView() {
		change();
		
	}

	/**
	 * Questo metodo si occupa di stampare un errore.
	 */
	@Override
	public void showError(String error) {
		this.error.setText(error);
		contenent.add(this.error);	
	}

	/**
	 * Questo metodo si occupa di cambiare la view in modo da rendere i campi dati da modificabili
	 *  a solo visualizzabili.
	 */
	@Override
	public void visualizeView() {
		clear();
		visualize();
	}

}

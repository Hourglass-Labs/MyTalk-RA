/*
 * Filename: ExtendedDataContactProfileView.java
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
 *  0.1     | 2013/06/17 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.view.contactprofile;

import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.shared.User;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
/**
 * Questa classe � uno dei widget della ContactProfileView il quale si occupa di visualizzare le informazioni
 * estese del contatto richiesto.
 * @author Sasa Ilievski
 *
 */
public class ExtendedDataContactProfileView implements WidgetPresenter{
	
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
	


	/**
	 * Costruttore della classe ExtendedDataContactProfileView.
	 * Il costurttore si occupa di inizializzare il pannello principale del widget (contenent) e di inserire
	 * gli altri pannelli che conterranno i dati estesi dell'user richiesto. Succesivamente inizializza varie 
	 * Label contenti le informazioni dei dati estesi
	 * @param user: contiene l'user richiesto.
	 */
	public ExtendedDataContactProfileView(User user)
	{
		
		
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
		
		Label cityUser= new Label(user.getCurrentLocation());
		cityUser.setStyleName("DatiDX");
		cityPanel.add(cityUser);	
		
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
		
		Label HometownUser= new Label(user.getHometown());
		HometownUser.setStyleName("DatiDX");
		HometownPanel.add(HometownUser);
		
		AltEmailLabelPanel=new AbsolutePanel();
		AltEmailLabelPanel.getElement().setId("pannello5");
		AltEmailLabelPanel.setStyleName("Pannello");
		contenent.add(AltEmailLabelPanel);
		
		Label recapitiAlt= new Label("Email alternativa:");
		recapitiAlt.setStyleName("DatiSX");
		AltEmailLabelPanel.add(recapitiAlt);
		
		AltEmailPanel=new AbsolutePanel();
		AltEmailPanel.getElement().setId("pannello6");
		AltEmailPanel.setStyleName("Pannello");
		contenent.add(AltEmailPanel);
		
		Label AltEmailUser= new Label(user.getAltEmail());
		AltEmailUser.setStyleName("DatiDX");
		AltEmailPanel.add(AltEmailUser);
		
		descriptionPanel=new AbsolutePanel();
		descriptionPanel.setStyleName("PannelloDescBoth");
		Label descriptionLabel= new Label("Descrizione personale:");
		descriptionLabel.getElement().setId("Titolo");
		descriptionPanel.add(descriptionLabel);
		
		descriptionTextPanel= new AbsolutePanel();
		descriptionTextPanel.getElement().setId("TestoDesc");
		descriptionTextPanel.getElement().getStyle().clearOverflow();
		descriptionPanel.add(descriptionTextPanel);
		contenent.add(descriptionPanel);
		
		Label descriptionsUser=new Label(user.getDescription());
		descriptionsUser.getElement().setId("contenutoTesto");		
		descriptionTextPanel.add(descriptionsUser);
		
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
		
		Label interestsUser = new Label(user.getInterests());
		interestsUser.getElement().setId("contenutoTesto");
		interestsTextPanel.add(interestsUser);
		
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
			
		Label inspirationsUser=new Label(user.getInspirations());
		inspirationsUser.getElement().setId("contenutoTesto");
		inspirationsTextPanel.add(inspirationsUser);
		
		
	}
	
	/**
	 * Restituisce il widget.
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
	
}
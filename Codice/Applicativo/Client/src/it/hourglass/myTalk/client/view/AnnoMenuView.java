/*
 * Filename: AnnoMenuView.java
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
 *  0.1     | 2013/05/10 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.AnnoMenuDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
/**
 * Questa classe rappresenta il widget Menù.
 * Questo widget ha la funzione di permettere all'utente lo switch della view desiderata.
 * @author Sasa Ilievski
 *
 */
public class AnnoMenuView extends Composite implements AnnoMenuDisplay{
	
	private Button HomeButton;
	private Button CallButton;
	private Button LoginButton;
	private Button RegistrationButton;
	private Button RecuperaPasswordButton;
	public AbsolutePanel contenent;
	/**
	 * Costruttore del Menu Widget.
	 * Si occupa di inizializzare i campi
	 */
	public AnnoMenuView()
	{
		contenent= new AbsolutePanel();
		contenent.getElement().setId("Menu");
		
		HomeButton=new Button("Home");
		HomeButton.setStyleName("Bottone");
		
		CallButton = new Button("Chiama");
		CallButton.setStyleName("Bottone");
		
		LoginButton = new Button("Login");
		LoginButton.setStyleName("Bottone");
		
		RegistrationButton = new Button("Registrati");
		RegistrationButton.setStyleName("Bottone");
		
		RecuperaPasswordButton = new Button("Recupera Password");
		RecuperaPasswordButton.setStyleName("Bottone");
		
		contenent.add(HomeButton);
		contenent.add(CallButton);
		contenent.add(LoginButton);
		contenent.add(RegistrationButton);
		contenent.add(RecuperaPasswordButton);
		
		HTML htmlimage = new HTML("<img id='logo' src='MyTalk.png' />");
		contenent.add(htmlimage);

	}
	
	/**
	 * Questo metodo si occupa di restituire il callButton
	 */
	@Override
	public HasClickHandlers getCallButton() {
		return CallButton;
	}
	/**
	 * Questo metodo si occupa di restituire il registrationButton
	 */
	@Override
	public HasClickHandlers getRegistrationButton() {
		return RegistrationButton;
	}
	/**
	 * Questo metodo si occupa di restituire il loginButton
	 */
	@Override
	public HasClickHandlers getLoginButton() {
		return LoginButton;
	}
	/**
	 * Questo metodo si occupa di restituire il recuperaPasswordButton
	 */
	@Override
	public HasClickHandlers getRecuperaPasswordButton() {
		return RecuperaPasswordButton;
	}
	/**
	 * Questo metodo si occupa di restituire l'homeButton
	 */
	@Override
	public HasClickHandlers getHomeButton() {
		return HomeButton;
	}
	/**
	 * Questo metodo si occupa di restituire il widget MenÃ¹
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}



}

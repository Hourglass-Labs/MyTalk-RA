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
 *  0.1     | 2013/05/18 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.SignedMenuDisplay;

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
public class SignedMenuView extends Composite implements SignedMenuDisplay{
	
	private Button HomeButton;
	private Button CallButton;
	private Button ProfileManagementButton;
	private Button LogoutButton;
	public AbsolutePanel contenuto;
	/**
	 * Costruttore del Menu Widget.
	 * Si occupa di inizializzare i campi
	 */
	public SignedMenuView()
	{
		contenuto= new AbsolutePanel();
		contenuto.getElement().setId("Menu");
		
		HomeButton=new Button("Home");
		HomeButton.setStyleName("Bottone");
		
		CallButton = new Button("Chiama");
		CallButton.setStyleName("Bottone");
		
		ProfileManagementButton = new Button("Profilo");
		ProfileManagementButton.setStyleName("Bottone");
		
		
		LogoutButton = new Button("Logout");
		LogoutButton.setStyleName("Bottone");
		
		contenuto.add(HomeButton);
		contenuto.add(CallButton);
		contenuto.add(ProfileManagementButton);
		contenuto.add(LogoutButton);
		
		HTML htmlimage = new HTML("<img id='logo' src='MyTalk.png' />");
		contenuto.add(htmlimage);

	}
	
	/**
	 * Questo metodo si occupa di restituire il callButton
	 */
	@Override
	public HasClickHandlers getCallButton() {
		return CallButton;
	}
	/**
	 * Questo metodo si occupa di restituire l'homeButton
	 */
	@Override
	public HasClickHandlers getHomeButton() {
		return HomeButton;
	}
	/**
	 * Questo metodo si occupa di restituire il widget Menù
	 */
	@Override
	public AbsolutePanel getView() {
		return contenuto;
	}

	/**
	 * Questo metodo si occupa di restituire il Profile Management Button
	 */
	@Override
	public HasClickHandlers getProfileManagementButton() {
		return ProfileManagementButton;
	}

	/**
	 * Questo metodo si occupa di restituire il message Button
	 */
	
	
	/**
	 * Questo metodo si occupa di restituire il Logout Button
	 */
	@Override
	public HasClickHandlers getLogoutButton() {

		return LogoutButton;
	}



}
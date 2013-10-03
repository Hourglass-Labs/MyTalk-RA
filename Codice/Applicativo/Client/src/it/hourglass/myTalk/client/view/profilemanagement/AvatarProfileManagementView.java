/*
 * Filename: AvatarProfileView.java
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

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

import it.hourglass.myTalk.client.presenter.display.AvatarProfileManagementDisplay;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;

/**
 * Questa classe è uno dei widget della ProfileManagmentView il quale si occupa di visualizzare l'avatar
 * del proprio contatto e di richiedere il Popup per la modifica dello stesso.
 * Questa classe implementa l'inferfaccia Display della classe AvatarProfileManagementPresenter.
 * @author Sasa Ilievski
 *
 */
public class AvatarProfileManagementView implements AvatarProfileManagementDisplay{
	
	private AbsolutePanel contenent;
	private Button changeAvatarButton;
	private Image avatar;
	private User user=Profile.getUser();
	
	/**
	 * Costruttore della classe AvatarProfileManagementView. Si occupa di inizializzare il pannello che contiene
	 * il widget e di creare l'immagine contenente l'avatar e visualizzarla. Inoltre aggiunge anche il bottone
	 * changeAvatarButton il quale Ã¨ necessario all'utente per richiedere il Popup di cambio avatar.
	 */
	public AvatarProfileManagementView()
	{
		contenent= new AbsolutePanel();
		contenent.getElement().setId("immagineProfilo");
		
		String avatarUrl;
		if(user.getAvatar()==null){
			//Avatar di default
			avatarUrl="caio.png";
		}
		else{
			avatarUrl=user.getAvatar();
		}
		avatar=new Image();
		avatar.setUrl(avatarUrl);
		avatar.getElement().setId("ImmagineProfilo");
		contenent.add(avatar);
	
		
		changeAvatarButton=new Button("Cambia");
		changeAvatarButton.getElement().setId("Salva");
		contenent.add(changeAvatarButton);
	}

	/**
	 * Restituice il widger
	 */
	public AbsolutePanel getView() {
		return contenent;
	}

	/**
	 * Restituisce il bottone changeAvatarButton
	 */
	@Override
	public HasClickHandlers getChangeAvatarButton() {
		return changeAvatarButton;
	}
}

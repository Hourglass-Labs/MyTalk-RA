/*
 * Filename: AvatarContactProfileView.java
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

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

/**
 * Questa classe è uno dei widget della ContactProfileView il quale si occupa di visualizzare l'avatar
 * del contatto richiesto.
 * @author Sasa Ilievski
 *
 */
public class AvatarContactProfileView implements WidgetPresenter{
	
	private AbsolutePanel contenent;
	private Image avatar;
	
	/**
	 * Costruttore della classe AvatarContactProfileView. Si occupa di inizializzare il pannello che contiene
	 * il widget e di creare l'immagine contenente l'avatar e visualizzarla.
	 * @param user: contiene l'user richiesto.
	 */
	public AvatarContactProfileView(User user)
	{
		contenent= new AbsolutePanel();
		contenent.getElement().setId("immagineProfilo");
		
		String avatarUrl;
		if(user.getAvatar()==null){
			avatarUrl="caio.png";
		}
		else{
			avatarUrl=user.getAvatar();
		}
		avatar=new Image();
		avatar.setUrl(avatarUrl);
		avatar.getElement().setId("ImmagineProfilo");
		contenent.add(avatar);	
	}

	/**
	 * Restituisce il widget.
	 */
	public AbsolutePanel getView() {
		return contenent;
	}


}

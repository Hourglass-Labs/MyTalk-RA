/*
 * Filename: FooterView.java
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
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.FooterDisplay;
import it.hourglass.myTalk.client.shared.Profile;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

/**
 * Questa classe rappresenta il widget FotterView
 * Attraverso questo widget sarà possibile per l'utente vedere il proprio id identificativo
 * @author Sasa Ilievski
 *
 */
public class FooterView extends Composite implements FooterDisplay{
	

	private AbsolutePanel contenent;
	private String username;
	private HTML htmlNomeutente;
	private Image avatar;
	/**
	 * Il costruttore si occupa di inizializzare i campi dati necessari al widget Footer
	 * @param nome
	 */
	public FooterView(String nome)
	{
		this.username=nome;
		contenent= new AbsolutePanel();
		contenent.setStyleName("NomeUtente");
		avatar = new Image();
		avatar.setUrl("maxg.jpg");
		avatar.setStyleName("ImmagineProfilo");
		contenent.add(avatar);
	    
	    htmlNomeutente = new HTML("<p>Il tuo codice identificativo temporaneo e: <span id='nomeBN'>"+nome+"</span></p>");
	    htmlNomeutente.setStyleName("NomeProfilo");
	    contenent.add(htmlNomeutente);
	}
	
	/**
	 * Questo metodo restituisce l'id identificativo
	 */
	@Override
	public String getUsername() {
		return username;
	}
	/**
	 * Questo metodo restituisce il widget Footer
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
	/**
	 * Questo metodo si occupa di settare l'id identificativo nel widget
	 */
	@Override
	public void setUsername(String username) {
		this.username=username;
	}
	
	/**
	 * Questo metodo si occupa di eseguire il refresh del footer
	 */
	@Override
	public void refresh() {
		contenent.remove(htmlNomeutente);
		if(Profile.getUser().getName()==null){
			avatar.setUrl("maxg.jpg");
			htmlNomeutente = new HTML("<p>Il tuo codice identificativo temporaneo e': <span id='nomeBN'>"+username+"</span></p>");
		}
		else
		{
			htmlNomeutente = new HTML("<p>Benvenuto <span id='nomeBN'>"+username+" !</span></p>");
			if(Profile.getUser().getAvatar()==null){
				contenent.remove(avatar);
				avatar=new Image();
				avatar.setUrl("caio.png");
				avatar.setStyleName("ImmagineProfilo");
				contenent.add(avatar);
			}
			else{
				contenent.remove(avatar);
				avatar=new Image();
				avatar.setUrl(Profile.getUser().getAvatar());
				avatar.setStyleName("ImmagineProfilo");
				contenent.add(avatar);
			}
		}
	    htmlNomeutente.setStyleName("NomeProfilo");
	    contenent.add(htmlNomeutente);
	}
	
	

}
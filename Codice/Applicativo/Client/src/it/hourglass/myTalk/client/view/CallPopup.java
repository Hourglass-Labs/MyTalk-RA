/*
 * Filename: CallPopup.java
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
 *  0.1     | 2013/06/02 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;

import it.hourglass.myTalk.client.presenter.display.CallPopupDisplay;

/**
 * Questa classe rappresenta il popup di ricezione di una richiesta di chiamata
 * @author Sasa Ilievski
 *
 */
public class CallPopup implements CallPopupDisplay{
	private PopupPanel popup;
	private Button accept;
	private Button refuse;
	private String name;
	
	/**
	 * Il costruttore si occupa di inizializzare i campi dati necessari e di visualizzare il popup
	 * @param nome
	 */
	public CallPopup(String nome, int type){
		this.name=nome;
		popup = new PopupPanel();
		AbsolutePanel layout = new AbsolutePanel();
		layout.getElement().setId("PopUpChiamata");
		
		
		popup.setWidget(layout);
		
		Image imageUtente = new Image();
		imageUtente.setUrl("caio.png");
		imageUtente.getElement().setId("ImmagineUtente");
		layout.add(imageUtente);
		
		AbsolutePanel absolutePanelTesto = new AbsolutePanel();
		layout.add(absolutePanelTesto);
		absolutePanelTesto.getElement().setId("Testo");
		
		String stype;
		if(type == 1)
			stype= "Audio";
		else if (type==2)
			stype= "Audio/Video";
		else if (type == 3)
			stype = "Audio/Video/Chat";
		else stype= "Chat";
		
		Label lblChiamataInArrivo = new Label("Chiamata di tipo "+stype+" in arrivo da : ");
		absolutePanelTesto.add(lblChiamataInArrivo);
		lblChiamataInArrivo.getElement().setId("TestoChiamata");
		
		Label lblUsername = new Label(nome);
		absolutePanelTesto.add(lblUsername);
		lblUsername.getElement().setId("NomeUtente");
		
		accept = new Button("Accetta");
		absolutePanelTesto.add(accept);
		accept.getElement().setId("Accetta");
		accept.setStyleName("Button");
		
		refuse = new Button("Rifiuta");
		absolutePanelTesto.add(refuse);
		refuse.getElement().setId("Rifiuta");
		refuse.setStyleName("Button");
		

		popup.show();
		popup.center();

		
	}
	/**
	 * Questo metodo restituisce il bottone di accettazione della chiamata
	 */
	@Override
	public HasClickHandlers getAcceptButton() {
		return accept;
	}
	/**
	 * Questo metodo restituisce il bottone di rifiuto della chiamata
	 */
	@Override
	public HasClickHandlers getRefuseButton() {
		return refuse;
	}
	
	@Override
	public void chiudi(){
		popup.removeFromParent();
	}
	
	public String getName(){
		return name;
	}
}



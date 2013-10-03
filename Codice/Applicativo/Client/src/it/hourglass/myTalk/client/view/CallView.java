/*
 * Filename: CallView.java
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
 *  0.1     | 2013/06/10 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;
/**
 * Questa classe rappresente il Widget per la chiamata
 * @author Sasa Ilievski
 */

import it.hourglass.myTalk.client.presenter.display.CallDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;


public class CallView implements CallDisplay{
	AbsolutePanel contenent;
	AbsolutePanel GuestVideoPanel;
	AbsolutePanel LocalVideoPanel;
	AbsolutePanel callUser;
	AbsolutePanel statistics;
	AbsolutePanel ChatPanel;
	AbsolutePanel Chat;
	AbsolutePanel VideoGuestPanel;
	Video GuestVideo;
	Video LocalVideo;
	TextBox textBoxInvia;
	TextBox callUserTextBox;
	TextBox callLenght;
	TextBox callByteD;
	TextBox callByteU;
	TextArea textAreaChat;
	Button callButton;
	Button sendButton;
	Button closeCall;
	ListBox streamRequest;
	Label calee;
	
	/**
	 * Costruttore che inizializza il Widget CallView
	 */
	public CallView()
	{
		contenent= new AbsolutePanel();
		contenent.getElement().setId("CorpoChiamata");
		
		GuestVideoPanel=new AbsolutePanel();
		GuestVideoPanel.getElement().setId("Video");
		contenent.add(GuestVideoPanel);
		
		callUser= new AbsolutePanel();
		callUser.getElement().setId("UtenteContattare");
		
		Label shareLabel= new Label("Condividi :");
		callUser.add(shareLabel);
		
		streamRequest = new ListBox();
		streamRequest.addItem("Audio");
		streamRequest.addItem("Audio/Video");
		streamRequest.addItem("Audio/Video/Chat");
		streamRequest.addItem("Chat");
		streamRequest.getElement().setId("ListaCondividere");
		callUser.add(streamRequest);
		streamRequest.setVisibleItemCount(1);
		
		closeCall= new Button("Chiudi Chiamata");
		closeCall.getElement().setId("ChiudiChiamata");
		
		Label insertUser = new Label("Inserire Username :");
		callUser.add(insertUser);
		
		callUserTextBox = new TextBox();
		callUser.add(callUserTextBox);
		
		callButton = new Button("Chiama");
		callUser.add(callButton);
		GuestVideoPanel.add(callUser);
		
		VideoGuestPanel=new AbsolutePanel();
		VideoGuestPanel.getElement().setId("VideoGuest");
		GuestVideoPanel.add(VideoGuestPanel);
		
		GuestVideo =  Video.createIfSupported();
		GuestVideo.setAutoplay(true);

		
		statistics=new AbsolutePanel();
		statistics.getElement().setId("StatiscticheCommunicazione");
		
		Image imageLenght = new Image();
		imageLenght.setUrl("http://www.appartamentilgirasole.com/images/orologio.png");
		imageLenght.setStyleName("StatisticheImg");
		statistics.add(imageLenght);
		
		callLenght = new TextBox();
		callLenght.setStyleName("StatisticheTextBox");
		callLenght.setReadOnly(true);
		statistics.add(callLenght);
		
		Image imageLatency = new Image();
		imageLatency.setUrl("http://it.opensuse.org/images/thumb/2/28/Icon-lan.png/48px-Icon-lan.png");
		imageLatency.setStyleName("StatisticheImg");
		statistics.add(imageLatency);
		
		callByteD = new TextBox();
		callByteD.setStyleName("StatisticheTextBox");
		callByteD.setReadOnly(true);
		statistics.add(callByteD);
		
		Image imageByte = new Image();
		imageByte.setUrl("http://www.andrealandonio.it/wp-content/uploads/2011/09/download.gif");
		imageByte.setStyleName("StatisticheImg");
		statistics.add(imageByte);
		
		callByteU = new TextBox();
		callByteU.setStyleName("StatisticheTextBox");
		callByteU.setReadOnly(true);
		statistics.add(callByteU);
		
		ChatPanel=new AbsolutePanel();
		ChatPanel.getElement().setId("Chat");
		contenent.add(ChatPanel);
		
		Chat=new AbsolutePanel();
		Chat.getElement().setId("ChatScorrimento");
		textAreaChat = new TextArea();
		textAreaChat.setCharacterWidth(120);
		textAreaChat.setReadOnly(true);
		textAreaChat.setVisibleLines(4);
		Chat.add(textAreaChat);
		ChatPanel.add(Chat);
		textAreaChat.getElement().getStyle().clearPadding();
		
		
		
		AbsolutePanel InvioDati=new AbsolutePanel();
		InvioDati.getElement().setId("InvioDati");
		Chat.add(InvioDati);
		
		textBoxInvia = new TextBox();
		InvioDati.add(textBoxInvia);
		textBoxInvia.getElement().setId("TestoInviare");
		
		sendButton = new Button("Invia");
		InvioDati.add(sendButton);
		sendButton.getElement().setId("ButtonInvia");
		
		LocalVideoPanel=new AbsolutePanel();
		LocalVideoPanel.getElement().setId("VideoUtente");
		contenent.add(LocalVideoPanel);
		
		
		//video utente
		LocalVideo = Video.createIfSupported();
		LocalVideo.setAutoplay(true);
		LocalVideo.setMuted(true);
		LocalVideoPanel.add(LocalVideo);	
	}
	

	/**
	 * Questo metodo si occupa di nascondere l'elemento video non locale e le statistiche di chiamata
	 */
	@Override
	public void nascondi(){
		callUser.remove(GuestVideo);
		callUser.remove(statistics);
	}
	/**
	 * Questo metodo si occupa di mostrare l'elemento video non locale e le statistiche di chiamata
	 */
	@Override
	public void mostra(){
		callUser.clear();
		calee=new Label();
		calee.setText("Sei in chiamata con "+callUserTextBox.getText()+" ");
		callUser.add(calee);
		callUser.add(closeCall);
		VideoGuestPanel.add(GuestVideo);
		GuestVideoPanel.add(statistics);
	}
	
	/**
	 * Questo metodo ritorna il bottone di invio della chat
	 */
	@Override
	public HasClickHandlers getSendButton() {
		return sendButton;
	}
	
	/**
	 * Questo metodo ritorna il Widget CallView
	 */
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
	/**
	 * Questo metodo ritorna il bottone di chiamata
	 */
	@Override
	public HasClickHandlers getCallButton() {
		return callButton;
	}
	/**
	 * Questo metodo ritorna il riferimento al video locale
	 */
	@Override
	public Video getLocalVideo() {
		return LocalVideo;
	}
	/**
	 * Questo metodo ritorna il riferimento al video dell'ospite
	 */
	@Override
	public Video getGuestVideo() {
		return GuestVideo;
	}
	/**
	 * Questo metodo ritorna la textArea della chat
	 */
	@Override
	public TextArea getChat() {
		return textAreaChat;
	}
	/**
	 * Questo metodo ritorna la text area per la statistica di latenza 
	 */
	@Override
	public TextBox getByteD() {
		return callByteD;
	}
	/**
	 * Questo metodo ritorna la text area per la statistica di byte scambiati
	 */
	@Override
	public TextBox getByteU() {

		return callByteU;
	}
	/**
	 * Questo metodo ritorna la text area per la statistica del tempo
	 */
	@Override
	public TextBox getLenght(){
		return callLenght;
	}
	/**
	 * Questo metodo ritorna il nome dell'utente da chiamare
	 */
	@Override
	public TextBox getcallUserTextBox(){
		return callUserTextBox;
	}
	/**
	 * Questo metodo ritorna il messaggio da inviare via chat
	 */
	@Override
	public TextBox getTextBoxInvia(){
		return textBoxInvia;
	}


	@Override
	public HasClickHandlers getCloseButton() {
		// TODO Auto-generated method stub
		return closeCall;
	}


	@Override
	public String getStreamRequest() {
		// TODO Auto-generated method stub
		return streamRequest.getValue(streamRequest.getSelectedIndex());
	}
	
	public Label getCalee(){
		return calee;
	}
	
	public void setCallname(String name){
		callUserTextBox.setValue(name);
	}
	
}


/*
 * Filename: PopupInformation.java
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


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupInformation{
	private PopupPanel popup;;
	private Button ok;
	private Timer timer;
	private Boolean autoclose;
		
	
	public PopupInformation(String message,Boolean autoclose){
		
		
		popup = new PopupPanel();
		AbsolutePanel layout = new AbsolutePanel();
		layout.getElement().setId("PopupMessage");
		popup.add(layout);
		
		Label messageLabel=new Label(message);
		messageLabel.getElement().setId("TestoPopUp");
		layout.add(messageLabel);
		
		Button ok=new Button("OK");
		layout.add(ok);		
		ok.getElement().setId("ButtonPopup");
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {		
				popup.removeFromParent();
			}
		});
		if(autoclose==true){
		  timer = new Timer(){
			  public void run() {
				  popup.removeFromParent();
			    }
		  };
		  timer.scheduleRepeating(5000);
		}
		popup.show();
		popup.setPopupPosition(50, 50);
	}

}

/*
 * Filename: FriendContactView.java
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

import it.hourglass.myTalk.client.presenter.display.FriendContactDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;



public class FriendContactView implements FriendContactDisplay{

 private AbsolutePanel content;
 private String name;
 private Button remove;
 
 public FriendContactView(String name){

	this.name=name;

	content= new AbsolutePanel();
	content.getElement().setId("UtenteAmico");
	
	Label nameLabel= new Label(name);
	nameLabel.getElement().setId("TestoUA");
	content.add(nameLabel);
	
	remove= new Button("Elimina");
	remove.getElement().setId("ButtonElimina");
	content.add(remove);
	}

@Override
public HasClickHandlers getRemoveButton() {
	return remove;
}

@Override
public AbsolutePanel getView() {
	return content;
}

@Override
public void selfDelete(){
	content.removeFromParent();
}


}


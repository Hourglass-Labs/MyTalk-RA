/*
 * Filename: AnnoMenuPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.presenter.display.FooterDisplay;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class FooterPresenter{
	
	  private final FooterDisplay display;
	  

	
	public FooterPresenter(FooterDisplay display){
		this.display=display;
	}
	
	public String getUsername(){
		return display.getUsername();
	}
	
	public void setUsername(String username){
		display.setUsername(username);
	}
	
	
	public void refreshFooter(String Id){
		display.setUsername(Id);
		display.refresh();
	}

	public AbsolutePanel getView() {
		return display.getView();
	}
	


}

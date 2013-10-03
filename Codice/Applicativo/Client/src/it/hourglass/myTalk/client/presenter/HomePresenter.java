/*
 * Filename: FriendMessagePresenter.java
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
 *  ---------+------------+------------------
 *  0.2    	|  2013/05/30 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.presenter;


import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.HomeDisplay;
import com.google.gwt.user.client.ui.AbsolutePanel;


/**
 * Questa classe e' il presenter del widget Home.
 * Questo widget si occupera' di mostrare il video di tutorial dell'applicativo
 * @author Umberto Martinati
 */
public class HomePresenter implements WidgetPresenter{  
	  /**
	   * Questa interfaccia espone i metodi che la view implementera e che il presenter potrﾃ�richiedere.s
	   * @author Umberto
	   *
	   */

  
  private final HomeDisplay display;
  /**
   * Costruttore della classe HomePresenter.
   * Questo costruttore si occupa di inizializzare i campi dati necessari
   */
  public HomePresenter(HomeDisplay display) {
    this.display = display;
  }
    
  /**
   * Questa funzione ritorna il bodyWidget HomeView
   */
@Override
public AbsolutePanel getView() {
	return display.getView();
}

  
}
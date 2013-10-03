/*
 * Filename: MyTalk.java
 * Package: it.hourglass.myTalk.client
 * Author: Umberto Martinati
 * Date: 2013/05/10
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  1.0		| 2013/05/10 | Approvazione classe
 * ---------+------------+------------------
 *  0.0     | 2013/05/10 | Creazione struttura classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/

package it.hourglass.myTalk.client;



import it.hourglass.myTalk.client.appcontroller.AppController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class MyTalk implements EntryPoint {

  public void onModuleLoad() {  
    HandlerManager eventBus = new HandlerManager(null);
    AppController controller = new AppController(eventBus);
    controller.init();
  }
}

/*
 * Filename: WidgetPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati - 
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione interfaccia
 * ---------+------------+------------------
 *  0.1     | 2013/05/20 |Codifica interfaccia
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 */

package it.hourglass.myTalk.client.presenter;

import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Questa interfaccia espone il metodo base di un widget per le view del sistema
 * @author Umberto Martinati
 * @version 1.0
 *
 */
public abstract interface WidgetPresenter {
  public abstract AbsolutePanel getView();
}


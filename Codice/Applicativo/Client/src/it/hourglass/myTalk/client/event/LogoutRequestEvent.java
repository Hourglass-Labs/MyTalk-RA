
/*
 * Filename: LogoutPageRequestEvent.java
 * Package: it.hourglass.myTalk.event 
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0		| 2013/07/5 | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/05/8 |Codifica classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package it.hourglass.myTalk.client.event;
import com.google.gwt.event.shared.GwtEvent;


public class LogoutRequestEvent extends GwtEvent<LogoutRequestEventHandler> {
  public static Type<LogoutRequestEventHandler> TYPE = new Type<LogoutRequestEventHandler>();
  
  @Override
  public Type<LogoutRequestEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LogoutRequestEventHandler handler) {
    handler.onLogoutRequest(this);
  }
}
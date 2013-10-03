/*
 * Filename: HomePageRequestEvent.java
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
import it.hourglass.myTalk.client.event.HomePageRequestEventHandler;

public class HomePageRequestEvent extends GwtEvent<HomePageRequestEventHandler> {
  public static Type<HomePageRequestEventHandler> TYPE = new Type<HomePageRequestEventHandler>();
  
  @Override
  public Type<HomePageRequestEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(HomePageRequestEventHandler handler) {
    handler.onHomePageRequestEvent(this);
  }
}
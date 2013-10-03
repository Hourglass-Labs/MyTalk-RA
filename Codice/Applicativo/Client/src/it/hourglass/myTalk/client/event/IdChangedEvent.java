/*
 * Filename: IdChangedEvent.java
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


public class IdChangedEvent extends GwtEvent<IdChangedEventHandler> {
  public static Type<IdChangedEventHandler> TYPE = new Type<IdChangedEventHandler>();
  
  @Override
  public Type<IdChangedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(IdChangedEventHandler handler) {
    handler.onIdChangedEvent(this);
  }
}
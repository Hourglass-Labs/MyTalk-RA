
/*
 * Filename: MessagePageRequestEvent.java
 * Package: it.hourglass.myTalk.event 
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0		| 2013/08/22 | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/07/20 |Codifica classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package it.hourglass.myTalk.client.event;
import com.google.gwt.event.shared.GwtEvent;
public class MessagePageRequestEvent extends GwtEvent<MessagePageRequestEventHandler> {
  public static Type<MessagePageRequestEventHandler> TYPE = new Type<MessagePageRequestEventHandler>();
  
  @Override
  public Type<MessagePageRequestEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
protected void dispatch(MessagePageRequestEventHandler handler) {
    handler.onMessagePageRequestEvent(this);
	
}
}
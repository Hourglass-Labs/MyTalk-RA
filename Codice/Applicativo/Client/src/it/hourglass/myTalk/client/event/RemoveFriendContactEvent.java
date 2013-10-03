
/*
 * Filename: RemoveFriendContactEvent.java
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
import it.hourglass.myTalk.client.event.RemoveFriendContactEventHandler;

public class RemoveFriendContactEvent extends GwtEvent<RemoveFriendContactEventHandler> {
	
	String contact;
	
	public RemoveFriendContactEvent(String contact){
		this.contact=contact;
	}
	
  public static Type<RemoveFriendContactEventHandler> TYPE = new Type<RemoveFriendContactEventHandler>();
  
  @Override
  public Type<RemoveFriendContactEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(RemoveFriendContactEventHandler handler) {
    handler.onRemoveFriendContactEvent(this,contact);
  }
}

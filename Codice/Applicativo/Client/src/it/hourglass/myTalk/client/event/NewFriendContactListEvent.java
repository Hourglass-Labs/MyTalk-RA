
/*
 * Filename: NewFriendContactListEvent.java
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
import it.hourglass.myTalk.client.event.NewFriendContactListEventHanlder;

public class NewFriendContactListEvent extends GwtEvent<NewFriendContactListEventHanlder> {
	
	private String contact;
	private Boolean status;
	
	public NewFriendContactListEvent(String contact,Boolean status){
		this.contact=contact;
		this.status=status;
	}
	
  public static Type<NewFriendContactListEventHanlder> TYPE = new Type<NewFriendContactListEventHanlder>();
  
  @Override
  public Type<NewFriendContactListEventHanlder> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(NewFriendContactListEventHanlder handler) {
    handler.onNewFriendContactListEvent(this,contact,status);
  }
}

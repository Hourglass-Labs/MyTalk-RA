/*
 * Filename: FriendContactProfilePageRequestEventHanlder.java
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

import com.google.gwt.event.shared.EventHandler;

public interface FriendContactProfilePageRequestHandler extends EventHandler {
  void onFriendContactProfilePageRequest(FriendContactProfilePageRequest friendContactProfilePageRequest);
}

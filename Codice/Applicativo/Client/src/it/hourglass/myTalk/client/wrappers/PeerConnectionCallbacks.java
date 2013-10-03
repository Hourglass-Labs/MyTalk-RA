/*
 * Filename: ConsoleLog.java
 * Package: it.hourglass.myTalk.client.wrapper
 * Author: Riccardo Cesarotto
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/05/21 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.wrappers;

import com.google.gwt.core.client.JavaScriptObject;

public interface PeerConnectionCallbacks {
	public void onicecandidate(JavaScriptObject jso);
	public void onconnecting(JavaScriptObject jso);
	public void onopen(JavaScriptObject jso);
	public void onaddstream(JavaScriptObject jso);
	public void onremovestream(JavaScriptObject jso);
	public void onchannelopen(JavaScriptObject jso);
	public void onchannelmessage(JavaScriptObject jso);
	public void onchannelclose(JavaScriptObject jso);
}

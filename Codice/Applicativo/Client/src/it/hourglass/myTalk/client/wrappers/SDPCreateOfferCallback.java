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

import it.hourglass.myTalk.client.wrappers.mozRTCSessionDescription;

import com.google.gwt.core.client.JavaScriptObject;

public interface SDPCreateOfferCallback {
	void mozRTCSessionDescriptionCallback(mozRTCSessionDescription sdp);
	void RTCSessionDescriptionCallback(RTCSessionDescription sdp);
	void RTCPeerConnectionErrorCallback(JavaScriptObject error);

}

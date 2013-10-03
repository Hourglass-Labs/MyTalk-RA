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

public class ConsoleLog {

	public static native void consoleLog(String message) /*-{
		console.log(message);
	}-*/;

	public static native void consoleLog(JavaScriptObject message) /*-{
		console.debug(message);
	}-*/;

	public static native void consoleDebug(JavaScriptObject message) /*-{
		console.debug(message);
	}-*/;

	public static native String createMediaObjectBlobUrl(JavaScriptObject stream) /*-{
		return URL.createObjectURL(stream);
	}-*/;

}

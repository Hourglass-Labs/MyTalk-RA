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

public class GetUserMediaUtils {
	public static native void getUserMedia(boolean audio, boolean video,
			GetUserMediaCallback callback) /*-{
		var cb = function(stream) {
			
			callback.@it.hourglass.myTalk.client.wrappers.GetUserMediaUtils.GetUserMediaCallback::navigatorUserMediaSuccessCallback(Lit/hourglass/myTalk/client/wrappers/MediaStream;)(stream);
		}

		var ecb = function(error) {
			callback.@it.hourglass.myTalk.client.wrappers.GetUserMediaUtils.GetUserMediaCallback::navigatorUserMediaErrorCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(error);
		}
		try {
			if(navigator.webkitGetUserMedia){
			navigator.webkitGetUserMedia({
				audio : audio,
				video : video
			}, cb, ecb);}
			else if(navigator.mozGetUserMedia){
			navigator.mozGetUserMedia({
				audio : audio,
				video : video
			}, cb, ecb);}
			}
			catch (err) {
			ecb(err);
		}
	}-*/;

	public interface GetUserMediaCallback {
		public void navigatorUserMediaSuccessCallback(MediaStream localStream);

		public void navigatorUserMediaErrorCallback(JavaScriptObject error);
	}

}

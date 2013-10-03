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

public class SDPOfferMediaConstraints {
	private boolean mandadoryOfferToReceiveAudio;
	private boolean mandadoryOfferToReceiveVideo;
	
	
	
	
	
	public SDPOfferMediaConstraints(boolean mandadoryOfferToReceiveAudio,
			boolean mandadoryOfferToReceiveVideo) {
		super();
		this.mandadoryOfferToReceiveAudio = mandadoryOfferToReceiveAudio;
		this.mandadoryOfferToReceiveVideo = mandadoryOfferToReceiveVideo;
	}





	public native JavaScriptObject asJavaScriptObject() /*-{
		var instance = this;
		return {
			'mandatory' : {
				'OfferToReceiveAudio' : instance.@it.hourglass.myTalk.client.wrappers.SDPOfferMediaConstraints::mandadoryOfferToReceiveAudio,
				'OfferToReceiveVideo' : instance.@it.hourglass.myTalk.client.wrappers.SDPOfferMediaConstraints::mandadoryOfferToReceiveVideo,
			}
		}
	}-*/;
}

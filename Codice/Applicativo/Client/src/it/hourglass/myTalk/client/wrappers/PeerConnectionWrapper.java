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

public class PeerConnectionWrapper {
	JavaScriptObject pc;
	JavaScriptObject sendChannel;
	JavaScriptObject receiveChannel;
	String bytes;
	String latency;
	boolean chat;

	public PeerConnectionWrapper(RTCConfiguration conf,
			PeerConnectionCallbacks callbacks, boolean chatbool) {
		init(conf, callbacks);
		chat=chatbool;
		if(chat==true) {
		sendChannel=createDataChannel(); 
		ConsoleLog.consoleLog("Assegnato sendDataChannel"); 
		ConsoleLog.consoleLog(getState());
		setChannel(callbacks);
		}
		
	}

	public native void init(RTCConfiguration conf,
			PeerConnectionCallbacks callbacks) /*-{
		var theInstance = this;
		var c = conf.@it.hourglass.myTalk.client.wrappers.RTCConfiguration::asJavaScriptObject(*)();

		if(navigator.webkitGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc = new webkitRTCPeerConnection(
				c, {optional: [{DtlsSrtpKeyAgreement: true},{RtpDataChannels: true}]});
		}
		else if (navigator.mozGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc = new mozRTCPeerConnection(
				c, {optional: [{DtlsSrtpKeyAgreement: true},{RtpDataChannels: true}]});
		}

		var _onicecandidate = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onicecandidate(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
		var _onconnecting = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onconnecting(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
		var _onopen = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onopen(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
		var _onaddstream = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onaddstream(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
		var _onremovestream = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onremovestream(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
		
		
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.onicecandidate = _onicecandidate;
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.onconnecting = _onconnecting;
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.onopen = _onopen;
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.onaddstream = _onaddstream;
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.onremovestream = _onremovestream;
		

	}-*/;

	public native void createAnswer(SDPOfferMediaConstraints mediaConstraints,
			SDPCreateOfferCallback callback) /*-{
		var theInstance = this;

		if(navigator.webkitGetUserMedia){
			var offerCallback = function(event) {																	
				callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::RTCSessionDescriptionCallback(Lit/hourglass/myTalk/client/wrappers/RTCSessionDescription;)(event);
			}
		}
		else if (navigator.mozGetUserMedia){
			var offerCallback = function(event) {																	
				callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::mozRTCSessionDescriptionCallback(Lit/hourglass/myTalk/client/wrappers/mozRTCSessionDescription;)(event);
			}
		}
		var errorCallback = function(event) {
			callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::RTCPeerConnectionErrorCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(event);
		}

		var mc = mediaConstraints.@it.hourglass.myTalk.client.wrappers.SDPOfferMediaConstraints::asJavaScriptObject(*)();

		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.createAnswer(offerCallback, errorCallback, mc);
	}-*/;

	public native void createOffer(SDPOfferMediaConstraints mediaConstraints,
			SDPCreateOfferCallback callback) /*-{
		var theInstance = this;

		if(navigator.webkitGetUserMedia){
			var offerCallback = function(event) {																	
				callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::RTCSessionDescriptionCallback(Lit/hourglass/myTalk/client/wrappers/RTCSessionDescription;)(event);
			}
		}
		else if (navigator.mozGetUserMedia){
			var offerCallback = function(event) {																	
				callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::mozRTCSessionDescriptionCallback(Lit/hourglass/myTalk/client/wrappers/mozRTCSessionDescription;)(event);
			}
		}
		var errorCallback = function(event) {
			callback.@it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback::RTCPeerConnectionErrorCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(event);
		}

		var mc = mediaConstraints.@it.hourglass.myTalk.client.wrappers.SDPOfferMediaConstraints::asJavaScriptObject(*)();

		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.createOffer(offerCallback, errorCallback, mc);
	}-*/;

	public native JavaScriptObject getLocalDescription()/*-{
		var theInstance = this;
		return theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.localDescription;
	}-*/;

	public native JavaScriptObject getRemoteDescription()/*-{
		var theInstance = this;
		return theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.remoteDescription;
	}-*/;

	public native void setLocalDescription(JavaScriptObject jso)/*-{
		var theInstance = this;
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.setLocalDescription(jso);
	}-*/;

	public native void addStream(JavaScriptObject jso)/*-{
		var theInstance = this;
		console.debug(jso);
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.addStream(jso);
	}-*/;

	public native void setRemoteDescription(JavaScriptObject jso)/*-{
		var theInstance = this;
		console.debug(jso);
		if(navigator.webkitGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.setRemoteDescription(new RTCSessionDescription(jso));
		}
		else if(navigator.mozGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.setRemoteDescription(new mozRTCSessionDescription(jso));
		}
	}-*/;

	public native void close()/*-{
		var theInstance = this;
		try {
			theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.stop();
			
		} catch(err) {
			console.log('Exeption while closing peer connection');
			console.debug(err);
		}
	}-*/;

	public native void dumpPCToConsole() /*-{
		console
				.debug(this.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc);
	}-*/;

	public native void addIceCandidate(JavaScriptObject jso) /*-{
		var theInstance = this;
		if(navigator.webkitGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.addIceCandidate(new RTCIceCandidate(jso));
		}
		else if(navigator.mozGetUserMedia){
		theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc
				.addIceCandidate(new mozRTCIceCandidate(jso));
		}
	}-*/;
	/** 
	 *  <p>
	 *  Metodo utilizzato per creare un "Data Channel" nella "PeerConnection".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
	public native JavaScriptObject createDataChannel() /*-{
	var theInstance = this;
	return theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.createDataChannel("sendDataChannel",{reliable: false});
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per gestire gli eventi relativi a "sendChannel" quali l'apertura,
	 *  la chiusura e la ricezione di un messaggio.
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	public native void setChannel(PeerConnectionCallbacks callbacks) /*-{
	var theInstance = this;
	console.log("Set Channel");
	
	var _onchannelopen = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onchannelopen(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
	var _onchannelmessage = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onchannelmessage(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
	var _onchannelclose = function(e) {
			callbacks.@it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks::onchannelclose(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		}
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::sendChannel.onopen=_onchannelopen;
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::sendChannel.onmessage=_onchannelmessage;
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::sendChannel.onclose=_onchannelclose;
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per ottenero lo stato del Data Channel "sendChannel".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
	public native String getState() /*-{
	var theInstance = this;
	if(this.chat==true) 
		{return theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::sendChannel.readyState;}
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per inviare messaggi di testo attraverso il metodo "send(str)" di "sendChannel".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
	public native void sendMessage(String str) /*-{
	var theInstance = this;
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::sendChannel.send(str);
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per ottenere i messaggi in entrata di "sendChannel".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
	public native String getMessage(JavaScriptObject jso) /*-{
	var theInstance = this;
	console.log(jso.data);
	return jso.data.toLocaleString();
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per ottenere i bytes inviati attraverso il metodo "getStats" di "PeerConnection".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
		public native String getBytesU() /*-{
	var theInstance = this;
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.getStats(function(stats){
		bytesU1=stats.result()[6].stat("bytesSent");
		bytesD1=stats.result()[6].local.stat("bytesReceived");
		bytesU2=stats.result()[1].stat("bytesSent");
		bytesD2=stats.result()[1].stat("bytesReceived");
	});
	bytesU1m=(bytesU1/1048576).toString();
	bytesD1m=(bytesD1/1048576).toString();
	bytesU2m=(bytesU2/1048576).toString();
	bytesD2m=(bytesD2/1048576).toString();
	return bytesU1m.substr(0,3)+bytesU2m.substr(0,3)+" UP /"+bytesD1m.substr(0,3)+bytesD2m.substr(0,3)+" DW";
	
	}-*/;
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per ottenere un indice di latenza di chiamata "getStats" di "PeerConnection".
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	
	
	public native String getBytesD() /*-{
	var theInstance = this;
	theInstance.@it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper::pc.getStats(function(stats){
	latency=stats.result()[6].stat("googJitterReceived");
	});
	if(latency){return " "+latency;}
	else return "0";
	}-*/;
	

}

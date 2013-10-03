/*
 * Filename: ParserInTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/01
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.2     | 2013/08/01 | Approvazione Test
 * ---------+------------+------------------
 *  0.1     | 2013/08/01 |Codifica getIDTypeTest()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */





package it.hourglass.myTalk.test;

import static org.junit.Assert.*;

import java.nio.CharBuffer;

import org.apache.catalina.websocket.WsOutbound;
import org.junit.Test;
import static org.mockito.Mockito.*;
import interf.ParserIn;
import interf.ParserIn.MyMessageInbound;
import java.lang.CharSequence;

public class ParserInTest {

	@Test
	public final void getIDTypeTest() {
		ParserIn pi= new ParserIn();
		String type="offer";
		if(pi.getIDType(type)==1){assert(true);}
	}
}

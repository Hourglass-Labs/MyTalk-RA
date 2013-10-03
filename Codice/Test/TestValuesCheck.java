package it.hourglass.myTalk.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import it.hourglass.myTalk.client.presenter.ValuesCheck;

/*
 * Filename: TestValuesCheck.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/11
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.6     | 2013/08/11  | Approvazione Test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/04  | Revisione, ampliamento dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/07  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/05  | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/07/04  | Stesura mock 
 * ---------+-------------+------------------
 *  0.1     | 2013/07/02  | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
*/

public class TestValuesCheck{
	ValuesCheck vc = new ValuesCheck();
 
	@Test
	public final void testcheckUniqueId(){   
	assertEquals("L'username dev'essere alfanumerico", vc.checkUniqueId("koala?"));
 }
	@Test
	public final void testcheckPasswords(){
	assertEquals("La password dev'essere un'unica parola di almeno 8 caratteri alfanumerici.",
			 vc.checkPasswords("x", "y"));
	}
	
	@Test
	public final void testcheckName(){
	assertEquals("Campo obbligatorio.",vc.checkName(""));
	}
	
	@Test
	public final void testcheckLastName(){	
	assertEquals("Campo obbligatorio.", vc.checkLastName(""));
	}
	
	@Test
	public final void testcheckEmail(){	
	assertEquals("",vc.checkEmail("kjb@kjb.kjn"));	
	}
	
 


	 
	 
 }



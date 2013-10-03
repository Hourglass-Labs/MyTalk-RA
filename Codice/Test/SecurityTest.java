package it.hourglass.myTalk.test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;
import it.hourglass.myTalk.server.model.businesslogic.Security;

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
 *  0.5     | 2013/08/04  | Revisione dei test a seguito della RQ
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

public class SecurityTest{

	Security s = new Security();
	String x;
	String y;
	

	@Test
	public void generateValidationTest(){
		x = s.generateValidation();
	}	
	
	@Test
	public void encryptPasswordTest(){
		x = s.generateValidation();
		//Ripeto i passi precedenti
		String y = s.encryptPassword(x);
		assertNotSame(x, y);
	}
	
	@Test
	public void checkPasswordTest(){
		x = s.generateValidation();
		String y = s.encryptPassword(x);
		assertNotSame(x, y);
		//Ripeto i passi precedenti
		assertEquals(true, s.checkPasswords(x,y));
		
	}
}
	


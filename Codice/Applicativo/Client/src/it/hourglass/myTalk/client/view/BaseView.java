/*
 * Filename: BaseView.java
 * Package: it.hourglass.myTalk.client.view
 * Author: Sasa Ilievski
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/05/8 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.BaseDisplay;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
/**
 * Questa classe rappresenta la BaseView.
 * Su questa classe verranno aggiunti i widget del menﾃｹ,del footer, e sarﾃ�possibile lo swhitch
 * dei widget centrali
 * @author Sasa Ilievski
 *
 */

public class BaseView extends Composite implements BaseDisplay{
	
	private AbsolutePanel leftPanel;
	private AbsolutePanel bodyPanel;
	private AbsolutePanel menuPanel;
	private RootPanel rootPanel;
	
	/**
	 * Costruttore della BaseView.
	 * Questo metodo si occupa di creare i pannelli dentro i quali andranno inseriti i widget del Menu', del
	 * body e del footer.
	 * Assegna ad ogni pannello il relativo stile css.
	 * 
	 */
	public BaseView() {
		rootPanel= RootPanel.get();
		
		AbsolutePanel contenitore = new AbsolutePanel();
		contenitore.setStyleName("Contenitore");
		rootPanel.add(contenitore);
		
		leftPanel = new AbsolutePanel();
		leftPanel.setStyleName("ContenitoreSX");
		
		menuPanel = new AbsolutePanel();
		menuPanel.getElement().setId("contenitoreMenu");
		leftPanel.add(menuPanel);
		
		bodyPanel = new AbsolutePanel();
		bodyPanel.setStyleName("ContenitoreDX");
		
		contenitore.add(leftPanel);
		contenitore.add(bodyPanel);	
		
	}
	
	/**
	 * Questo metodo si occupa di eseguire lo switch del BodyWidget del pannello destro
	 */
	@Override
	public void switchBody(AbsolutePanel pannello) {
		bodyPanel.clear();
		bodyPanel.add(pannello);
		
	}

	/**
	 * Questo metodo si occupa di esguire lo switch del Menu nel pannello sinistro
	 */
	@Override
	public void switchMenu(AbsolutePanel pannello) {
		menuPanel.clear();
		menuPanel.add(pannello);
	}

	/**
	 * Questo metodo si occupa di eseguire lo swtiche del footer
	 */
	@Override
	public void switchFooter(AbsolutePanel pannello) {
		rootPanel.add(pannello);
		
	}

	@Override
	public void addContactList(AbsolutePanel panel) {
		leftPanel.add(panel);
		
	}
	
}

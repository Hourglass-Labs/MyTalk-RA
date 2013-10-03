/*
 * Filename: AnnoMenuView.java
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
 *  0.1     | 2013/06/22 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
/*
 * Filename: AnnoMenuView.java
 * Package: it.hourglass.myTalk.client.presenter
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
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.LoginDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Questa classe rappresenta il Widget LoginView.
 * @author Sasa Ilievski
 *
 */
public class LoginView extends Composite implements LoginDisplay{
	private AbsolutePanel contenuto;
	private TextBox usernameTextBox;
	private Button loginButton;
	private PasswordTextBox passwordTextBox;
	private Label error=new Label("");
	
	public LoginView()
	{
		error.getElement().setId("Errore");
		contenuto= new AbsolutePanel();
		contenuto.setStyleName("LoginView");
		
		Grid grid = new Grid(2, 2);
		contenuto.add(grid);
		
		Label lblUsername = new Label("Username");
		grid.setWidget(0, 0, lblUsername);
		
		usernameTextBox = new TextBox();
		grid.setWidget(0, 1, usernameTextBox);
		
		Label lblPassword = new Label("Password");
		grid.setWidget(1, 0, lblPassword);
		
		passwordTextBox = new PasswordTextBox();
		grid.setWidget(1, 1, passwordTextBox);
		
		loginButton = new Button("Accedi");
		loginButton.setStyleName("BtnAccedi");
		contenuto.add(loginButton);
	
	}
	public AbsolutePanel getView()
	{
		return contenuto;
	}
	@Override
	public HasClickHandlers getLoginButton() {
		return loginButton;
	}
	
	@Override
	public HasValue<String> getUsername() {
		return usernameTextBox;
	}
	@Override
	public HasValue<String> getPassword() {
		return passwordTextBox;
	}
	@Override
	public void showLoginError(String errore) {
		this.error.setText(errore);
		contenuto.add(error);
		
	}
}
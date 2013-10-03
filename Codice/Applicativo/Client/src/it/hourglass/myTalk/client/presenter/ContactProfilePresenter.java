/*
 * Filename: ContactProfilePresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.4     | 2013/06/30  | Aggiunta metodo recover
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/20 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;


import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.presenter.display.ContactProfileDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Questa classe funge da presenter alla ContactProfileView
 * Questa classe si occupa di gestire la logica per la creazione della pagina di visualizzazione 
 * del profilo di un altro utente.
 * @author Umberto Martinati
 *
 */

public class ContactProfilePresenter implements WidgetPresenter{

	
	private final DialogBox recoverProfile = new DialogBox();
	private ContactProfileDisplay display;
	private RPCServiceAsync service = GWT.create(RPCService.class);
	
	/**
	 * Costruttore della classe ContactProfilePresenter. Esso si occupa di recuperare il profilo
	 * dell'utente. Successivamente si occupa di richiedere l'inizializzazione della view con 
	 * le informazioni recuperate.
	 * @param eventBus
	 * @param display
	 */
	public ContactProfilePresenter(HandlerManager eventBus,ContactProfileDisplay display){
		this.display=display;	
		
		recoverProfile.setText("Recuperando il profilo...");
		recoverProfile.center();
		recoverProfile.show();
		
		if(Profile.getUser().getViewFriendProfile()!=null)
			recover();
		else{
			recoverProfile.removeFromParent();
			eventBus.fireEvent(new HomePageRequestEvent());
		}
	}
	
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Questo metodo si occupa di recupeare il profilo di un utente.
	 */
	private void recover(){
		AsyncCallback<User> callback = new AsyncCallback<User>() {
		    public void onFailure(Throwable caught) {
		       GWT.log(caught.getMessage(), null);
		       }
		    public void onSuccess(User user) {
		    	if(user!=null){
		    		display.init(user);
		    		Profile.getUser().setViewFriendProfile(null);
		    	}
		    	else{
		    		PopupInformation popup=new PopupInformation("Il profilo richiesto non esiste ",true);
		    	}
		    	recoverProfile.removeFromParent();
		    	}
		    };
		    service.fetchFriendProfile(Profile.getUser().getViewFriendProfile(), callback);
		}

}

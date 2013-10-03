package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.HomeDisplay;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * Questa classe rappresenta il bodyWidget della HomeView
 * @author Sasa Ilievski
 *
 */

public class HomeView implements  HomeDisplay{
	private AbsolutePanel contenuto;
	/**
	 * Costruttore del widget HomeView.
	 * Si occupa di inizializzare il widget.
	 */
	public HomeView()
	{
		contenuto= new AbsolutePanel();
		contenuto.getElement().setId("PagPrincipale");
		
		HTML testo1=new HTML("<p>Benvenuti nel sistema MyTalk.");
		contenuto.add(testo1);
		HTML testo2=new HTML("<p>Premi play per visualizzare il videotutorial.");
		contenuto.add(testo2);
		AbsolutePanel video=new AbsolutePanel();
		contenuto.add(video);
		video.getElement().setId("video");
		
		//Da inserire il video tutorial
		HTML videoTuttorial=new HTML("<iframe width='480' height='360' src='//www.youtube.com/embed/My2FRPA3Gf8?rel=0' frameborder='0' allowfullscreen></iframe>");
		video.add(videoTuttorial);
		video.getElement().getStyle().clearPosition();
		video.getElement().getStyle().clearOverflow();
		
		
	}
	
	/**
	 * Questo metodo si occupa di restituire il widget HomeView
	 */
	@Override
	public AbsolutePanel getView() {
		return contenuto;
	}

}

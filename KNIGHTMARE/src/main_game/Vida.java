package main_game;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Vida {
	private ArrayList<Image> vidasJugador;
	
	
	public ArrayList<Image> getVidasJugador() {
		return vidasJugador;
	}


	public void setVidasJugador(ArrayList<Image> vidasJugador) {
		this.vidasJugador = vidasJugador;
	}


	//CONSTRUCTOR
	public Vida(){
		vidasJugador = new ArrayList();
		for(int i = 0;i<=5;i++){
			vidasJugador.add(new ImageIcon(getClass().getResource("HP_Value_"+i+".png")).getImage());
		}

	}
}

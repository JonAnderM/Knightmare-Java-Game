package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

public class FondoParallax {

	private Enemigo perro;
	private Image fondoEstatico;
	private int posFondoLejano;
	private int posCementerio;
	private ArrayList<Image> fondoLejano;
	private ArrayList<Image> suelo;
	private ArrayList<Image> nivel2;
	private ArrayList<Image> nivel3;
	private ArrayList<Image> fondoCementerio;
	private static ArrayList<Rectangle> rectSuelo;
	private int posSuelo;
	private int camara;
	private int camaraMid;
	private int camaraFar;
	private int posX;
	private int posY = 350;
	private int alto;
	private int ancho;
	private int direccion;
	private Timer bucleFondo;
	private Personaje jugador;

	public Image getFondoEstatico() {
		return fondoEstatico;
	}

	public void setFondoEstatico(Image fondoEstatico) {
		this.fondoEstatico = fondoEstatico;
	}

	public int getPosFondoLejano() {
		return posFondoLejano;
	}

	public void setPosFondoLejano(int posFondoLejano) {
		this.posFondoLejano = posFondoLejano;
	}

	public int getPosCementerio() {
		return posCementerio;
	}

	public void setPosCementerio(int posCementerio) {
		this.posCementerio = posCementerio;
	}

	public ArrayList<Image> getFondoLejano() {
		return fondoLejano;
	}

	public void setFondoLejano(ArrayList<Image> fondoLejano) {
		this.fondoLejano = fondoLejano;
	}

	public ArrayList<Image> getNivel2() {
		return nivel2;
	}

	public void setNivel2(ArrayList<Image> nivel2) {
		this.nivel2 = nivel2;
	}

	public ArrayList<Image> getNivel3() {
		return nivel3;
	}

	public void setNivel3(ArrayList<Image> nivel3) {
		this.nivel3 = nivel3;
	}

	public ArrayList<Image> getFondoCementerio() {
		return fondoCementerio;
	}

	public void setFondoCementerio(ArrayList<Image> fondoCementerio) {
		this.fondoCementerio = fondoCementerio;
	}

	public int getPosSuelo() {
		return posSuelo;
	}

	public void setPosSuelo(int posSuelo) {
		this.posSuelo = posSuelo;
	}

	public int getCamara() {
		return camara;
	}

	public void setCamara(int camara) {
		this.camara = camara;
	}

	public int getCamaraMid() {
		return camaraMid;
	}

	public void setCamaraMid(int camaraMid) {
		this.camaraMid = camaraMid;
	}

	public int getCamaraFar() {
		return camaraFar;
	}

	public void setCamaraFar(int camaraFar) {
		this.camaraFar = camaraFar;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public FondoParallax() {
		direccion = 0;
		alto = 50;
		ancho = 100;
		camara = 0;
		camaraMid = 0;
		camaraFar = 0;
		posCementerio = 0;
		posFondoLejano = 0;
		jugador=new Personaje();
		fondoLejano = new ArrayList<>();
		fondoCementerio = new ArrayList<>();
		fondoEstatico = new ImageIcon(getClass().getResource("background.png")).getImage();
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoCementerio.add(new ImageIcon(getClass().getResource("graveyard.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains2.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains2.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains.png")).getImage());
		fondoLejano.add(new ImageIcon(getClass().getResource("mountains2.png")).getImage());

		registrarEventos();
	}

	private void registrarEventos() {
		bucleFondo = new Timer(30, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void moverFondo(int direccionFondo) {
		if (direccionFondo == KeyEvent.VK_D) {
			camaraMid += 3;
			camaraFar += 1;
			posFondoLejano -= 2;
			posCementerio -= 7;
			posSuelo -= 10;
			
		}
		if (direccionFondo == KeyEvent.VK_A) {
			posFondoLejano += 2;
			posCementerio += 7;
			posSuelo += 10;
			camaraMid -= 3;
			camaraFar -= 1;
		}
		if(jugador.getPosicionRealMapa()==5000){
			camaraMid += 0;
			camaraFar += 0;
			posFondoLejano -= 0;
			posCementerio -= 0;
			posSuelo -= 0;
			
		}
	}

	public void dibujar(Graphics g) {

		g.drawImage(fondoEstatico, 0, 0, 1200, 720, null);
		for (int i = 0; i < fondoLejano.size(); i++) {
			if (i == 1) {
				posFondoLejano = 800;
			}
			g.drawImage(fondoLejano.get(i), posFondoLejano * i - camaraFar, 250, 800, 500, null);
		}
		for (int i = 0; i < fondoCementerio.size(); i++) {
			if (i == 1) {
				posCementerio = 900;
			}
			g.drawImage(fondoCementerio.get(i), posCementerio * i - camaraMid, 300, 900, 300, null);
		}

	}
	public void dibujarEstatico(Graphics g) {

		g.drawImage(fondoEstatico, 0, 0, 1200, 720, null);
		for (int i = 0; i < fondoLejano.size(); i++) {
			if (i == 1) {
				posFondoLejano = 800;
			}
			g.drawImage(fondoLejano.get(i), posFondoLejano * i , 250, 800, 500, null);
		}
		for (int i = 0; i < fondoCementerio.size(); i++) {
			if (i == 1) {
				posCementerio = 900;
			}
			g.drawImage(fondoCementerio.get(i), posCementerio * i , 300, 900, 300, null);
		}

	}

}
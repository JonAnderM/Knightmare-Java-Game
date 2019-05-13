package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

public class BossStage {
	private Image fondoEstatico;
	private int posFondoLejano;
	private int posCementerio;
	private ArrayList<Image> fondoLejano;
	private ArrayList<Image> suelo;
	private ArrayList<Image> decorativo;
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
	private boolean fondoQuieto;


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

	public ArrayList<Image> getSuelo() {
		return suelo;
	}

	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(posX, posY, alto, ancho);

		return rect;

	}

	public void setSuelo(ArrayList<Image> suelo) {
		this.suelo = suelo;
	}

	public BossStage() {
		direccion = 0;
		alto = 50;
		ancho = 100;
		camara = 0;
		camaraMid = 0;
		camaraFar = 0;
		posCementerio = 0;
		posFondoLejano = 0;
		decorativo = new ArrayList<>();
		fondoLejano = new ArrayList<>();
		rectSuelo = new ArrayList<>();
		fondoCementerio = new ArrayList<>();
		suelo = new ArrayList<>();
	}

	public static ArrayList<Rectangle> getRectSuelo() {
		return rectSuelo;
	}

	public void setRectSuelo(ArrayList<Rectangle> rectSuelo) {
		this.rectSuelo = rectSuelo;
	}


	public void generarSuelo() {

			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo-columna.png")).getImage());
			decorativo.add(new ImageIcon(getClass().getResource("statue.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo-columna.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());
			
			

	}

	public void dibujar(Graphics g, int posSuelo, int camara) {

		int i = 0;
		
		while (i < 12) {
			generarSuelo();
			
			if (i == 1) {
				posSuelo = 100;
				g.drawImage(decorativo.get(0), 160, 60, 230, 280, null);
				g.drawImage(decorativo.get(0), 760, 60, 230, 280, null);
				
			}
			if (suelo.get(i).getHeight(null) > 50) {

				g.drawImage(suelo.get(i), posSuelo * i , 327, 100, 300, null);
				
				// g.fillRect( posSuelo*i-camara, 550, 100, 50);

			} else {

				g.drawImage(suelo.get(i), posSuelo * i , 500, 100, 120, null);
				
				if (i % 5 == 0) {

					// perro.setPosX((posSuelo*i-camara)+perro.getDireccion());
					// perro.dibujar(g);
				}
				// g.fillRect( posSuelo*i-camara, 550, 100, 50);

			}
			posY = 350;
			rectSuelo.add(new Rectangle(posSuelo * i , 550, 100, 50));
			if (i % 10 == -1) {
				g.drawImage(suelo.get(i), posSuelo * i , 350, 100, 120, null);
				g.fillRect(posSuelo * i , 400, 100, 50);
				rectSuelo.add(new Rectangle(posSuelo * i , 400, 100, 50));
				g.drawImage(suelo.get(i), posSuelo * i , 350, 100, 120, null);
				g.fillRect(posSuelo * i , 400, 100, 50);
				rectSuelo.add(new Rectangle(posSuelo * i , 400, 100, 50));
				g.drawImage(suelo.get(i), posSuelo * i , 350, 100, 120, null);
				g.fillRect(posSuelo * i , 400, 100, 50);
				posY = 400;
				rectSuelo.add(new Rectangle(posSuelo * i , 400, 100, 50));
			}
			i++;
		}

	}

	public boolean isFondoQuieto() {
		return fondoQuieto;
	}

	public void setFondoQuieto(boolean fondoQuieto) {
		this.fondoQuieto = fondoQuieto;
	}

}

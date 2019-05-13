package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

public class Escenario {
	private AreaJuego areaJuego;
	private Image fondoEstatico;
	private int posFondoLejano;
	private int posCementerio;
	private ArrayList<Image> fondoLejano;
	private ArrayList<Image> suelo;
	private ArrayList<Image> decoracion;
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
	private BossStage bossStage;
	private Image instrucciones;

	public AreaJuego getAreaJuego() {
		return areaJuego;
	}

	public void setAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
	}

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
		return decoracion;
	}

	public void setNivel2(ArrayList<Image> nivel2) {
		this.decoracion = nivel2;
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

	public Escenario() {
		instrucciones = new ImageIcon(getClass().getResource("arbolTUTO.png")).getImage();
		decoracion = new ArrayList();
		direccion = 0;
		alto = 50;
		ancho = 100;
		camara = 0;
		camaraMid = 0;
		camaraFar = 0;
		posCementerio = 0;
		posFondoLejano = 0;
		// areaJuego=new AreaJuego();
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

	public void moverFondo(int direccionFondo) {

		if (direccionFondo == KeyEvent.VK_D && !fondoQuieto) {
			camara += 7;
			posSuelo -= 10;
		}
		if (direccionFondo == KeyEvent.VK_A && !fondoQuieto) {
			camara -= 7;
			posSuelo += 10;

		}
	}

	public void generarSuelo() {
		decoracion.add(0, instrucciones);
		decoracion.add(1, new ImageIcon(getClass().getResource("bush-small.png")).getImage());
		decoracion.add(2, new ImageIcon(getClass().getResource("stone-2.png")).getImage());
		decoracion.add(3, new ImageIcon(getClass().getResource("stone-1.png")).getImage());
		decoracion.add(4, new ImageIcon(getClass().getResource("tree-2.png")).getImage());
		decoracion.add(5, new ImageIcon(getClass().getResource("tree-1.png")).getImage());
		decoracion.add(6, new ImageIcon(getClass().getResource("bush-large.png")).getImage());
		double r = Math.random();
		if (r < 0.5) {
			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo1.png")).getImage());
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());

		}
		if (r < 0.4) {
			suelo.add(new ImageIcon(getClass().getResource("suelo-columna.png")).getImage());

		}
		if (r > 0.5) {
			suelo.add(new ImageIcon(getClass().getResource("suelo2.png")).getImage());

		}
	}

	public void dibujar(Graphics g) {
		if(suelo.size() < 1000){
			generarSuelo();
		}
		
		int i = 0;
		
		while (i < suelo.size()) {

			if(i%7==0){
				g.drawImage(decoracion.get(2), posSuelo * i - camara, 430, 60, 100, null);
			}
			if(i%10==0){
				g.drawImage(decoracion.get(1), posSuelo * i - camara, 430, 100, 100, null);
			}
			if(i%19==0 && i!=0){
				g.drawImage(decoracion.get(5), posSuelo * i - camara, 127, 350, 400, null);
			}
			if (i == 1) {
				posSuelo = 100;
				g.drawImage(decoracion.get(0), posSuelo * i - camara, 127, 350, 400, null);
			}
			if (suelo.get(i).getHeight(null) > 50) {

				g.drawImage(suelo.get(i), posSuelo * i - camara, 327, 100, 300, null);
				// g.fillRect( posSuelo*i-camara, 550, 100, 50);


			} else {

				g.drawImage(suelo.get(i), posSuelo * i - camara, 500, 100, 120, null);
				if (i % 5 == 0) {

					// perro.setPosX((posSuelo*i-camara)+perro.getDireccion());
					// perro.dibujar(g);
				}
				// g.fillRect( posSuelo*i-camara, 550, 100, 50);

			}
			posY = 350;
			rectSuelo.add(new Rectangle(posSuelo * i - camara, 550, 100, 50));
			if (i % 10 == -1) {
				g.drawImage(suelo.get(i), posSuelo * i - camara, 350, 100, 120, null);
				//g.fillRect(posSuelo * i - camara, 400, 100, 50);
				rectSuelo.add(new Rectangle(posSuelo * i - camara, 400, 100, 50));
				g.drawImage(suelo.get(i), posSuelo * i - camara, 350, 100, 120, null);
				//g.fillRect(posSuelo * i - camara, 400, 100, 50);
				rectSuelo.add(new Rectangle(posSuelo * i - camara, 400, 100, 50));
				g.drawImage(suelo.get(i), posSuelo * i - camara, 350, 100, 120, null);
				//g.fillRect(posSuelo * i - camara, 400, 100, 50);
				posY = 400;
				rectSuelo.add(new Rectangle(posSuelo * i - camara, 400, 100, 50));


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

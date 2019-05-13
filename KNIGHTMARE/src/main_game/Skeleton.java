package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Skeleton extends Enemigo{

	private static final boolean LEFT = false;
	private static final boolean RIGHT = false;
	private static final boolean JUMP = false;
	private static final boolean ATACK = false;

	private int jugadorXRelativa = 0;
	private AreaJuego areaJuego;
	private int velocitySalto;
	private Personaje jugador;
	private boolean atacar = false;
	private boolean muerto = false;

	private boolean jugadorVisto = false;
	private int posX, posY;
	private Escenario escenario;
	private boolean finAtaque;
	private int anchoPersonaje, altoPersonaje;
	private int gravedad;
	private int tiempoAtaque;
	private int posicionReal = 0;
	private int olfato;

	private boolean collision;
	private boolean saltando = false;
	private int direccionFondo;
	private int gForce = 1;
	private int velocity = 0;
	private Image estado;
	private Image enemigoIzquierda;
	private Image enemigoDerecha;
	private Image enemigoStopIzquierda;
	private Image enemigoStopDerecha;
	private Image enemigoAtaqueIzquierda;
	private Image enemigoAtaqueDerecha;
	private Timer inteligencia;
	private Timer ataque;

	private Image esqueletoBorn;
	private Image esqueletoIzquierda;
	private Image squeletoDerecha;
	private Timer movimiento;
	private int limiteMax;
	private int limiteMin;

	public int getLimiteMax() {
		return limiteMax;
	}

	public void setLimiteMax(int limiteMax) {
		this.limiteMax = limiteMax;
	}

	public int getLimiteMin() {
		return limiteMin;
	}

	public void setLimiteMin(int limiteMin) {
		this.limiteMin = limiteMin;
	}

	// GETTERS Y SETTERS
	public int getPosicionReal() {
		return posicionReal;
	}

	public void setPosicionReal(int posicionReal) {
		this.posicionReal = posicionReal;
	}

	public int getDireccionFondo() {
		return direccionFondo;
	}

	public void setDireccionFondo(int direccionFondo) {
		this.direccionFondo = direccionFondo;
	}

	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(posicionReal + 50, posY + 40, 70, 170);

		return rect;

	}

	public int getAlto() {
		return anchoPersonaje;
	}

	public void setAncho(int alto) {
		this.anchoPersonaje = alto;
	}

	public int getAncho() {
		return altoPersonaje;
	}

	public void setAlto(int alto) {
		this.altoPersonaje = alto;
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

	public Image getEstado() {
		return estado;
	}

	public void setEstado(Image estado) {
		this.estado = estado;
	}

	public int getOlfato() {
		return olfato;
	}

	public void setOlfato(int olfato) {
		this.olfato = olfato;
	}
	public boolean isMuerto() {
		return muerto;
	}

	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}
	public Skeleton() {
		jugador = new Personaje();
		enemigoIzquierda = new ImageIcon(getClass().getResource("skeleton-clothed.gif")).getImage();
		enemigoDerecha = new ImageIcon(getClass().getResource("skeleton-clothed-right.gif")).getImage();
		enemigoStopIzquierda = new ImageIcon(getClass().getResource("skeleton-rise-clothed.gif")).getImage();
		enemigoStopDerecha = new ImageIcon(getClass().getResource("skeleton-rise-clothed.gif")).getImage();
		enemigoAtaqueIzquierda = new ImageIcon(getClass().getResource("skeleton-rise-clothed.gif")).getImage();
		enemigoAtaqueDerecha = new ImageIcon(getClass().getResource("skeleton-rise-clothed.gif")).getImage();
		escenario = new Escenario();
		posX=posicionReal;
		posY = 350;
		jugadorVisto = false;
		setEstado(enemigoIzquierda);
		registrarEventos();
	}

	private void registrarEventos() {
		movimiento = new Timer(55, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mover();
						
			}
		});
		movimiento.start();

	}

	public void mover() {
		if(posicionReal > limiteMin && getEstado() == enemigoIzquierda) {
			setEstado(enemigoIzquierda);
			setPosX(getPosX() - 5);
		}
		if(posicionReal == limiteMin){
			setEstado(enemigoDerecha);
		}
		if (posicionReal >= limiteMin && getEstado() == enemigoDerecha) {
			setPosX(getPosX() + 5);
		} 
		if(posicionReal == limiteMax){
			setEstado(enemigoIzquierda);
		}
	}

	public void dibujar(Graphics g) {
		//g.drawRect(getRect().x, getRect().y, getRect().width, getRect().height);
		g.drawImage(getEstado(), getPosicionReal(), getPosY(), 170, 200, null);
	}


}

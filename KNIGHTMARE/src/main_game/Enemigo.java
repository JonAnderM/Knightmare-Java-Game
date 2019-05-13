package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Enemigo {

	private static final boolean LEFT = false;
	private static final boolean RIGHT = false;
	private static final boolean JUMP = false;
	private static final boolean ATACK = false;
	private int velocitySalto;
	private int posX, posY;
	private int posicionMuerte;
	private int anchoPersonaje, altoPersonaje;
	private int gravedad;
	private int tiempoAtaque;
	private int posicionReal;
	private int olfato;
	private int numParpadeo = 0;
	private int direccionFondo;
	private int gForce = 1;
	private int velocity = 0;
	private int jugadorXRelativa = 0;
	private boolean atacar = false;
	private boolean muerto = false;
	private boolean jugadorVisto = false;
	private boolean collision;
	private boolean saltando = false;
	private boolean finAtaque;
	private AreaJuego areaJuego;
	private Personaje jugador;
	private Escenario escenario;
	private Image esqueletoBorn;
	private Image esqueletoIzquierda;
	private Image squeletoDerecha;
	private Image fuegoMuerte;
	private Image estado;
	private Image enemigoIzquierda;
	private Image enemigoDerecha;
	private Image enemigoStopIzquierda;
	private Image enemigoStopDerecha;
	private Image enemigoAtaqueIzquierda;
	private Image enemigoAtaqueDerecha;
	private Timer inteligencia;
	private Timer ataque;
	private Timer parpadeo;


	// GETTERS Y SETTERS
	public boolean isMuerto() {
		return muerto;
	}

	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}

	public int getOlfato() {
		return olfato;
	}

	public void setOlfato(int olfato) {
		this.olfato = olfato;
	}
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
		rect.setBounds(posicionReal + 60, posY + 50, 160, 110);

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

	public Enemigo() {

		jugador = new Personaje();
		enemigoIzquierda = new ImageIcon(getClass().getResource("hell-hound-run.gif")).getImage();
		fuegoMuerte = new ImageIcon(getClass().getResource("enemy-death.gif")).getImage();
		enemigoDerecha = new ImageIcon(getClass().getResource("hero-idle.gif")).getImage();
		enemigoStopIzquierda = new ImageIcon(getClass().getResource("hell-hound-idle.gif")).getImage();
		enemigoStopDerecha = new ImageIcon(getClass().getResource("hell-hound-idle.gif")).getImage();
		enemigoAtaqueIzquierda = new ImageIcon(getClass().getResource("hell-hound-attack.gif")).getImage();
		enemigoAtaqueDerecha = new ImageIcon(getClass().getResource("hell-hound-idle.gif")).getImage();
		escenario = new Escenario();
		//posX = 900;
		posY = 400;
		jugadorVisto = false;
		setEstado(enemigoStopIzquierda);
		registrarEventos();
	}

	private void registrarEventos() {
		ataque = new Timer(300, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				atacar();
				
				
			}

		});
		ataque.stop();
		parpadeo = new Timer(300, new ActionListener() {

			

			public void actionPerformed(ActionEvent e) {

				if(getPosX() == 4000){
					setPosX(posicionMuerte);
					
				}else{
					setPosX(4000);
					
				}
				numParpadeo++;
				if(numParpadeo>=1){
					numParpadeo=0;
					
					parpadeo.stop();
				}

			}

		});
		
		inteligencia = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				olfato = jugador.getPosicionRealMapa() + 100;
			}

		});
		inteligencia.start();

	}

	public Timer getParpadeo() {
		return parpadeo;
	}

	public void setParpadeo(Timer parpadeo) {
		this.parpadeo = parpadeo;
	}

	public void dibujar(Graphics g) {
		//g.drawRect(getRect().x, getRect().y, getRect().width, getRect().height);
		g.drawImage(getEstado(), getPosicionReal(), getPosY(), 270, 150, null);
	}

	public void atacar() {
		setEstado(enemigoAtaqueIzquierda);

	}

	public void perseguir() {
		//System.out.println(getRect() +"--"+ jugador.getPosicionRealMapa());
		if (getRect().intersects(jugador.getRect())) {
			ataque.start();
			

		} else {
			if (getPosicionReal() - 200 < jugador.getPosicionRealMapa()) {
				setEstado(enemigoIzquierda);
				//System.out.println(getPosicionReal()+"---jugador:"+jugador.getPosicionRealMapa());
				posX -= 10;
			} else {
				//System.out.println("voy a la dcha");
				posX += 10;
			}

		}

	}

	public Timer getAtaque() {
		return ataque;
	}

	public void setAtaque(Timer ataque) {
		this.ataque = ataque;
	}

	public void mover() {
		System.out.println("me quiero mover");
		Random r = new Random();

		if (r.nextInt(2) == 1) {

		}

	}
	public void matar(){
		setMuerto(true);
		setEstado(fuegoMuerte);
		posicionMuerte = getPosX();
		//setPosX(4000);
		parpadeo.start();
	}

	public int getDireccion() {
		// TODO Auto-generated method stub
		return 0;
	}

}

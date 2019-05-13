package main_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AreaJuego extends Canvas {
	private static final int FIN_MAPA = 3000;
	protected static final int PRINCIPIO_MAPA = 300;
	// DATOS
	private Personaje jugador;
	//SONIDOS

	private Sonido ataqueEnemigo;
	private Sonido ataqueSacerdote;
	private Sonido vozSacerdote;

	//--------------------------------
	
	private Sonido musicaIntro;
	private int nivel;
	private float gravedad = 9;
	private int camara = 0;
	private Escenario escenario;
	private FondoParallax fondoParallax;
	private Enemigo perro;

	// PARA EL DOBLE BUFFER
	private Graphics pantVirtual;
	private Image buffer;
	private Timer reloj;

	// PARA PANTALLA INICIAL
	private Image arbol;
	private Image cruz;
	private Image lapida;
	private Image piedra;
	private Image arbolGrande;
	private Image youDied;
	
	
	private Image arbusto;
	private Image arbustoGrande;
	private Image pantallaWin;
	private Image healthBar;
	
	//private int vidaSacerdote=30;
	private int vidaSacerdote=256;
	private ArrayList<Enemigo> enemigos;
	private ArrayList<Skeleton> esqueletos;
	
	private Timer movimiento;
	private Enemigo enemigo;
	private Enemigo enemigo2;
	private Enemigo enemigo3;
	private Timer inteligencia;
	private Skeleton esqueleto1;
	private Skeleton esqueleto2;
	private Timer muerteEnemigo;
	private Skeleton esqueleto3;
	private Menu menu;
	private boolean unicoPlay=false;
	private boolean juegoEmpezado;
	private BossStage bossStage;
	private boolean bossFinal=false;
	private boolean bossMuerto=false;
	private Sacerdote sacerdote;
	private Sonido musicaMain;
	private Sonido sonidoATKSacerdote;
	private Sonido musicaBoss;
	
	private Timer relojDañoSacerdote;
	private int puntuacion=0;
	
	private Sonido musicaMuerte;
	private boolean ganador = false;
	private Sonido musicaWin;
	private Timer animacionWin;
	
	// GETTERS Y SETTERS
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	// CONSTRUCTOR
	public AreaJuego() {
		youDied = new ImageIcon(getClass().getResource("youDied.gif")).getImage();
		pantallaWin = new ImageIcon(getClass().getResource("pantallaWin.gif")).getImage();
		healthBar = new ImageIcon(getClass().getResource("healthBar.png")).getImage();
		musicaIntro = new Sonido("introTheme.wav");
		musicaMuerte = new Sonido("muertoMusica.wav");
		musicaMain = new Sonido("mainTheme.wav");
		musicaBoss = new Sonido("bossTheme.wav");
		musicaWin = new Sonido("winMusica.wav");
		sonidoATKSacerdote = new Sonido("ataqueSacerdote.wav");
		bossStage = new BossStage();
		bossStage.setFondoQuieto(false);
		menu=new Menu();
		fondoParallax = new FondoParallax();
		escenario = new Escenario();
		sacerdote = new Sacerdote();
		enemigo = new Enemigo();
		enemigo.setPosX(3000);
		enemigo2 = new Enemigo();
		enemigo3 = new Enemigo();
		enemigos = new ArrayList();
		enemigo3.setPosX(1300);
		enemigo2.setPosX(2200);
		musicaIntro.playLoop();
		esqueletos = new ArrayList();
		esqueleto1 = new Skeleton();
		esqueleto1.setPosX(600);		
		esqueleto1.setLimiteMin(200);
		esqueleto1.setLimiteMax(599);	
		esqueleto2 = new Skeleton();
		esqueleto2.setPosX(1400);
		esqueleto2.setLimiteMin(900);
		esqueleto2.setLimiteMax(1399);

		esqueleto3 = new Skeleton();
		esqueleto3.setPosX(2500);
		esqueleto3.setLimiteMin(2300);
		esqueleto3.setLimiteMax(2999);

		requestFocus();
		nivel = 1;
		jugador = new Personaje();
		jugador.setPosX(0);
		jugador.setPosY(350);
		enemigos.add(enemigo);
		enemigos.add(enemigo2);
		enemigos.add(enemigo3);
		esqueletos.add(esqueleto1);
		esqueletos.add(esqueleto2);
		esqueletos.add(esqueleto3);
		juegoEmpezado=false;
		registrarEventos();
	}

	// METODOS
	public void registrarEventos() {
		this.addKeyListener(new KeyAdapter() {


			public void keyPressed(KeyEvent e) {
				if(!juegoEmpezado){
					if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP){
						menu.setElegido(menu.getStart());
					}
					if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){
						menu.setElegido(menu.getOptions());
					}
					if(menu.getElegido()==menu.getStart() && e.getKeyCode()==KeyEvent.VK_ENTER){
						juegoEmpezado=true;
						musicaIntro.detener();
						musicaMain.playLoop();
					}
				}
				if(bossFinal){
					musicaMain.detener();
					musicaBoss.playLoop();
				}

			}
		});
		animacionWin = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sacerdote.setPosY(sacerdote.getPosY()-10);
				//sacerdote.getParpadeo().start();

			}
		});

		inteligencia = new Timer(30, new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				for(int i=0; i< enemigos.size();i++){
					if (enemigos.get(i).getOlfato() > enemigos.get(i).getPosicionReal() && !enemigos.get(i).getAtaque().isRunning()) {
						enemigos.get(i).perseguir();
						esqueletos.get(i).mover();

					}
					if (enemigos.get(i).getRect().intersects(jugador.getRect())) {
						enemigos.get(i).getAtaque().start();
						enemigos.get(i).setPosX(enemigos.get(i).getPosX());
						jugador.dañoRecibido();
					}
					if(esqueletos.get(i).getRect().intersects(jugador.getRect())){
						jugador.dañoRecibido();
					}
					if (jugador.isFinAtaque()) {
						matarEnemigo();
					}

				}

			}

		});
		inteligencia.start();
		reloj = new Timer(20, new ActionListener() {


			public void actionPerformed(ActionEvent e) {

				if(jugador.getPosicionRealMapa()<3700 && !bossFinal){
					controlarLimites();
					repaint();
					//System.out.println("ENTRO EN LIMITES");
					bossFinal=false;
				}else{

					//jugador.setPosicionRealMapa(5000);
					bossStage.setFondoQuieto(true);

					pantallaFinal();
					bossFinal=true;
					//System.out.println("BOOOSSS");
					repaint();
				}
				if(jugador.isPerdido() && !unicoPlay || jugador.isPerdido() && bossFinal){
					unicoPlay=true;
					
					sonidoATKSacerdote.detener();
					musicaWin.detener();
					musicaMain.detener();
					musicaBoss.detener();
					musicaMuerte.playLoop();
				}

			}
		});
		reloj.start();
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				jugador.soltarTeclas(e.getKeyCode());
			}

			public void keyPressed(KeyEvent e) {
				jugador.modificarMovimiento(e.getKeyCode());
			}
		});
		relojDañoSacerdote = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vidaSacerdote-=30; 
				relojDañoSacerdote.stop();

			}
		});
	}


	public Personaje getJugador() {
		return jugador;
	}

	public void setJugador(Personaje jugador) {
		this.jugador = jugador;
	}

	protected void controlarLimites() {

		jugador.setPosicionRealMapa(jugador.getPosX() + escenario.getCamara());

		for(int i=0;i<enemigos.size();i++){
			esqueletos.get(i).setPosicionReal(esqueletos.get(i).getPosX() - escenario.getCamara());
			enemigos.get(i).setPosicionReal(enemigos.get(i).getPosX() - escenario.getCamara());
		}

		if (jugador.getPosX() > FIN_MAPA) {

			jugador.setPosX(0);
		}
		if (jugador.getDireccionFondo() == 68) {
			if (jugador.getPosX() < 400 || jugador.getPosicionRealMapa() > FIN_MAPA) {
				escenario.setFondoQuieto(true);
				jugador.mover();
			} 	else {
				escenario.setFondoQuieto(false);
				jugador.saltarBgMov();
				escenario.moverFondo(jugador.getDireccionFondo());
				fondoParallax.moverFondo(jugador.getDireccionFondo());
			}
		} else {
			if (escenario.getCamara() < 10) {
				escenario.setFondoQuieto(true);
				jugador.mover();
			} else {
				escenario.setFondoQuieto(false);
				jugador.saltarBgMov();
				escenario.moverFondo(jugador.getDireccionFondo());
				fondoParallax.moverFondo(jugador.getDireccionFondo());
			}
		}

	}
	public void matarEnemigo() {
		for(int i=0;i<enemigos.size();i++){
			if (jugador.getRectAtaque().intersects(enemigos.get(i).getRect())) {
				enemigos.get(i).matar();

				if(jugador.isFinAtaque()){
					puntuacion+=1;
				}

			}
			if (jugador.getRectAtaque().intersects(esqueletos.get(i).getRect())) {
				esqueletos.get(i).matar();
				if(jugador.isFinAtaque()){
					puntuacion+=1;
				}
			}
		}
		if(bossFinal && jugador.getRectAtaque().intersects(sacerdote.getRect())){
			relojDañoSacerdote.start();
			if(vidaSacerdote <= 0){
				ganador = true;
				sacerdote.setPosY(1000);
				animacionWin.start();
				musicaBoss.detener();
				musicaWin.playLoop();
			}

		}

	}

	@Override
	public void paint(Graphics g) {
		if(!ganador){



			if(juegoEmpezado){
				if(bossFinal){
					fondoParallax.dibujarEstatico(g);
					bossStage.dibujar(g, 0, 0);
					sacerdote.dibujar(g);
					jugador.dibujar(g);
					sacerdote.dibujar(g);
					g.drawImage(healthBar, 400,580,300,100,null);
					g.setColor(Color.RED.darker().darker());
					g.fillRect(423, 605, vidaSacerdote, 20);

				}else{
					fondoParallax.dibujar(g);
					escenario.dibujar(g);
					for(int i=0;i<enemigos.size();i++){
						enemigos.get(i).dibujar(g);
						esqueletos.get(i).dibujar(g);
					}
					jugador.dibujar(g);
					g.setColor(Color.WHITE);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
					g.drawString("points:"+puntuacion, 1040, 40);
					//System.out.println(jugador.getPosicionRealMapa());
				}
			}else{
				menu.dibujar(g);
			}
		}else{
			g.drawImage(pantallaWin, 0, 0, 1200,720, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
			g.drawString("YOU WIN", 260, 280);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("New Record: "+puntuacion, 470, 330);
			sacerdote.getColisionAtaque().setBounds(0, 0, 0, 0);
		}




	}
	public void pantallaFinal(){
		if(!bossFinal){
			jugador.setPosX(-80);
		}else
			fondoParallax.setPosCementerio(0);
		fondoParallax.setPosFondoLejano(0);
		jugador.setPosicionRealMapa(jugador.getPosX() );
		bossStage.setFondoQuieto(true);
		escenario.setFondoQuieto(true);
		jugador.mover();

		if(!unicoPlay && jugador.getRect().intersects(sacerdote.getColisionAtaque())){

			sacerdote.getAtaque().start();
			if(sacerdote.isDaño()){
				sonidoATKSacerdote.playLoop();
				jugador.dañoRecibido();
			}

		}else{

			sacerdote.getAtaque().stop();
			sonidoATKSacerdote.detener();
			if(jugador.getPosX() > sacerdote.getPosX()){
				sacerdote.ataqueDer();
			} else {
				sacerdote.ataqueIzq();
			}
		}


	}
	public void update(Graphics g) {
		buffer = createImage(1200, 720);
		pantVirtual = buffer.getGraphics();
		// super.update(g);
		paint(pantVirtual);
		// VOLCAR LA IMAGEN CREADA DE LA PANTALLA VIRTUAL SOBRE LA PANTALLA REAL
		if(jugador.isPerdido()){
			g.drawImage(youDied, 0, 0, 1200, 720, null);
		}else{
			g.drawImage(buffer, 0, 0, 1200, 720, null);
		}

	}
}

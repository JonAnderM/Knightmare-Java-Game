package main_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;

public class Personaje {
	private Sonido ataquePersonaje;
	private ArrayList<Sonido> personajeAndar;
	private ArrayList<Sonido> dañoPersonaje;
	private ArrayList<Sonido> vozPersonaje;
	private static final boolean LEFT = false;
	private static final boolean RIGHT = false;
	private static final boolean JUMP = false;
	private static final boolean ATACK = false;
	private static int posicionRealMapa;
	private boolean perVisible;
	private boolean perdido=false;
	

	private int tiempoImmortal = 0;
	private Vida vidas;
	private Image playerDerecha;
	private Image playerIzquierda;
	private Image playerSaltoDerecha;
	private Image playerSaltoIzquierda;
	private Image playerAtaqueDerecha;
	private Image playerAtaqueIzquierda;
	private Image playerStopDerecha;
	private Image playerStopIzquierda;
	private Image playerSalto;
	private Image estado;
	private Image fotoDaño;
	private int jugadorXRelativa = 0;
	private int velocitySalto;
	private Enemigo perro;
	private boolean teclas[];
	private int posX, posY;
	private Escenario escenario;
	private Timer salto;
	private boolean finAtaque;
	private int anchoPersonaje, altoPersonaje;
	private int gravedad;
	private Timer ataque;
	private int tiempoAtaque;
	private boolean collision;
	private boolean saltando = false;
	private Timer timeColision;
	private int direccionFondo;
	private Timer gravity;
	private int gForce = 1;
	private int numParpadeo = 3;
	private int velocity = 0;
	private Timer isColision;
	private Timer tiempoEterno;
	private Image dañoDerecha;
	private Timer timeDaño;
	private int vidasRestantes;
	private Timer parpadeo;
	private int posicionMuerte;
	

	// GETTERS Y SETTERS
	public boolean isPerdido() {
		return perdido;
	}

	public void setPerdido(boolean perdido) {
		this.perdido = perdido;
	}
	public int getDireccionFondo() {
		return direccionFondo;
	}

	public void setDireccionFondo(int direccionFondo) {
		this.direccionFondo = direccionFondo;
	}

	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(posX + 70, posY + 190, anchoPersonaje - 10, altoPersonaje - 90);

		return rect;

	}

	public Rectangle getRectAtaque() {
		Rectangle rectAtaque = new Rectangle();
		if (getEstado() == playerAtaqueDerecha) {
			rectAtaque.setBounds(posX + 150, posY + 80, anchoPersonaje, altoPersonaje - 50);
		} else {
			rectAtaque.setBounds(posX - 20, posY + 80, anchoPersonaje, altoPersonaje - 50);
		}

		return rectAtaque;

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

	// CONSTRUCTOR
	public Personaje() {
		vidas = new Vida();
		ataquePersonaje = new Sonido("ataqueJugador.wav");
		personajeAndar = new ArrayList();
		personajeAndar.add(new Sonido("pieDerecho.wav"));
		dañoPersonaje = new ArrayList();
		dañoPersonaje.add(new Sonido("personaeDaño2.wav"));
		vozPersonaje = new ArrayList();
		
		perVisible=true;
		vidasRestantes=5;
		finAtaque = false;
		collision = true;
		escenario = new Escenario();
		velocitySalto = 20;
		teclas = new boolean[10];
		dañoDerecha = new ImageIcon(getClass().getResource("hero-hit-right.gif")).getImage();
		estado = new ImageIcon(getClass().getResource("hero-idle.gif")).getImage();
		playerStopDerecha = new ImageIcon(getClass().getResource("hero-idle.gif")).getImage();
		playerStopIzquierda = new ImageIcon(getClass().getResource("hero-idle-left.gif")).getImage();
		playerDerecha = new ImageIcon(getClass().getResource("hero-run.gif")).getImage();
		playerIzquierda = new ImageIcon(getClass().getResource("hero-left-run.gif")).getImage();
		playerAtaqueDerecha = new ImageIcon(getClass().getResource("hero-attack.gif")).getImage();
		playerAtaqueIzquierda = new ImageIcon(getClass().getResource("hero-attack-left.gif")).getImage();
		playerSaltoDerecha = new ImageIcon(getClass().getResource("hero-jump.gif")).getImage();
		playerSaltoIzquierda = new ImageIcon(getClass().getResource("hero-jump-left.gif")).getImage(); // .getScaledInstance(100,
		// 200,
		// Image.SCALE_FAST);
		altoPersonaje = 100;
		anchoPersonaje = 100;
		posX = 0;
		posicionRealMapa = 0;
		posY = 600;
		teclas[0] = false; // Izquierda
		teclas[1] = false; // Derecha
		teclas[2] = false; // Saltar
		teclas[3] = false; // atacar
		teclas[4] = false; // Izquierda
		teclas[5] = false; // Izquierda
		teclas[6] = false; // Izquierda

		registrarEventos();

	}

	public int getPosicionRealMapa() {
		return posicionRealMapa;
	}

	public void setPosicionRealMapa(int posicionRealMapa) {
		this.posicionRealMapa = posicionRealMapa;
	}

	// METODOS
	public void registrarEventos() {

		salto = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				saltando = true;
				saltar();
				if (teclas[0]) {
					setEstado(playerSaltoIzquierda);
				}
				if (teclas[1]) {
					setEstado(playerSaltoDerecha);
				}
				if (collision) {
					if (getEstado() == playerSaltoDerecha && direccionFondo == 0) {
						setEstado(playerStopDerecha);
					}
					if (getEstado() == playerSaltoIzquierda && direccionFondo == 0) {
						setEstado(playerStopIzquierda);
					}
					if (getEstado() == playerSaltoDerecha && direccionFondo != 0) {
						setEstado(playerDerecha);
					}
					if (getEstado() == playerSaltoIzquierda && direccionFondo != 0) {
						setEstado(playerIzquierda);
					}
					salto.stop();
					saltando = false;
					return;
				}
			}
		});

		ataque = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!finAtaque) {
					
					ataquePersonaje.playLoop();
					finAtaque = true;
					
				} else {
					
					finAtaque = false;
					if (getEstado() == playerAtaqueDerecha) {
						setEstado(playerStopDerecha);
					} else {
						setEstado(playerStopIzquierda);
					}
					ataquePersonaje.detener();
					ataque.stop();
				}
			}
		});
		gravity = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!collision) {
					gravedad += 1;
				}

			}
		});
		gravity.start();
		parpadeo = new Timer(100, new ActionListener() {

			

			public void actionPerformed(ActionEvent e) {
				
				if(perVisible){
					dañoPersonaje.get(0).playLoop();
					perVisible=false;
					
				}else{
					perVisible=true;
					
				}
				numParpadeo++;
				if(numParpadeo==4){
					if(vidasRestantes==0){
						perdido=true;
						vidasRestantes=0;
					}else{
					vidasRestantes-=1;
					}
				}
				if(numParpadeo>=10){
					numParpadeo=0;
					perVisible=true;
					dañoPersonaje.get(0).detener();
					parpadeo.stop();
				}

			}

		});

		timeColision = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (i < escenario.getRectSuelo().size() && !collision) {
					if (getRect().intersects(escenario.getRectSuelo().get(i))) {
						collision = true;
						gravedad = 0;

					} else {
						collision = false;

					}
					i++;
				}
			}
		});
		timeColision.start();

		tiempoEterno = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				while (i < escenario.getRectSuelo().size()) {
					if (collision) {
						collision = true;
						velocity = 0;

					} else {
						collision = false;

					}
					i++;
				}
				velocity = 0;

			}
		});
		tiempoEterno.start();

	}

	public boolean isFinAtaque() {
		return finAtaque;
	}

	public void setFinAtaque(boolean finAtaque) {
		this.finAtaque = finAtaque;
	}

	public void modificarMovimiento(int teclasPulsadas) {

		if (teclasPulsadas == KeyEvent.VK_A && !saltando) {
			teclas[0] = true;
			teclas[1] = false; // Izquierda
			direccionFondo = teclasPulsadas;
			setEstado(playerIzquierda);
		}
		if (teclasPulsadas == KeyEvent.VK_A && saltando) {
			teclas[0] = true; // Izquierda
			direccionFondo = teclasPulsadas;
		}

		if (teclasPulsadas == KeyEvent.VK_D && !saltando) {
			teclas[1] = true;
			teclas[0] = false; // Derecha
			direccionFondo = teclasPulsadas;
			setEstado(playerDerecha);
		}
		if (teclasPulsadas == KeyEvent.VK_D && saltando) {
			teclas[1] = true; // Derecha
			direccionFondo = teclasPulsadas;
		}

		if (teclasPulsadas == KeyEvent.VK_J) {
			teclas[2] = true; // Saltar
			collision = false;
			if (getEstado() == playerDerecha || getEstado() == playerStopDerecha) {
				setEstado(playerSaltoDerecha);
			}
			if (getEstado() == playerIzquierda || getEstado() == playerStopIzquierda) {
				setEstado(playerSaltoIzquierda);
			}
		}
		if (teclasPulsadas == KeyEvent.VK_K) {
			teclas[3] = true; // atacar
			if (getEstado() == playerDerecha || getEstado() == playerStopDerecha || getEstado() == playerSaltoDerecha|| getEstado() == playerAtaqueDerecha) {
				setEstado(playerAtaqueDerecha);
			} else {
				setEstado(playerAtaqueIzquierda);
			}
			ataque.start();
			

		}
	}

	public void soltarTeclas(int teclaReleased) {
		if (teclaReleased == KeyEvent.VK_A && !saltando) {
			teclas[0] = false; // Izquierda
			setEstado(playerStopIzquierda);
			direccionFondo = 0;
		}
		if (teclaReleased == KeyEvent.VK_A && saltando) {
			teclas[0] = false; // Izquierda
			direccionFondo = 0;
		}
		if (teclaReleased == KeyEvent.VK_D && !saltando) {
			teclas[1] = false; // Derecha
			setEstado(playerStopDerecha);
			direccionFondo = 0;
		}
		if (teclaReleased == KeyEvent.VK_D && saltando) {
			teclas[1] = false; // Derecha
			direccionFondo = 0;
		}
		if (teclaReleased == KeyEvent.VK_J) {
			teclas[2] = false; // Saltar
		}
		if (teclaReleased == KeyEvent.VK_K) {
			teclas[3] = false; // atacar
			
		}

	}

	private void saltar() {
		setPosY(posY - velocitySalto + gravedad);
	}

	public void mover() {
		
		velocitySalto = 20;
		if (teclas[0]) {
			if (getPosX() >= -15)
				setPosX(getPosX() - 15);

			if (teclas[2])
				salto.start();
		}
		if (teclas[1]) {

			setPosX(getPosX() + 15);
		}
		if (teclas[2]) {
			salto.start();
		}
		if (teclas[3]) {
			ataque.start();
		}

		posicionMuerte=getPosX();
	}

	public void saltarBgMov() {
		velocitySalto = 20;
		if (teclas[0] && teclas[2]) {
			salto.start();
		}

		if (teclas[2]) {
			salto.start();
		}
		if (teclas[3]) {
			ataque.start();
		}
	}

	public void dibujar(Graphics g) {
		//g.drawRect(getRect().x, getRect().y, getRect().width, getRect().height);
		//g.drawRect(getRectAtaque().x, getRectAtaque().y, getRectAtaque().width, getRectAtaque().height);
		if(perVisible){
			g.drawImage(getEstado(), getPosX(), getPosY(), 250, 200, null);
		}
		
		dibujarVidas(g);
	}

	private void dibujarVidas(Graphics g) {
		g.drawImage(vidas.getVidasJugador().get(vidasRestantes),0,-100,260,260,null);

	}

	public boolean getCollision() {
		return collision;
	}

	public void dañoRecibido() {
		parpadeo.start();
		//timeDaño.start();

	}

	public void setEstadoInicial() {
		setEstado(playerStopDerecha);
	}

}

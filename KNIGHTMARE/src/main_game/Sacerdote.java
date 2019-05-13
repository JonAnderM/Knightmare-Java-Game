package main_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Sacerdote extends Enemigo {
	private static final int ANCHO = 380;
	private static final int ALTO= 500;
	private int pares=1;
	private boolean daño;
	
	private Sonido voz;
	private Image healthBar;
	private int vidaSacerdote=300;
	private Image sacerdoteStop;
	private Image sacerdoteStopIzq;
	private Image sacerdoteAtaqueDer;
	private Image estadoSacerdote;
	private Timer movimiento;
	private Timer ataque;
	private int posicionSacerdote;
	private Rectangle colisionSacerdote;
	private Rectangle colisionAtaque;
	private Image sacerdoteAtaqueIzq;
	//Getters and Setters
	public Timer getAtaque() {
		return ataque;
	}
	public void setAtaque(Timer ataque) {
		this.ataque = ataque;
	}
	public Rectangle getColisionAtaque() {
		return colisionAtaque;
	}
	public void setColisionAtaque(Rectangle colisionAtaque) {
		this.colisionAtaque = colisionAtaque;
	}

	public Rectangle getColisionSacerdote() {
		return colisionSacerdote;
	}
	public void setColisionSacerdote(Rectangle colisionSacerdote) {
		this.colisionSacerdote = colisionSacerdote;
	}
	public boolean isDaño() {
		return daño;
	}
	public void setDaño(boolean daño) {
		this.daño = daño;
	}
	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(posicionSacerdote + 70, 90 + 190, ANCHO - 10, ALTO - 90);

		return rect;

	}
	
//CONSTRUCTOR
	
	public Sacerdote(){
		
		voz = new Sonido("vozSacerdote.wav");
		sacerdoteStop = new ImageIcon(getClass().getResource("sacerdoteStop.gif")).getImage();
		sacerdoteStopIzq = new ImageIcon(getClass().getResource("sacerdoteStopIzq.gif")).getImage();
		sacerdoteAtaqueDer = new ImageIcon(getClass().getResource("sacerdoteAtaqueDerecha.gif")).getImage();
		sacerdoteAtaqueIzq = new ImageIcon(getClass().getResource("sacerdoteAtaqueIzquierda.gif")).getImage();
		posicionSacerdote = 400;
		colisionSacerdote = new Rectangle();
		colisionAtaque = new Rectangle();
		setPosX(posicionSacerdote);
		

		
		movimiento = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movimientoSacerdote();

				
			}
		});
		//movimiento.start();
		ataque = new Timer(1500, new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pares%2==0 ){
					daño=true;
					
				}else{
					daño=false;
				}
				if(estadoSacerdote == sacerdoteStop){
					estadoSacerdote = sacerdoteAtaqueDer;
				}
				if(estadoSacerdote == sacerdoteStopIzq){
					estadoSacerdote = sacerdoteAtaqueIzq;
				}
				pares++;
				
			}
			
		});
	}
	public void dibujar(Graphics g){
		
		g.drawImage(estadoSacerdote, getPosX(), 90, ANCHO, ALTO, null);
		if(estadoSacerdote == sacerdoteAtaqueDer || estadoSacerdote == sacerdoteStop){
			//cuadrado enemigo
			//g.drawRect(getPosX() + 100, 200, ANCHO - 230, ALTO-150);
			colisionSacerdote.setBounds(getPosX() + 100, 200, ANCHO - 230, ALTO-150);
			//Cuadrado ataque
			//g.drawRect(getPosX() + 100 +100, 200, 200, ALTO - 150);
			colisionAtaque.setBounds(getPosX() + 100 +100, 200, 200, ALTO - 150);
		}else{
			
			
			//cuadrado enemigo
			//g.drawRect(getPosX() + 100, 200, ANCHO - 230, ALTO-150);
			colisionSacerdote.setBounds(getPosX() + 100, 200, ANCHO - 230, ALTO-150);
			//Cuadrado ataque
			//g.drawRect(getPosX() - 100 +100, 200, 200, ALTO - 150);
			colisionAtaque.setBounds(getPosX() - 100 , 200, 200, ALTO - 150);
		}

		
		
	}
	
	public void movimientoSacerdote(){
		
		estadoSacerdote = sacerdoteStop;
		System.out.println(posicionSacerdote);
		setPosX(posicionSacerdote + 5);
	}
	public void ataqueDer(){
		
		estadoSacerdote = sacerdoteStop;

		
	}
	
	public void ataqueIzq(){

		estadoSacerdote = sacerdoteStopIzq;

		
		
	}

}

package main_game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Menu extends Canvas {
	
	private Image fondo, start, options;
	private Image elegido;
	
	public Image getFondo() {
		return fondo;
	}

	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}

	public Image getStart() {
		return start;
	}

	public void setStart(Image start) {
		this.start = start;
	}

	public Image getOptions() {
		return options;
	}

	public void setOptions(Image options) {
		this.options = options;
	}

	public Image getElegido() {
		return elegido;
	}

	public void setElegido(Image elegido) {
		this.elegido = elegido;
	}
	
	public Menu(){
		fondo = new ImageIcon(getClass().getResource("fondoMenu.gif")).getImage();
		start = new ImageIcon(getClass().getResource("start.gif")).getImage();
		options = new ImageIcon(getClass().getResource("options.gif")).getImage();
		elegido = start;
		registrarEventos();
	}
	
	private void registrarEventos() {
		

	}

	public void dibujar(Graphics g) {

		g.drawImage(fondo, 0, 0, 1200, 720, null);
		g.drawImage(elegido, 100, 150, 1000, 520, null);
		

		
	}
}

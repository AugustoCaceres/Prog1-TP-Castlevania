package juego;

import entorno.Entorno;
import java.awt.Color;
import java.awt.Image;
import entorno.Herramientas;

public class Commodore {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Image commodore;
	
	public Commodore(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.alto = 30;
		this.commodore = Herramientas.cargarImagen("commodore-128.png");
	}
	
	public void dibujarse (Entorno entorno) {
		
		entorno.dibujarRectangulo(this.x, this.y - 30, this.ancho, this.alto, 0, Color.blue);
		entorno.dibujarImagen(commodore, this.x, this.y - 30, 0, 0.09);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	
}

package juego;

import java.awt.Color;
import entorno.Entorno;

public class Piso {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Piso(int x, int y) {
		this.x = x;
		this.y = y;
		this.alto = 10;
		this.ancho = 800;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.white);
	}

	public int extremoIzquierdo() {
		return this.getX() - this.getAncho()/2;
	}
	
	public int extremoDerecho() {
		return this.getX() + this.getAncho()/2;
	}
	
	public double dondeEmpiezaElSuelo() {
		return this.y - (this.alto / 2) + 2;
	}
	
}

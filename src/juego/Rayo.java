package juego;

import entorno.Entorno;

public class Rayo {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private boolean activo;
	
	public Rayo (int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 20;
		this.alto = 5;
		this.activo = false;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void dibujarse(Entorno entorno, String direccion) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, null);
	}

	public void moverDerecha() {
		this.x = this.x + 5;
	}
	
	public void moverIzquierda() {
		this.x = this.x - 5;
	}
	
}

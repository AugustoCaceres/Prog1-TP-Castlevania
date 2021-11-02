package juego;

import entorno.Entorno;

public class RayoLaser {
	
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private boolean activo;
	private String sentido;
	
	public RayoLaser(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.alto = 5;
		this.ancho = 20;
		this.activo = false;
		this.sentido = "izquierda";
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
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, null);
	}

	public void moverDerecha() {
		this.x = this.x + 5;
	}
	
	public void moverIzquierda() {
		this.x = this.x - 5;
	}
	
}

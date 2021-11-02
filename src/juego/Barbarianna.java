package juego;

import java.awt.Color;

import entorno.Entorno;

public class Barbarianna {
	
	private int x;
	private int y;
	private int altura;
	private int ancho;
	private boolean saltando;
	private boolean agachada;
	private boolean escudo;
	
	public Barbarianna(int x, int y) {
		this.x = x;
		this.y = y;
		this.altura = 50;
		this.ancho = 30;
		this.saltando = false;
		this.agachada = false;
		this.escudo = false;
	}
	
	public void dibujarse(Entorno entorno) {
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada('w')) {
			this.saltar(entorno);
			this.agachada = false;
		} else if (entorno.estaPresionada(entorno.TECLA_ABAJO) || entorno.estaPresionada('s')) {
			this.agacharse(entorno);
			this.saltando = false;
		} else if (entorno.estaPresionada('c')) {
			this.cubrirse(entorno);
			this.saltando = false;
			this.agachada = false;
		} else {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura, 0, Color.orange); 
			this.saltando = false;
			this.agachada = false;
			this.escudo = false;
		}
	}
	
	public void moverIzquierda() {
		this.x -= 10;
	}
	
	public void moverDerecha() {
		this.x += 10;
	}
	
	public void moverArriba() {
		this.y -= 30;
	}
	
	public void moverAbajo() {
		this.y += 10;
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

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}	
	
	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}

	public boolean isAgachada() {
		return agachada;
	}

	public void setAgachada(boolean agachada) {
		this.agachada = agachada;
	}

	public boolean isEscudo() {
		return escudo;
	}

	public void setEscudo(boolean escudo) {
		this.escudo = escudo;
	}

	public void u() {
		y += 100;
	}
	
	public void saltar(Entorno entorno) {
		this.saltando = true;
		entorno.dibujarRectangulo(this.x, this.y - 50, 30, 20, 0, Color.orange);
	}
	
	public void agacharse(Entorno entorno) {
		this.agachada = true;
		entorno.dibujarRectangulo(this.x, this.y + 10, 30, 50, 190, Color.orange);
	}
	
	public void cubrirse(Entorno entorno) {
		this.escudo = true;
		entorno.dibujarRectangulo(this.x, this.y, 15, 50, 0, Color.white);
	}

}

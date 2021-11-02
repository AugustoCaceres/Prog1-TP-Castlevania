package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Velocirraptor {
	
	private int x;
	private int y;
	private int altura;
	private int ancho;
	private String direccion;
	private RayoLaser rayoLaser;
	
	public Velocirraptor() {
		this.x = 700;
		this.y = 117;
		this.altura = 50;
		this.ancho = 10;
		this.direccion = "izquierda";
		this.rayoLaser = new RayoLaser(this.x, this.y);
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

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public RayoLaser getRayoLaser() {
		return rayoLaser;
	}

	public void setRayoLaser(RayoLaser rayoLaser) {
		this.rayoLaser = rayoLaser;
	}

	public int pisa() {
		return (int) this.y + this.altura/2 + 2;
	}
	
	public int lateralDerecho() {
		return (int) this.x + this.getAncho() / 2;
	}
	
	public int lateralIzquierdo() {
		return (int) this.x - this.getAncho() / 2;
	}
	
	public int pisando(Piso[] pisos) {
		for (int i = 0; i < pisos.length; i++) {
			
			if (pisa() == (int) pisos[i].dondeEmpiezaElSuelo()) {
				if (this.lateralDerecho() < pisos[i].extremoIzquierdo() || 
					this.lateralIzquierdo() > pisos[i].extremoDerecho()) {
					return -1;
				} else {
					return i;
				}
			}
		}
		return -1;
	}

	public void dibujarse(Entorno entorno, int segundos, Piso[] pisos) {
		Random r = new Random();
		int rayoX = 1 + r.nextInt(100);

		//si esta pisando algún piso
		if (pisando(pisos) != -1) {
		
			//x del velocirraptor es mayor a diez y el piso es par, significa que va a tener el hueco
			//en la izquierda entonces lo muevo a la izquierda
			if (this.x >= 10 && pisando(pisos) % 2 == 0) {
				this.x = this.x - 2;
				entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.white);
				this.direccion = "izquierda";
				//if (rayoLaser != null) {
					if (rayoX == 95 
							&& !rayoLaser.isActivo() 
							&& rayoLaser.getSentido().equals("izquierda")) {
						this.rayoLaser = new RayoLaser(this.x, this.y);
						this.rayoLaser.setActivo(true);
					}
					if (rayoLaser.isActivo() 
							&& rayoLaser.getX() > 10 
							&& this.direccion.equals("izquierda")
							&& this.x > 50){
						this.rayoLaser.moverIzquierda();
						this.rayoLaser.dibujarse(entorno);
					} 
					if (this.rayoLaser.getX() <= 10) {
						this.rayoLaser.setActivo(false);
						this.rayoLaser.setSentido("derecha");
					}
				//}
			}
			
			//si el piso es impar hay que mover el velocirraptor a la derecha
			else if (this.x <= 800 && pisando(pisos) % 2 == 1) {
				this.x = this.x + 2;
				entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.white);
				this.direccion = "derecha";
				if (rayoLaser != null) {
					if (rayoX == 95 
							&& !rayoLaser.isActivo()
							&& rayoLaser.getSentido().equals("derecha")
							) {
						this.rayoLaser = new RayoLaser(this.x, this.y);
						this.rayoLaser.setActivo(true);
					} 
					if (rayoLaser.isActivo() 
							&& rayoLaser.getX() < 790 
							&& this.direccion.equals("derecha")
							&& this.x < 750){
						this.rayoLaser.moverDerecha();
						this.rayoLaser.dibujarse(entorno);
					} 
					if (this.rayoLaser.getX() >= 790) {
						this.rayoLaser.setActivo(false);
						this.rayoLaser.setSentido("izquierda");
						
					}
				}
			}
		}
		//si no esta pisando un piso tiene que caer por el hueco
		if (pisando(pisos) == -1) {
			this.y += 1;
			entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.white);
			//si queda un espacio para llegar hasta la pared sigue cayendo tipo en diagonal
			if (this.x <= 800 && this.direccion.equals("derecha")) {
				this.x = this.x + 2;
				entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.white);
			}
			else {
				this.direccion = "izquierda";
			}
			
			//esto lo mismo pero para el otro lado
			if (this.x >= 10 && this.direccion.equals("izquierda")) {
				this.x = this.x - 2;
				entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.white);
			} 
			
			else {
				this.direccion = "derecha";
			}
		}
		
	}
	
}
	


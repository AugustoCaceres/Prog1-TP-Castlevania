package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;


public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	private Commodore maquinaDelTiempo;
	private Barbarianna barbarianna;
	private Piso pisos[];
	private Velocirraptor velocirraptors[];
	private Rayo rayo;
	private Image imgGanar, imgPerder, fondo, inicio;
	
	private int vidas, puntos, segundos, velocirraptorsEliminados;
	private boolean jugando, disparo, cambioDireccion, terminar;
	private String direccion;
	
	public Juego() {
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Castlevania, Barbarianna Viking EditionTarea - Grupo 3 - Elverdi - Brunaga - Caceres - V0.01", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		this.maquinaDelTiempo = new Commodore(500, 125);
		this.barbarianna = new Barbarianna(100, 520);
		this.pisos = new Piso[5];
		this.velocirraptors = new Velocirraptor[8];
		this.imgGanar = Herramientas.cargarImagen("ganaste-png.png");
		this.imgPerder = Herramientas.cargarImagen("game-over.jpg");
		this.fondo = Herramientas.cargarImagen("fondo.png");
		this.inicio = Herramientas.cargarImagen("enter.png");
		
		pisos[0] = new Piso(400, 550);
		pisos[1] = new Piso(200, 450);
		pisos[2] = new Piso(600, 350);
		pisos[3] = new Piso(200, 250);
		pisos[4] = new Piso(600, 150);
		
		velocirraptors[0] = new Velocirraptor();
		velocirraptors[1] = null;
		velocirraptors[2] = null;
		velocirraptors[3] = null;
		velocirraptors[4] = null;
		velocirraptors[5] = null;
		velocirraptors[6] = null;
		velocirraptors[7] = null;
		
		this.vidas = 3;
		this.puntos = 0;
		this.segundos = 0;
		this.jugando = false;
		this.velocirraptorsEliminados = 0;
		this.direccion = "derecha";
		this.cambioDireccion = false;
		this.terminar = false;
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el mÃ©todo tick() serÃ¡ ejecutado en cada instante y 
	 * por lo tanto es el mÃ©todo mÃ¡s importante de esta clase. AquÃ­ se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		if (!terminar) {
			entorno.dibujarImagen(inicio, 400, 300, 0, 0.5);
		}
		
		if (entorno.sePresiono(entorno.TECLA_ENTER) && !terminar) {
			entorno.dibujarImagen(inicio, 400, 300, 0, 0.5);
			jugando = true;
		}
		
		if (jugando == true) {
			segundos++;
			
			dibujarPantalla();
			
			dibujarVelocirraptor();
			
			reiniciarVelocirraptor();
			
			dibujarVelocirraptor();
			
			tratamientoBarbarianna();
			
			if (disparo) {
				
				actualizarPosicionTrueno();
			
			}
			
			eliminarVelocirraptors();
			
			jugando = vidas();
			
		} else {
			
			if (vidas == 0) {
				
				entorno.dibujarImagen(imgPerder, 390, 300, 0, 0.5);
			} 
			
			if (terminar && vidas > 0){
					entorno.dibujarImagen(imgGanar, 375, 300, 0, 1);
					
				}
			}
		
	}
	
	//crear los elementos estáticos del juego
	public void dibujarPantalla() {
		
		entorno.dibujarImagen(fondo, 400, 265, 0, 1.8);
		
		entorno.cambiarFont(Font.MONOSPACED, 20, Color.white);
		entorno.escribirTexto("Vidas: "+ this.vidas, 20, 590);
		entorno.escribirTexto("Puntos: "+ this.puntos, 670, 590);
		entorno.escribirTexto("Enemigos eliminados: "+ this.velocirraptorsEliminados, 270, 590);
		
		for(int i = 0; i < pisos.length; i++) {
			pisos[i].dibujarse(entorno);
		}
		
		maquinaDelTiempo.dibujarse(entorno);
		
	}
	
	//va dibujando al array de velocirraptors
	public void dibujarVelocirraptor() {
		
		for (int i = 0; i < velocirraptors.length; i++) {
			
			//si el velocirraptor es distinto de null lo dibuja en el extremo superior derecho de la pantalla
			if (velocirraptors[i] != null) {
				velocirraptors[i].dibujarse(entorno, segundos, pisos);
			}
			
			//si el velocirraptor en la posición i es nulo, o sea que lo mató barbarianna
			else {
				
				//espera a que el contador que está en thick llegue a 150
				if (segundos == 150) {
					//crea en esa posición un velocirraptor y lo dibuja en el extremo superior derecho de la pantalla. También reinicia los segundos
					velocirraptors[i] = new Velocirraptor();
					velocirraptors[i].dibujarse(entorno, segundos, pisos);
					segundos = 0;
				}
				
			}
			
		}
	}
	
	//metodo para reiniciar a los velocirraptors que salen por la esquina inferior izquierda de la pantalla
	public void reiniciarVelocirraptor() {
		
		for (int j = 0; j < velocirraptors.length; j++) {
			
			if (velocirraptors[j] != null) {
				
				if (velocirraptors[j].getX() < 10 && velocirraptors[j].getY() >= 500) {
					velocirraptors[j] = null;
				}
			
			}
		}
	}
	
	void tratamientoBarbarianna() {
		this.barbarianna.dibujarse(entorno);
		
				if((entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) 
						&& this.barbarianna.getX() > 0 + barbarianna.getAncho()/2) {
					this.barbarianna.moverIzquierda();
					if (this.direccion == "izquierda") {
						this.cambioDireccion = false;
					}else {
						this.cambioDireccion = true;
					}
					this.direccion = "izquierda";
				} 
				
				if((entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) 
						&& this.barbarianna.getX() < entorno.ancho() - barbarianna.getAncho()/2) {
					this.barbarianna.moverDerecha();
					if (this.direccion == "derecha") {
						this.cambioDireccion = false;
					}else {
						this.cambioDireccion = true;
					}
					this.direccion = "derecha";
				} 
				
				if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					//si se presiona la barra espaciadora se crea un rayo 
					//(que en realidad es un trueno) y se pone un booleano en true
					disparo = true;
					this.rayo = new Rayo(this.barbarianna.getX(), this.barbarianna.getY());
					this.rayo.setActivo(true);	
				} 			
					
				//primer salto del cero al uno
				if(entorno.estaPresionada('u') 
						&& barbarianna.getX() > 620 
						&& barbarianna.getY() < 535 
						&& barbarianna.getY() > 415) {
					this.barbarianna = new Barbarianna(585, 420);
				}
				
				//cae del uno a cero
				if(barbarianna.getX() > 600 
						&& barbarianna.getY() <= 420 
						&& barbarianna.getY() >= 380) {
					this.barbarianna.u();
					}
				
				//segundo salto del uno al dos 
				if(entorno.estaPresionada('u') 
						&& barbarianna.getX() < 210 
						&& barbarianna.getY() < 430 
						&& barbarianna.getY() > 325) {
					this.barbarianna = new Barbarianna(215, 320);
				}
				
				//cae del dos al uno 
				if(barbarianna.getX() < 190 
						&& barbarianna.getY() <= 320 
						&& barbarianna.getY() >= 230) {
					this.barbarianna.u();
				}
				
				//tercer salto del dos al tres
				if(entorno.estaPresionada('u') 
						&& barbarianna.getX() > 620 
						&& barbarianna.getY() <= 320 
						&& barbarianna.getY() > 200) {
					this.barbarianna = new Barbarianna(585,220);
				}
				
				//cae del tres al dos 
				if(barbarianna.getX() > 620 
						&& barbarianna.getY() <= 220 
						&& barbarianna.getY() >= 125) {
					this.barbarianna.u();
				}
				
				//cuarto salto del tres al cuatro
				if(entorno.estaPresionada('u')
						&& barbarianna.getX() < 190 
						&& barbarianna.getY() <= 220) {	 
					this.barbarianna = new Barbarianna(215,120);
				}
				
				//cae del cuatro al tres
				if(barbarianna.getX() < 190 
						&& barbarianna.getY() <= 120) {
					this.barbarianna.u();
				}
				
			}
	
	public boolean choqueBarbariannaVelocirraptor() {
		for (int i = 0; i < velocirraptors.length; i++) {
			
			if (velocirraptors[i] != null) {
				
				if (seTocan(barbarianna, velocirraptors[i]) /*&& !this.barbarianna.isSaltando()*/) {
					velocirraptors[i] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	
	//para chequear si colisionan barbarianna y algún velocirraptor
	public boolean seTocan(Barbarianna b, Velocirraptor v) {
		return distancia(b, v) <= 0;
	}
	
	public double distancia(Barbarianna b, Velocirraptor v) {
		return distanciaCentro(b, v) - b.getAncho()/2 - v.getAncho()/2  <= 0 ?
				0 : distanciaCentro(b, v) - b.getAncho()/2 - v.getAncho()/2;
	}
	
	public double distanciaCentro(Barbarianna b, Velocirraptor v) {
		return Math.sqrt(Math.pow((b.getX() - v.getX()),2) + Math.pow((b.getY() - v.getY()),2)); 
	}
	
	
	//para determinar el final del juego, que puede ser porque ya no quedan vidas o
	//porque barbarianna haya alcanzado la computadora
	public boolean vidas() {
		
		//parte que verifica que aún quedan vidas
		if (choqueBarbariannaVelocirraptor() || choqueRayoBarbarianna()) {
			System.out.println("Perdiste una vida!");
		
			vidas--;
			
			this.barbarianna = new Barbarianna(100, 520);
		
			if (vidas == 0) {
				terminar = true;
				return false;
			
			}
		
		}
		
		//parte que verifica que barbarianna haya alcanzado la computadora
		if (barbariannaAlcanzaObjetivo()) {
			terminar = true;
			System.out.println("Ganaste!");
			return false;
		}
		
		return true;
	}
	
	
	//para chequear que colisionan barbarianna y la computadora
	public boolean barbariannaAlcanzaObjetivo() {
		if (seTocan(barbarianna, maquinaDelTiempo)) {
			return true;
		}
		return false;
	}
	
	public boolean seTocan(Barbarianna b, Commodore mdt) {
		return distancia(b, mdt) <= 0;
	}
	
	public double distancia(Barbarianna b, Commodore mdt) {
		return distanciaCentro(b, mdt) - b.getAncho()/2 - mdt.getAncho()/2  <= 0 ?
				0 : distanciaCentro(b, mdt) - b.getAncho()/2 - mdt.getAncho()/2;
	}
	
	public double distanciaCentro(Barbarianna b, Commodore mdt) {
		return Math.sqrt(Math.pow((b.getX() - mdt.getX()),2) + Math.pow((b.getY() - mdt.getY()),2)); 
	}
	
	//método que actualiza la posición del trueno thick a thick
	public void actualizarPosicionTrueno() {
			
		if (this.rayo != null) {
			
			//aca entra solo si el rayo no es nulo, o sea si existe un rayo
			//si el x del rayo es mayor a cinco y barbarianna viene desde la izquierda,
			//el rayo se va a mover para la izquierda
			if (this.rayo.getX() >= 5
					&& direccion.equals("izquierda")
					&& this.rayo.isActivo()
					&& !this.cambioDireccion) {
				
				this.rayo.moverIzquierda();
				this.rayo.dibujarse(entorno, direccion);
			}  
			//si el x del rayo es menor que 795 y barbarianna viene desde la derecha,
			//el rayo se va a mover para la derecha
			else if (this.rayo.getX() <= 795
					&& direccion.equals("derecha")
					&& this.rayo.isActivo()
					&& !this.cambioDireccion) {
				
				this.rayo.moverDerecha();
				this.rayo.dibujarse(entorno, direccion);
			}
			//si el x de rayo no entra en los otros if el rayo debería eliminarse 
			//pero no pasa eso
			else { 
				this.rayo.setActivo(false);
				this.rayo = null; 
			}
			
		}

	}
	
	//método que elimina al velocirraptor que haya colisionado con el trueno de barbarianna
	public void eliminarVelocirraptors() {
		for (int i = 0; i < velocirraptors.length; i++) {
			if (velocirraptors[i] != null && rayo != null) {
				
				if (seTocan(rayo, velocirraptors[i])) {
					
					rayo = null;
					velocirraptors[i] = null;
					this.velocirraptorsEliminados++;
					this.puntos += 10;
					
				}
			}
		}
	}
	
	//para chequear que colisionan el trueno de barbarianna con algún velocirraptor
	public boolean seTocan(Rayo r, Velocirraptor v) {
		return distancia(r, v) <= 0;
	}
	
	public double distancia(Rayo r, Velocirraptor v) {
		return distanciaCentro(r, v) - r.getAncho()/2 - v.getAncho()/2  <= 0 ?
				0 : distanciaCentro(r, v) - r.getAncho()/2 - v.getAncho()/2;
	}
	
	public double distanciaCentro(Rayo r, Velocirraptor v) {
		return Math.sqrt(Math.pow((r.getX() - v.getX()),2) + Math.pow((r.getY() - v.getY()),2)); 
	}
	
	public boolean choqueRayoBarbarianna() {
		for (int i = 0; i < velocirraptors.length; i++) {
			if (velocirraptors[i] != null) {
				if (seTocan(barbarianna, velocirraptors[i].getRayoLaser()) 
						&& !this.barbarianna.isSaltando() 
						&& !this.barbarianna.isAgachada()
						&& !this.barbarianna.isEscudo()) {
					return true;
				}	
			}
		}
		return false;
	}
	
	public boolean seTocan(Barbarianna b, RayoLaser r) {
		return distancia(b, r) <= 0;
	}
	
	public double distancia(Barbarianna b, RayoLaser r) {
		return distanciaCentro(b, r) - b.getAncho()/2 - r.getAncho()/2  <= 0 ?
				0 : distanciaCentro(b, r) - b.getAncho()/2 - r.getAncho()/2;
	}
	
	public double distanciaCentro(Barbarianna b, RayoLaser r) {
		return Math.sqrt(Math.pow((b.getX() - r.getX()),2) + Math.pow((b.getY() - r.getY()),2)); 
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}

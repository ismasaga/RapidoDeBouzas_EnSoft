package comunicacion_sistema_central;

import principal.*;
import controlador.InterfazControlador;

public class HiloEscribirAleatorio extends Thread {
	private InterfazComunicacionSistemaCentral sc;
	private InterfazControlador ic;
	
	public HiloEscribirAleatorio(InterfazComunicacionSistemaCentral sc, InterfazControlador ic) {
		this.sc = sc;
		this.ic = ic;
	}
	
	public void run() {
		while(true) {
			Camion c = ic.recibirCamion();
			sc.escribirTiempoAleatorio(c);
			try {
				sleep(10000); // 10 segundos se escribe la posicion del camion en el fichero
			} catch(InterruptedException e) {
				System.out.println("Excepcion en HiloEscribirAleatorio.run(): " + e);
			}
		}
	}
}

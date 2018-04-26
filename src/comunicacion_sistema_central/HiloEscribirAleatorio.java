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
		boolean flag = true;
		while(flag) {
			Camion c = ic.recibirCamion();
			if (c != null) {
				System.err.println("Fio");
				sc.escribirTiempoAleatorio(c);
			}
			try {
				sleep(15000); // 15 segundos se escribe la posicion del camion en el fichero
			} catch(InterruptedException e) {
				System.out.println("Excepcion en HiloEscribirAleatorio.run(): " + e);
			}
			if (c.getDestinos().isEmpty())
				flag = false;
		}
	}
}

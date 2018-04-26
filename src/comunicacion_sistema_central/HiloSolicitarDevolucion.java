package comunicacion_sistema_central;

import controlador.InterfazControlador;
import principal.*;

public class HiloSolicitarDevolucion extends Thread {

	private InterfazControlador ic;
	
	public HiloSolicitarDevolucion(InterfazControlador ic) {
		this.ic = ic;
	}
	
	public void run() {
		Paquete devol = ic.solicitarDevolucionAleatoria();
		try {
			sleep(10000); // 10 segundos se escribe la posicion del camion en el fichero
		} catch(InterruptedException e) {
			System.out.println("Excepcion en HiloEscribirAleatorio.run(): " + e);
		}
		System.out.println("Nova devolución: [" + devol.getId() + ";" + devol.getEstado() + ";" + devol.getCliente().getDni() + ";(x = " + devol.getD().getX() + ", y = " + devol.getD().getY() + ")");
	}
	
}

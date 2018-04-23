package comunicacion_sistema_central;

import java.util.ArrayList;
import principal.*;

public interface InterfazComunicacionSistemaCentral {
	public boolean escribirTiempoAleatorio(Camion c);
	public boolean escribirConfirmacionEntrega(Paquete p);
	public ArrayList<Paquete> solicitarPaquetesAEntregar();
	public Paquete solicitarDevolucion();
	public boolean notificarDetencion(Camion c);
	public ArrayList<String> listarParadas();
	public void confirmarPaquete(Paquete p);
}

package repartidor;

import java.util.ArrayList;
import principal.*;

public interface InterfazRepartidor {
	public void anadirPaquete(String id, Destino d, Cliente c, String estado);
	public void borrarPaquete(String id);
	public Paquete buscarPaquete(String id);
	public void modificarPaquete(String id, Destino d, Cliente c, EstadoPaquete estado, String observacions);
	public void aceptarPaquete(String id, boolean firma);
	public void rechazarPaquete(String id, String motivoRechazo);
	public void anadirReceptorAlternativo(String id, String nombre, String dni, boolean firma);
	public ArrayList<Paquete> ejecutarLecturaEntregasDia();
	public void escribirConfirmacionEntrega(Paquete p);
}

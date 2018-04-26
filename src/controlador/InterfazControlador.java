package controlador;

import java.util.ArrayList;
import principal.*;

public interface InterfazControlador {
	
	public Paquete solicitarPaquete(String id);

	public void anadirPaquete(Paquete p);

	public void borrarPaquete(String id);

	public Paquete buscarPaquete(String id);

	public void modificarPaquete(Paquete p);

	public void notificarDetencion(Camion c);

	public void enviarCamion(Camion c);

	public Camion recibirCamion();

	public Paquete confirmarPaquete();

	public ArrayList<Paquete> solicitarListaPaquetes();

	public Tiempo solicitarTiempoEstimadoEntregaPaquete(Paquete p);
}
package controlador;

import java.util.ArrayList;

import principal.*;

public class Controlador implements InterfazControlador {
	private ArrayList<Paquete> listaEntrega;
	private ArrayList<Paquete> listaDevolucion;
	
	public Controlador() {
	}

	@Override
	public void notificarDetencion(Camion c) {
		System.out.println("Camion en controlador: ");
		System.out.println("Posicion camion: (" + c.getLocalizacion().getX() + ", " + c.getLocalizacion().getY() + ")");
	}

	@Override
	public Paquete solicitarPaquete(String id) {
		return null;
	}

	@Override
	public void anadirPaquete(Paquete p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarPaquete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Paquete buscarPaquete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarPaquete(Paquete p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarCamion(Camion c) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Paquete> solicitarListaPaquetes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tiempo solicitarTiempoEstimadoEntregaPaquete(Paquete p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notificarDestino(Destino d) {
		// TODO Auto-generated method stub

	}
}
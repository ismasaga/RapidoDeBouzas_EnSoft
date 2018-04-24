package controlador;

import java.util.ArrayList;

import principal.*;

public class Controlador implements InterfazControlador {

	private ArrayList<Paquete> listaEntrega;
	private ArrayList<Paquete> listaDevolucion;

	public ArrayList<Paquete> getListaDevolucion() {
		return listaDevolucion;
	}

	public void setListaDevolucion(ArrayList<Paquete> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}

	public void setListaEntrega(ArrayList<Paquete> listaEntrega) {
		this.listaEntrega = listaEntrega;
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
	public Camion recibirCamion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paquete confirmarPaquete() {
		// TODO Auto-generated method stub
		return null;
	}

	public Paquete solicitarDevolucionAleatoria() {
		return null;
	}

	public ArrayList<Paquete> getListaEntrega() {
		return null;
	}

	public ArrayList<Paquete> getListaDevoluciones() {
		return null;
	}

	public void modificarEstadoCamion(EstadoCamion estado) {

	}

	public EstadoCamion getEstadoActualCamion() {
		return null;
	}

}
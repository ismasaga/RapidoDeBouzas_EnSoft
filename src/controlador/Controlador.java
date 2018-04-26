package controlador;

import java.util.ArrayList;

import comunicacion_sistema_central.HiloEscribirAleatorio;
import comunicacion_sistema_central.InterfazComunicacionSistemaCentral;
import comunicacion_sistema_central.SistemaCentral;
import localizacion_gestion_destino.InterfazLocalizacionGestionDestino;
import localizacion_gestion_destino.LocalizacionControl;
import principal.*;
import repartidor.InterfazRepartidor;

public class Controlador implements InterfazControlador {

	private ArrayList<Paquete> listaEntrega;
	private ArrayList<Paquete> listaDevolucion;
	private InterfazComunicacionSistemaCentral sc;
	private InterfazLocalizacionGestionDestino il;
	private InterfazRepartidor rep;

	public Controlador(InterfazRepartidor rep) {
		this.rep = rep;
		sc = new SistemaCentral();
		listaDevolucion = new ArrayList<>();
	}

	/**
	 * Se quedan hay que metelos no diagrama de clases
	 * @return
	 */
	public ArrayList<Paquete> getListaDevolucion() {
		return listaDevolucion;
	}

	public void setListaDevolucion(ArrayList<Paquete> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}

	public void setListaEntrega(ArrayList<Paquete> listaEntrega) {
		this.listaEntrega = listaEntrega;
	}



	//---------------------------------------------------------------------------



	/**
	 * Metodo que redirixe a notificacion de parada que proven do
	 * subsistema de localizacion e xestion
	 */
	@Override
	public void notificarDetencion(Camion c) {
		Destino d = c.getLocalizacion();
		boolean flag = true;
		
		for (Paquete p : listaEntrega) {
			if (p.getD().getX() == d.getX() && p.getD().getY() == d.getY()) {
				System.out.println("Entregouse un paquete");
				rep.escribirConfirmacionEntrega(p);
				sc.escribirConfirmacionEntrega(p);
				return;
			}
		}
		for (Paquete p : listaDevolucion) {
			if (p.getD().getX() == d.getX() && p.getD().getY() == d.getY()) {
				System.out.println("Recolleuse unha devolución");
				rep.escribirConfirmacionEntrega(p);
				sc.escribirConfirmacionEntrega(p);
				return;
			}
		}
		if (flag)
			System.out.println("Non se atopou un paquete co mesmo destino que nos pasou InterfazLocalizacion");
	}

	/**
	 * Devolve o paquete que concorde coa id se recibe por parametro
	 * Devolve null en caso de que non exista
	 */
	@Override
	public Paquete solicitarPaquete(String id) {
		for (Paquete paquete : listaDevolucion) {
			if (paquete.getId().equals(id)) {
				return paquete;
			}
		}
		for (Paquete paquete : listaEntrega) {
			if (paquete.getId().equals(id)) {
				return paquete;
			}
		}
		return null;
	}

	/**
	 * Engade un paquete na lista de paquetes
	 * Xestiona internamente se e para devolucion ou para repartir
	 */
	@Override
	public void anadirPaquete(Paquete p) {
		if (p.getEstado() == EstadoPaquete.DEVOLUCION) {
			listaDevolucion.add(p);
		} else if (p.getEstado() == EstadoPaquete.EN_REPARTO) {
			listaEntrega.add(p);
		}
	}

	/**
	 * Borra un paquete de unha das listas
	 * Se o borra de ambas sae erro pola consola
	 */
	@Override
	public void borrarPaquete(String id) {
		int flag = 0;

		for (Paquete p : listaDevolucion) {
			if (p.getId().equals(id)) {
				listaDevolucion.remove(p);
				System.out.println("Paquete eliminado da lista de paquetes a devolver");
				flag++;
			}
		}
		for (Paquete p : listaEntrega) {
			if (p.getId().equals(id)) {
				listaEntrega.remove(p);
				System.out.println("Paquete eliminado da lista de paquetes a entregar");
				flag++;
			}
		}
		if (flag > 1) {
			System.err.println("ERRO!!! O paquete foi eliminado das dúas listas, comportamento anómalo");
		}
	}

	/**
	 * ********METODO DUPLICADO CON solicitarPaquete(String id)*******
	 * Devolve o paquete que concorde coa id se recibe por parametro
	 * Devolve null en caso de que non exista
	 */
	@Override
	public Paquete buscarPaquete(String id) {
		for (Paquete paquete : listaDevolucion) {
			if (paquete.getId().equals(id)) {
				return paquete;
			}
		}
		return null;
	}

	@Override
	public void modificarPaquete(Paquete p) {
		// Busco polo id e igualo todo menos o id (non se pode modificar)
	}

	/**
	 * Redirixe o camion que ven do sistema de localizacion para o sistema central
	 * que e quen o precisa para escribir
	 */
	@Override
	public void enviarCamion(Camion c) {
		sc.notificarDetencion(c);
	}

	/**
	 * Devolve a lista de paquetes a entregar e instancia o sistema
	 * de localizacion e control pasandolle a lista de destinos
	 * -----------------SO SE PODE CHAMAR UNHA VEZ-----------------
	 */
	@Override
	public ArrayList<Paquete> solicitarListaPaquetes() {
		ArrayList<Destino> listaDestinos = new ArrayList<>();
		listaEntrega = sc.solicitarPaquetesAEntregar();
		for (Paquete p : listaEntrega) {
			listaDestinos.add(new Destino(p.getD()));
		}
		il = new LocalizacionControl(listaDestinos, this);
		HiloEscribirAleatorio fio = new HiloEscribirAleatorio(sc, this);
		fio.start();
		return listaEntrega;
	}

	@Override
	public Tiempo solicitarTiempoEstimadoEntregaPaquete(Paquete p) {
		// Cando faga o singleton vou chamar a localizacion e control (buscarFecha(p.getDestino))
		return null;
	}

	@Override
	public Camion recibirCamion() {
		return il.recibirCamion();
	}

	@Override
	public Paquete confirmarPaquete() {
		// VER SE FAI FALLA
		return null;
	}

	/**
	 * Devolve un paquete a devolver
	 * Realmente sempre devolve o primeiro asi que se non se elimina sempre devolvera o mesmo
	 * @return
	 */
	public Paquete solicitarDevolucionAleatoria() {
		Paquete p = sc.solicitarDevolucion();
		listaDevolucion.add(p);
		return p;
	}

	public ArrayList<Paquete> getListaEntrega() {
		// VER SE FAI FALLA
		return null;
	}

	public ArrayList<Paquete> getListaDevoluciones() {
		// VER SE FAI FALLA
		return null;
	}

	public void modificarEstadoCamion(EstadoCamion estado) {
		// VER SE FAI FALLA
	}

	public EstadoCamion getEstadoActualCamion() {
		// VER SE FAI FALLA
		return null;
	}

}
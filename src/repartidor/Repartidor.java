package repartidor;

import java.util.ArrayList;

import controlador.*;
import principal.*;

public class Repartidor implements InterfazRepartidor {
	private InterfazControlador iC;
	
	public Repartidor() {
		this.iC = new Controlador();
	}

	@Override
	public void anadirPaquete(String id, Destino d, Cliente c, String estado) {
		Paquete p=new Paquete(id, d, c, "Ninguna", null, null, false , EstadoPaquete.valueOf(estado));
		this.iC.anadirPaquete(p);
	}
	
	@Override
	public void borrarPaquete(String id) {
		this.iC.borrarPaquete(id);
	}
	
	@Override
	public Paquete buscarPaquete(String id) {
		return this.iC.buscarPaquete(id);
	}
	
	@Override
	public void modificarPaquete(String id, Destino d, Cliente c, EstadoPaquete estado, String observacions) {
		Paquete p=new Paquete(id, d, c, observacions, null, null, false, estado);
		this.iC.anadirPaquete(p);
	}
	
	@Override
	public void aceptarPaquete(String id, boolean firma) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.getCliente().setFirma(firma);
		//this.iC.modificarPaquete(p);
	}
	
	@Override
	public void rechazarPaquete(String id, String motivoRechazo) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.setObservaciones(motivoRechazo);
		
		//this.iC.modificarPaquete(p); 
	}
	
	@Override
	public void anadirReceptorAlternativo(String id, String nombre, String dni, boolean firma) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.getCliente().setNombre(nombre);
		p.getCliente().setDni(dni);
		p.getCliente().setFirma(firma);
		//this.iC.modificarPaquete(p); 
	}
	
	@Override
	public ArrayList<Paquete> ejecutarLecturaEntregasDia() {
		return this.iC.solicitarListaPaquetes();
	}
	
	/*public void escribirConfirmacionEntrega(Paquete p) {
		//Non sirve para nada
	}*/
	
}

package repartidor;

public class Repartidor implements InterfazRepartidor {
	private InterfazControlador iC;
	
	public Repartidor() {
		this.iC = new Controlador();
	}

	public void anadirPaquete(String id, Destino d, Cliente c, String estado) {
		Paquete p=new Paquete(id, d, c, "Ninguna", null, null, false , EstadoPaquete.valueOf(estado));
		this.iC.anadirPaquete(p);
	}
	
	public void borrarPaquete(String id) {
		this.iC.borrarPaquete(id);
	}
	
	public Paquete buscarPaquete(String id) {
		return this.iC.buscarPaquete(id);
	}
	
	public void modificarPaquete(String id, Destino d, Cliente c, EstadoPaquete estado, String observacions) {
		Paquete p=new Paquete(id, d, c, observaciones, null, null, false, EstadoPaquete.valueOf(estado));
		this.iC.anadirPaquete(p);
	}
	
	public void aceptarPaquete(String id, boolean firma) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.setFirma(firma);
		//this.iC.modificarPaquete(p);
	}
	
	public void rechazarPaquete(String id, String motivoRechazo) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.setObservaciones(motivoRechazo);
		
		
		
		//this.iC.modificarPaquete(p); 
	}
	
	public void anadirReceptorAlternativo(String id, String nombre, String dni, boolean firma) {
		Paquete p=this.iC.solicitarPaquete(id);
		p.setNombre(nombre);
		p.setDni(dni);
		p.setFirma(firma);
		//this.iC.modificarPaquete(p); 
	}
	
	public ArrayList<Paquete> ejecutarLecturaEntregasDia() {
		return this.iC.solicitarListaPaquetes();
	}
	
	/*public void escribirConfirmacionEntrega(Paquete p) {
		//Non sirve para nada
	}*/
	
}

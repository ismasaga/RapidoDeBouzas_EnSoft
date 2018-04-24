package principal;

public class Paquete {
	private String id;
	private Destino d;
	private Cliente c;
	private String observaciones;
	private String dniAlternativo;
	private String nombreAlternativo;
	private boolean firmaAlternativa;
	private EstadoPaquete estado;
	
	public Paquete(String id, Destino d, Cliente c, String observaciones, String dniAlternativo, String nombreAlternativo,
			boolean firmaAlternativa, EstadoPaquete estado) {
		this.id = id;
		this.d = d;
		this.c=c;
		this.observaciones = observaciones;
		this.dniAlternativo = dniAlternativo;
		this.nombreAlternativo = nombreAlternativo;
		this.firmaAlternativa = firmaAlternativa;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Destino getD() {
		return d;
	}

	public void setD(Destino d) {
		this.d = d;
	}
	
	public Cliente getCliente() {
		return c;
	}
	
	public void setCliente(Cliente c) {
		this.c = c;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDniAlternativo() {
		return dniAlternativo;
	}

	public void setDniAlternativo(String dniAlternativo) {
		this.dniAlternativo = dniAlternativo;
	}

	public String getNombreAlternativo() {
		return nombreAlternativo;
	}

	public void setNombreAlternativo(String nombreAlternativo) {
		this.nombreAlternativo = nombreAlternativo;
	}

	public boolean isFirmaAlternativa() {
		return firmaAlternativa;
	}

	public void setFirmaAlternativa(boolean firmaAlternativa) {
		this.firmaAlternativa = firmaAlternativa;
	}

	public EstadoPaquete getEstado() {
		return estado;
	}

	public void setEstado(EstadoPaquete estado) {
		this.estado = estado;
	}
}

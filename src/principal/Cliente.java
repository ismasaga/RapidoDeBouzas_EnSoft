package principal;

public class Cliente {
	private String dni;
	private String nombre;
	private Destino direccion;
	private String telefono;
	private boolean firma;
	public Cliente(String dni, String nombre, Destino direccion, String telefono, boolean firma) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.firma = firma;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Destino getDireccion() {
		return direccion;
	}
	public void setDireccion(Destino direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isFirma() {
		return firma;
	}
	public void setFirma(boolean firma) {
		this.firma = firma;
	}
}

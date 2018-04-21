package localizacion_gestion_destino;

import java.util.ArrayList;
import main.*;

public interface InterfazLocalizacionGestionDestino {

	public Camion recibirCamion();

	public void anadirDestino(Destino d);

	public void imprimirDestinos();

	public void imprimirDestino(Destino d);

	public void imprimirTiempo(Tiempo t);

	public Tiempo buscarFecha(Destino d);

	public Destino siguienteDestino();

	public ArrayList<Destino> listarDestinos();

	public void moverCamion(); // para poder probalo, despois quitalo

	// public Tiempo calcularTiempoDetenido();
	//
	// public Tiempo calcularTiempoMovimiento();
	//
	// public Integer calcularNumeroParadas();
	//
	// public Float calcularNumeroKilometros();
	//
	// public Float calcularMediaVelocidad();
	//
	// public Float calcularMediaParadas();
	//
	// public Tiempo calcularMediaTiempoParada();
	//
	// public Tiempo calcularMediaTiempoEntrega();
	//
	// public Tiempo calcularMediaTiempoRecogida();
	//
	// public ArrayList<Tiempo> getTiemposDesplazamiento();
	//
	// public ArrayList<Tiempo> getTiemposDesplazamientosAcum();
	//
	// public Float getPorcentajeTiempoParadas();
	//
	// public Float getPorcentajeTiempoMovimiento();
	//
	// public Float getPorcentajeTiempoEntregas();
}

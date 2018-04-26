package localizacion_gestion_destino;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import principal.*;

public interface InterfazLocalizacionGestionDestino {

	public Camion recibirCamion();

	public void anadirDestino(Destino d);

	public void imprimirDestinos();

	public void imprimirDestino(Destino d);

	public void imprimirTiempo(Tiempo t);

	public Tiempo buscarFecha(Destino d);

	public Destino siguienteDestino();

	public ArrayList<Destino> listarDestinos();

	public Tiempo calcularTiempoDetenido();

	public Tiempo calcularTiempoMovimiento();

	public Integer calcularNumeroParadas();

	public Float calcularNumeroKilometros();

	public Float calcularMediaVelocidad(); // no se modifica nunca

	public Float calcularMediaParadas();

	public Tiempo calcularMediaTiempoParada();

	public Tiempo calcularMediaTiempoEntrega();

	// public Tiempo calcularMediaTiempoRecogida(); // pienso que es mejor usar el metodo calcularMediaTiempoEntrega para ambos

	public LinkedHashMap<Destino, Tiempo> getTiemposDesplazamiento(); // se modifico la interfaz, ademas, devuelve los acumulados tambien (no implementada)

	public LinkedHashMap<Destino, Tiempo> getTiemposDesplazamientosAcum(); // se modifico la interfaz

	public Float getPorcentajeTiempoParadas();

	public Float getPorcentajeTiempoMovimiento();

	public Float getPorcentajeTiempoEntregas();

//	public HiloMover getHm(); // para conseguir el hilo de mover
}

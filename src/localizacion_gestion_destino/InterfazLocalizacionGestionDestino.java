package localizacion_gestion_destino;

import java.util.ArrayList;

import main.Destino;
import main.Fecha;
import main.Tiempo;

public interface InterfazLocalizacionGestionDestino {
	public void anadirDestino(Destino d);

	public Fecha buscarFecha(Destino d);

	public Tiempo calcularTiempoDetenido();

	public Tiempo calcularTiempoMovimiento();

	public Integer calcularNumeroParadas();

	public Float calcularNumeroKilometros();

	public Float calcularMediaVelocidad();

	public Float calcularMediaParadas();

	public Tiempo calcularMediaTiempoParada();

	public Tiempo calcularMediaTiempoEntrega();

	public Tiempo calcularMediaTiempoRecogida();

	public ArrayList<Tiempo> getTiemposDesplazamiento();

	public ArrayList<Tiempo> getTiemposDesplazamientosAcum();

	public Float getPorcentajeTiempoParadas();

	public Float getPorcentajeTiempoMovimiento();

	public Float getPorcentajeTiempoEntregas();
}

package localizacion_gestion_destino;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import principal.*;

public class Principal {
	public static void main(String[] args) {

		ArrayList<Destino> destinos = new ArrayList<>();
		Destino d1 = new Destino(2f, 4f);
		Destino d2 = new Destino(6f, 8f);
		Destino d3 = new Destino(5f, 4f);
		Destino d4 = new Destino(1f, 1f);
		destinos.add(d1);
		destinos.add(d2);
		destinos.add(d3);
		destinos.add(d4);

		InterfazLocalizacionGestionDestino il = new LocalizacionControl(destinos);

		// buscar tiempo
		// Tiempo t = il.buscarFecha(d1);
		// il.imprimirTiempo(t);

		try {
			il.getHm().join();
		} catch (InterruptedException e) {
			System.out.println("Error al esperar por el hilo!: " + e);
		}

		il.imprimirDestinos();

		// xa esta todo probado
		// System.out.print("Tiempo movimiento: ");
		// il.imprimirTiempo(il.calcularTiempoMovimiento());
		//
		// System.out.println("Km realizados: " + il.calcularNumeroKilometros() + "
		// km");
		//
		// System.out.println("Media paradas: " + il.calcularMediaParadas());
		//
		// System.out.print("Media tiempo parada: ");
		// il.imprimirTiempo(il.calcularMediaTiempoParada());
		//
		// System.out.print("Media tiempo entrega: ");
		// il.imprimirTiempo(il.calcularMediaTiempoEntrega());

		// System.out.println("Porcentaje tiempo parado: " +
		// il.getPorcentajeTiempoParadas());
		// System.out.println("Porcentaje tiempo movimiento: " +
		// il.getPorcentajeTiempoMovimiento());
		// System.out.println("Porcentaje tiempo entregas: " +
		// il.getPorcentajeTiempoEntregas());

		// para recoller os tempos acumulados
		LinkedHashMap<Destino, Tiempo> tiemposAcum = il.getTiemposDesplazamientosAcum();
		System.out.println("Tiempos acumulados: ");
		for (Destino d : tiemposAcum.keySet()) {
			il.imprimirDestino(d);
			il.imprimirTiempo(tiemposAcum.get(d));
		}

		System.out.println("Finished!");
		System.exit(0);
	}
}

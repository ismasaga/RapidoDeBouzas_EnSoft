package localizacion_gestion_destino;

import main.*;

public class Principal {
	public static void main(String[] args) {
		InterfazLocalizacionGestionDestino il = new LocalizacionControl();
		
		// anadir destinos
		 Destino d1 = new Destino(2f, 4f);
		 Destino d2 = new Destino(6f, 8f);
		// Destino d3 = new Destino(5f, 4f);
		// Destino d4 = new Destino(1f, 1f);

		il.anadirDestino(d1);
		il.anadirDestino(d2);
		// il.anadirDestino(d3);
		// il.anadirDestino(d4);
		// il.imprimirDestinos();

		// imprimir destino
		// il.imprimirDestino(d1);

		// buscar tiempo
		// Tiempo t = il.buscarFecha(d1);
		// il.imprimirTiempo(t);
		
		
		try {
			il.getHm().join();
		} catch (InterruptedException e) {
			System.out.println("Error al esperar por el hilo!: " + e);
		}
		
		System.out.print("Tiempo movimiento: ");
		il.imprimirTiempo(il.calcularTiempoMovimiento());
		
		System.out.println("Km realizados: " + il.calcularNumeroKilometros() + " km");
		
		System.out.println("Media paradas: " + il.calcularMediaParadas());
		
		System.out.print("Media tiempo parada: ");
		il.imprimirTiempo(il.calcularMediaTiempoParada());
		
		System.out.println("Porcentaje tiempo parado: " + il.getPorcentajeTiempoParadas());
		System.out.println("Porcentaje tiempo movimiento: " + il.getPorcentajeTiempoMovimiento());
		
		System.out.println("Finished!");
		System.exit(0);

	}
}

package localizacion_gestion_destino;

import java.util.LinkedHashMap;

import main.*;

public class HiloMover extends Thread {

	private LocalizacionControl lc;
	private Camion camion;
	private LinkedHashMap<Destino, Tiempo> destinosEntregados;
	private static boolean ejecutar = true;

	public HiloMover(LocalizacionControl lc, Camion camion) {
		this.lc = lc;
		this.camion = camion;
		this.destinosEntregados = new LinkedHashMap<>();
	}

	@Override
	public void run() {
		int i = 0;
		while (ejecutar) {
			moverCamion(i); // i es para calcular el tiempo total en movimiento, si es 0 se calcula ya el tiempo total
			i++;
//			try {
//				Thread.sleep(5000); // 10 segundos
//			} catch (InterruptedException e) {
//				System.out.println("Excepcion on moverCamion()");
//			}
		}
	}

	public void moverCamion(int i) {
		Destino posActual = this.camion.getLocalizacion();
		Destino proxDestino = lc.siguienteDestino();
		if (proxDestino != null) {
			System.out.println("En camino al próximo destino:\n");
			while (posActual.getX() <= proxDestino.getX() && posActual.getY() <= proxDestino.getY()) {
				// recalcular al tener el camion una nueva posicion
				actualizarTiempos(i); // si es 0, se calcula todo lo que andara el camion, para asi guardarlo en tiempoMovimiento
				lc.imprimirDestinos();
				posActual.setX(posActual.getX() + proxDestino.getX() / 5.5f);
				posActual.setY(posActual.getY() + proxDestino.getY() / 5.5f);
				this.camion.setLocalizacion(new Destino(posActual.getX(), posActual.getY()));

				lc.simularParada(proxDestino);
				i++;
			}

			this.camion.setLocalizacion(lc.siguienteDestino());
			System.out.println("Nueva posición del camion: ");
			lc.imprimirDestino(this.camion.getLocalizacion());

			// introduzco en el array de entregas y lo quito del camion
			this.destinosEntregados.put(this.camion.getLocalizacion(),
					this.camion.getDestinos().get(this.camion.getLocalizacion()));

			this.camion.getDestinos().remove(this.camion.getLocalizacion());

			// http://www.shodor.org/interactivate/activities/SimplePlot/
			// (0, 0) (0.36, 0.72) (0.72, 1.45) (1.09, 2.18)(1.45,2.90)(1.81, 3.63)(2, 4)
			// (2.18, 4.36)

			System.out.println("Notificando llegada a destino...\n");
			lc.notificarDetencion();

		} else {
			System.out.println("No hay mas destinos para entregar paquetes!");
//			while (this.camion.getDestinos().isEmpty()) {
				ejecutar = false;
//			}
				System.out.println("Finalizando entrega...");
//				System.exit(0);
		}
	}

	private void actualizarTiempos(int i) {
		Destino actual = this.camion.getLocalizacion();
		Float x, y, hipotenusa;
		Integer horasInt, minutosInt, segundosInt;
		float horas, minutos, segundos;

		for (Destino d : this.camion.getDestinos().keySet()) {
			x = d.getX() - actual.getX();
			y = d.getY() - actual.getY();
			hipotenusa = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

			// System.out.println();
			// System.out.println("Hipotenusa = " + hipotenusa + "km");

			horas = (float) (hipotenusa / this.camion.getVelocidad());
			horasInt = (int) horas;

			minutos = horas * 60 - horasInt * 60;
			minutosInt = (int) minutos;

			segundos = minutos * 60 - minutosInt * 60;
			segundosInt = (int) segundos;

			Tiempo t = new Tiempo(horasInt, minutosInt, segundosInt);
			this.camion.getDestinos().put(d, t);

			Tiempo tDetenido = new Tiempo(0, 0, 0);
			tDetenido = tDetenido.sumarTiempo(tDetenido, this.camion.getDestinos().get(d));

			if (i == 0) {
				this.camion.setTiempoMovimiento(t.sumarTiempo(this.camion.getTiempoMovimiento(), t));
//				System.out.print("Tiempo movimiento: ");
//				lc.imprimirTiempo(this.camion.getTiempoMovimiento());
			}
		}
	}
}

package localizacion_gestion_destino;

import java.util.*;

import principal.*;
import controlador.*;

//http://snipplr.com/view/2789/sorting-map-keys-by-comparing-its-values/
//https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
//https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
//https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
//https://www.tutorialspoint.com/java/java_using_comparator.htm

public class LocalizacionControl implements InterfazLocalizacionGestionDestino {
	private Camion camion;
	private InterfazControlador ic;
	private HiloMover hm;
	private HashMap<Destino, Float> hipotenusas;
	private ArrayList<Tiempo> tiempoParadas;
	private LinkedHashMap<Destino, Float> numeroParadas;

	public LocalizacionControl() {
		this.ic = new Controlador();
		this.camion = new Camion();
		this.hipotenusas = new HashMap<>();
		this.tiempoParadas = new ArrayList<>();
		this.numeroParadas = new LinkedHashMap<>();

		this.hm = new HiloMover(this, this.camion);
		this.hm.start();
	}

	@Override
	public void anadirDestino(Destino d) {
		camion.getDestinos().put(d, null);
		realizarAlgoritmo();
		calcularKm();
	}

	private void calcularKm() {
		float km = 0;
		for (Destino destino : hipotenusas.keySet()) {
			km = km + this.hipotenusas.get(destino);
		}
		this.camion.setKilometros(km);
	}

	private void realizarAlgoritmo() {
		Float x;
		Float y;
		Float hipotenusa;
		Tiempo t;
		float horas, minutos, segundos;
		int horasInt, minutosInt, segundosInt;
		for (Destino d : camion.getDestinos().keySet()) {
			x = d.getX();
			y = d.getY();
			hipotenusa = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

			// System.out.println("(x, y) = (" + x + ", " + y + ")");
			// System.out.println("Hipotenusa = " + hipotenusa + "km");

			this.hipotenusas.put(d, hipotenusa);

			horas = (float) (hipotenusa / camion.getVelocidad());
			horasInt = (int) horas;

			minutos = horas * 60 - horasInt * 60;
			minutosInt = (int) minutos;

			segundos = minutos * 60 - minutosInt * 60;
			segundosInt = (int) segundos;

			// System.out.println("Tiempo = (" + horasInt + "h, " + minutosInt + "min, " +
			// segundosInt + "s)");

			t = new Tiempo(horasInt, minutosInt, segundosInt);
			camion.getDestinos().put(d, t);
		}

		// para imprimir
		// int i = 0;
		// for (Map.Entry<Destino, Tiempo> h : camion.getDestinos().entrySet()) {
		// System.out.println("Destino " + i);
		// System.out.println("(x = " + h.getKey().getX() + ", y = " + h.getKey().getY()
		// + ")");
		// System.out.println("Tiempo = (" + h.getValue().getHora() + "h, " +
		// h.getValue().getMinuto() + "min, " + h.getValue().getSegundo() + "s)");
		// System.out.println();
		// i++;
		// }

		// asi estan os tempos actualizados, falta ordenalos
		ordenarTiempos(camion.getDestinos());
	}

	private void ordenarTiempos(LinkedHashMap<Destino, Tiempo> destinos) {
		List<Tiempo> tiempos = new ArrayList<>(destinos.values());

		// exemplos de proba
		// tiempos.add(new Tiempo(0, 30, 0));
		// tiempos.add(new Tiempo(0, 13, 24));
		// tiempos.add(new Tiempo(0, 19, 12));
		// tiempos.add(new Tiempo(1, 5, 12));
		// tiempos.add(new Tiempo(2, 2, 12));
		// tiempos.add(new Tiempo(0, 0, 16));
		// tiempos.add(new Tiempo(0, 0, 12));

		Collections.sort(tiempos, new Tiempo());
		// for (Tiempo t : tiempos) {
		// System.out.println("Tiempo = (" + t.getHora() + "h, " + t.getMinuto() + "min,
		// " + t.getSegundo() + "s)");
		// }
		getKeyByValue(destinos, tiempos);
	}

	private void getKeyByValue(LinkedHashMap<Destino, Tiempo> destinos, List<Tiempo> tiempos) {
		// Tiempo t = new Tiempo();
		LinkedHashMap<Destino, Tiempo> aux = new LinkedHashMap<>();
		for (int j = 0; j < tiempos.size(); j++) {
			for (Map.Entry<Destino, Tiempo> entry : destinos.entrySet()) {
				if (tiempos.get(j).getHora() == entry.getValue().getHora()
						&& tiempos.get(j).getMinuto() == entry.getValue().getMinuto()
						&& tiempos.get(j).getSegundo() == entry.getValue().getSegundo()) {
					aux.put(entry.getKey(), tiempos.get(j));
					// this.camion.setTiempoMovimiento(t.sumarTiempo(this.camion.getTiempoMovimiento(),
					// tiempos.get(j)));
				}
			}
		}
		this.camion.setDestinos(aux);

		// System.out.print("Tiempo movimiento: ");
		// imprimirTiempo(this.camion.getTiempoMovimiento());
	}

	@Override
	public void imprimirDestinos() {
		for (Map.Entry<Destino, Tiempo> h : this.camion.getDestinos().entrySet()) {
			System.out.println("(x = " + h.getKey().getX() + ", y = " + h.getKey().getY() + ")");
			System.out.println("Tiempo = (" + h.getValue().getHora() + "h, " + h.getValue().getMinuto() + "min, "
					+ h.getValue().getSegundo() + "s)");
			System.out.println();
		}
	}

	@Override
	public void imprimirDestino(Destino d) {
		System.out.println("(x = " + d.getX() + ", y = " + d.getY() + ")");
	}

	@Override
	public void imprimirTiempo(Tiempo t) {
		System.out.println("Tiempo = (" + t.getHora() + "h, " + t.getMinuto() + "min, " + t.getSegundo() + "s)");
	}

	@Override
	public Tiempo buscarFecha(Destino d) {
		return this.camion.getDestinos().get(d);
	}

	public void simularParada(Destino d) {
		int hayParada = (int) (Math.random() * 100);
		if (hayParada % 2 == 0) { // nueva parada
			int aleatorio = (int) (Math.random() * 3 + 1); // cambiar a 58
			System.out.println("Hay parada de " + aleatorio + "s");

			Tiempo t = new Tiempo();
			t = t.sumarTiempo(this.camion.getTiempoDetenido(), aleatorio);
			this.camion.setTiempoDetenido(t);

			System.out.print("Tiempo detenido: ");
			imprimirTiempo(t);

			this.camion.setNumParadas(this.camion.getNumParadas() + 1);
			if (this.numeroParadas.get(d) == null) {
				this.numeroParadas.put(d, 1f);
			} else {
				this.numeroParadas.put(d, this.numeroParadas.get(d) + 1); // numero de paradas que tenia ese destino + 1
			}

			System.out.println("Numero paradas en (x = " + d.getX() + ", y = " + d.getY() + ") = "
					+ this.numeroParadas.get(d) + "\n");

			this.tiempoParadas.add(new Tiempo(0, 0, aleatorio));
			try {
				Thread.sleep(aleatorio * 1000);
			} catch (InterruptedException e) {
				System.out.println("Excepcion on calcularAleatorio()");
			}
		} else {
			// System.out.println("No hay parada!");
		}
	}

	@Override
	public Destino siguienteDestino() {
		Destino siguienteDestino = null;
		try {
			siguienteDestino = (Destino) this.camion.getDestinos().keySet().toArray()[0]; // el primero de la lista
			return siguienteDestino;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public ArrayList<Destino> listarDestinos() {
		return new ArrayList<>(camion.getDestinos().keySet());
	}

	@Override
	public Camion recibirCamion() {
		return camion;
	}

	public void notificarDetencion() {
		this.ic.notificarDetencion(this.camion);
	}

	// public Camion getCamion() {
	// return camion;
	// }

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	@Override
	public Tiempo calcularTiempoDetenido() {
		return this.camion.getTiempoDetenido();
	}

	@Override
	public Tiempo calcularTiempoMovimiento() {
		return this.camion.getTiempoMovimiento();
	}

	@Override
	public Integer calcularNumeroParadas() {
		return this.camion.getNumParadas();
	}

	@Override
	public Float calcularNumeroKilometros() {
		return this.camion.getKilometros();
	}

	@Override
	public Float calcularMediaVelocidad() {
		return this.camion.getVelocidad(); // no se modifica nunca
	}

	@Override
	public Float calcularMediaParadas() {
		Float media = 0f;
		for (Destino d : this.numeroParadas.keySet()) {
			media += this.numeroParadas.get(d);
			// System.out.println("media = " + media);
		}
		// System.out.println("Media final = " + media);
		return (float) media / (float) this.numeroParadas.size();
	}

	@Override
	public Tiempo calcularMediaTiempoParada() {
		Tiempo t = new Tiempo();
		for (int i = 0; i < this.tiempoParadas.size(); i++) {
			t = t.sumarTiempo(t, this.tiempoParadas.get(i));
			// imprimirTiempo(t);
		}
		t = t.calcularMedia(t, this.tiempoParadas.size());
		// System.out.print("Tiempo final = ");
		// imprimirTiempo(t);
		return t;
	}

	// @Override
	// public Tiempo calcularMediaTiempoEntrega() {
	//
	// }
	//
	// @Override
	// public Tiempo calcularMediaTiempoRecogida() {
	//
	// }
	//
	// @Override
	// public ArrayList<Tiempo> getTiemposDesplazamiento() {
	//
	// }
	//
	// @Override
	// public ArrayList<Tiempo> getTiemposDesplazamientosAcum() {
	//
	// }

	@Override
	public Float getPorcentajeTiempoParadas() {
		Tiempo t = new Tiempo();
		int segundosParado = t.calcularTiempoSegundos(this.camion.getTiempoDetenido());
		int segundosMovimiento = t.calcularTiempoSegundos(this.camion.getTiempoMovimiento());
		int total = segundosParado + segundosMovimiento;
		// System.out.println("Total = " + total + "s");
		return ((float) segundosParado / (float) total) * 100f;
	}

	@Override
	public Float getPorcentajeTiempoMovimiento() {
		Tiempo t = new Tiempo();
		int segundosParado = t.calcularTiempoSegundos(this.camion.getTiempoDetenido());
		int segundosMovimiento = t.calcularTiempoSegundos(this.camion.getTiempoMovimiento());
		int total = segundosParado + segundosMovimiento;
		// System.out.println("Total = " + total + "s");
		return ((float) segundosMovimiento / (float) total) * 100f;
	}

	// @Override
	// public Float getPorcentajeTiempoEntregas() {
	//
	// }

	// creo que se pode borrar
	// public void actualizarDestinos(Destino d) {
	//
	// }

	// public Destino informarDetencion() {
	//
	// }

	public HiloMover getHm() {
		return hm;
	}

	public void setHm(HiloMover hm) {
		this.hm = hm;
	}

}

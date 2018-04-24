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
	private ArrayList<Destino> destinos;
	private HashMap<Destino, Float> hipotenusas;
	private ArrayList<Tiempo> tiempoParadas;
	private ArrayList<Tiempo> tiempoEntregas;
	private LinkedHashMap<Destino, Float> numeroParadas; // de un destino en particular
	private ArrayList<Destino> destinosIniciales;

	// public LocalizacionControl() {
	// this.ic = new Controlador();
	// this.camion = new Camion();
	// this.hipotenusas = new HashMap<>();
	// this.tiempoParadas = new ArrayList<>();
	// this.tiempoEntregas = new ArrayList<>();
	// this.numeroParadas = new LinkedHashMap<>();
	//
	// this.hm = new HiloMover(this, this.camion);
	// this.hm.start();
	// }

	public LocalizacionControl(ArrayList<Destino> destinos) {
		this.ic = new Controlador();
		this.camion = new Camion();
		this.destinos = destinos;
		this.hipotenusas = new HashMap<>();
		this.tiempoParadas = new ArrayList<>();
		this.tiempoEntregas = new ArrayList<>();
		this.numeroParadas = new LinkedHashMap<>();

		for (Destino d : destinos) {
			anadirDestino(d);
		}
		
		this.destinosIniciales = new ArrayList<>();

		for (Destino d : this.camion.getDestinos().keySet()) {
			d = new Destino(d);
			this.destinosIniciales.add(d);
		}

		// this.destinosIniciales = Arrays.copyOf(this.destinosIniciales, new
		// ArrayList<Destino>(this.camion.getDestinos().keySet()));

		System.out.println("Destinos iniciales LocalizacionControl");
		for (Destino d : this.destinosIniciales) {
			imprimirDestino(d);
		}

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

		// asi estan los tiempos actualizados, falta ordenarlos
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
		LinkedHashMap<Destino, Tiempo> aux = new LinkedHashMap<>();
		for (int j = 0; j < tiempos.size(); j++) {
			for (Map.Entry<Destino, Tiempo> entry : destinos.entrySet()) {
				if (tiempos.get(j).getHora() == entry.getValue().getHora()
						&& tiempos.get(j).getMinuto() == entry.getValue().getMinuto()
						&& tiempos.get(j).getSegundo() == entry.getValue().getSegundo()) {
					aux.put(entry.getKey(), tiempos.get(j));
				}
			}
		}
		this.camion.setDestinos(aux);
	}

	@Override
	public void imprimirDestinos() {
		System.out.println("Imprimiendo destinos: ");
		
		for (Destino d : this.destinosIniciales) {
			imprimirDestino(d);
		}

	}

	public void imprimirDestinosActualizados() {
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
			int aleatorio = (int) (Math.random() * 3 + 1); // cambiar a (Math.random() * 58 + 1), parada menor de 1min
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

	public void simularEntrega(Destino d) {
		int aleatorio = (int) (Math.random() * 3 + 1); // cambiar a (Math.random() * 300 + 300) (600s, 10 min x 60s como
														// maximo e 300s como min)
		System.out.println("Hay parada en destino (" + d.getX() + ", " + d.getY() + ") de " + aleatorio + "s");

		int minutos = 0;
		int segundos = aleatorio;

		if (aleatorio > 60) {
			minutos = aleatorio / 60;
			segundos = aleatorio % 60;
		}
		this.tiempoEntregas.add(new Tiempo(0, minutos, segundos));

		try {
			Thread.sleep(aleatorio * 1000);
		} catch (InterruptedException e) {
			System.out.println("Excepcion on calcularAleatorio()");
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
		this.ic.notificarDetencion(this.camion); // se llama a la interfaz del controlador
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

	@Override
	public Tiempo calcularMediaTiempoEntrega() {
		Tiempo t = new Tiempo();
		for (int i = 0; i < this.tiempoEntregas.size(); i++) {
			t = t.sumarTiempo(t, this.tiempoEntregas.get(i));
			// imprimirTiempo(t);
		}

		// System.out.print("Tiempo total Entrega = ");
		// imprimirTiempo(t);

		t = t.calcularMedia(t, this.tiempoEntregas.size());

		// System.out.print("Tiempo final media Entrega = ");
		// imprimirTiempo(t);
		return t;
	}

	// @Override
	// public Tiempo calcularMediaTiempoRecogida() {
	//
	// }

	@Override
	public LinkedHashMap<Destino, Tiempo> getTiemposDesplazamiento() { // devuelve los acumulados tambien
		return getTiemposDesplazamientosAcum();
	}

	@Override
	public LinkedHashMap<Destino, Tiempo> getTiemposDesplazamientosAcum() {
		LinkedHashMap<Destino, Tiempo> tiemposAcum = new LinkedHashMap<>();
		ArrayList<Tiempo> tiempos = this.hm.getTiempoAcumulado();

		for (int i = 0; i < this.destinosIniciales.size(); i++) {
			tiemposAcum.put(this.destinosIniciales.get(i), tiempos.get(i));
		}

		return tiemposAcum;
	}

	@Override
	public Float getPorcentajeTiempoParadas() {
		int segundosParado = calcularTiempoSegundosParado();
		int segundosMovimiento = calcularTiempoSegundosMovimiento();
		int segundosEntrega = calcularTiempoSegundosEntrega();
		int total = segundosParado + segundosMovimiento + segundosEntrega;
		// System.out.println("segundosParado = " + segundosParado);
		// System.out.println("Total = " + total + "s");
		return ((float) segundosParado / (float) total) * 100f;
	}

	@Override
	public Float getPorcentajeTiempoMovimiento() {
		int segundosParado = calcularTiempoSegundosParado();
		int segundosMovimiento = calcularTiempoSegundosMovimiento();
		int segundosEntrega = calcularTiempoSegundosEntrega();
		int total = segundosParado + segundosMovimiento + segundosEntrega;
		// System.out.println("segundosMovimiento = " + segundosMovimiento);
		// System.out.println("Total = " + total + "s");
		return ((float) segundosMovimiento / (float) total) * 100f;
	}

	@Override
	public Float getPorcentajeTiempoEntregas() {
		int segundosParado = calcularTiempoSegundosParado();
		int segundosMovimiento = calcularTiempoSegundosMovimiento();
		int segundosEntrega = calcularTiempoSegundosEntrega();
		int total = segundosParado + segundosMovimiento + segundosEntrega;
		// System.out.println("segundosEntrega = " + segundosEntrega);
		// System.out.println("Total = " + total + "s");
		return ((float) segundosEntrega / (float) total) * 100f;
	}

	private int calcularTiempoSegundosParado() {
		Tiempo t = new Tiempo();
		return t.calcularTiempoSegundos(this.camion.getTiempoDetenido());
	}

	private int calcularTiempoSegundosMovimiento() {
		Tiempo t = new Tiempo();
		return t.calcularTiempoSegundos(this.camion.getTiempoMovimiento());
	}

	private int calcularTiempoSegundosEntrega() {
		Tiempo t = new Tiempo();
		for (int i = 0; i < this.tiempoEntregas.size(); i++) {
			t = t.sumarTiempo(t, this.tiempoEntregas.get(i));
			// imprimirTiempo(t);
		}
		return t.calcularTiempoSegundos(t);
	}

	// public Camion getCamion() {
	// return camion;
	// }

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public HiloMover getHm() {
		return hm;
	}

	public void setHm(HiloMover hm) {
		this.hm = hm;
	}

}

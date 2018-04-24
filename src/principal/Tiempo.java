package principal;

import java.util.Comparator;

public class Tiempo implements Comparator<Tiempo>, Comparable<Tiempo> {
	private int hora;
	private int minuto;
	private int segundo;

	public Tiempo() {
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
	}

	public Tiempo(int hora, int minuto, int segundo) {
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}

	@Override
	public int compare(Tiempo t1, Tiempo t2) {
		if (t1.getHora() == t2.getHora()) {
			if (t1.getMinuto() == t2.getMinuto()) {
				return t1.getSegundo() - t2.getSegundo();
			} else {
				return t1.getMinuto() - t2.getMinuto();
			}
		} else {
			return t1.getHora() - t2.getHora();
		}
	}

	@Override
	public int compareTo(Tiempo t) {
		return 1;
	}

	public Tiempo sumarTiempo(Tiempo tin, int valor) {
		int segundo = 0, minuto = 0, hora = 0;

		segundo = tin.getSegundo() + valor;
		minuto = tin.getMinuto();
		hora = tin.getHora();

		if (segundo > 59) {
			segundo = 0;
			minuto += 1;
			if (minuto > 59) {
				minuto = 0;
				hora += 1;
			}
		}
		return new Tiempo(hora, minuto, segundo);
	}

	public Tiempo sumarTiempo(Tiempo tin, Tiempo sumar) {
		int segundo = 0, minuto = 0, hora = 0;
		segundo = tin.getSegundo() + sumar.getSegundo();
		minuto = tin.getMinuto() + sumar.getMinuto();
		hora = tin.getHora() + sumar.getHora();
		if (segundo > 59) {
			segundo = 0;
			minuto += 1;
			if (minuto > 59) {
				minuto = 0;
				hora += 1;
			}
		} else if (minuto > 59) {
			minuto = 0;
			hora += 1;
		}
		return new Tiempo(hora, minuto, segundo);
	}

	public Tiempo calcularMedia(Tiempo t, int denominador) {
		int segundos = calcularTiempoSegundos(t);
		int segundosFinal = segundos / denominador;

		int minutosFinal = 0, horasFinal = 0;

		if (segundosFinal > 59) {
			minutosFinal = segundosFinal / 60;
			segundosFinal = 0;
			if (minutosFinal > 59) {
				horasFinal = minutosFinal / 60;
				minutosFinal = 0;
			}
		}
		return new Tiempo(horasFinal, minutosFinal, segundosFinal);
	}

	public int calcularTiempoSegundos(Tiempo t) {
		return t.getHora() * 3600 + t.getMinuto() * 60 + t.getSegundo();
	}
}

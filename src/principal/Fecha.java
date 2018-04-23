package principal;

public class Fecha {
	private int ano;
	private int mes;
	private int dia;
	private Tiempo t;
	public Fecha(int ano, int mes, int dia, Tiempo t) {
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.t = t;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public Tiempo getT() {
		return t;
	}
	public void setT(Tiempo t) {
		this.t = t;
	}
	
	
}

package principal;

import java.util.LinkedHashMap;

public class Camion {
    private Destino localizacion;
    private Float kilometros;
    private Integer numParadas;
    private Tiempo tiempoDetenido;
    private Tiempo tiempoMovimiento;
    private Float velocidad;
    private EstadoCamion estadoCamion;
    private LinkedHashMap<Destino, Tiempo> destinos;

    public Camion() {
        this.localizacion = new Destino(0f, 0f);
        this.kilometros = 0f;
        this.numParadas = 0;
        this.tiempoDetenido = new Tiempo();
        this.tiempoMovimiento = new Tiempo();
        this.velocidad = 20f;
        this.estadoCamion = EstadoCamion.TRANSITO;
        this.destinos = new LinkedHashMap<>();
    }

    public Destino getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Destino localizacion) {
        this.localizacion = localizacion;
    }

    public Float getKilometros() {
        return kilometros;
    }

    public void setKilometros(Float kilometros) {
        this.kilometros = kilometros;
    }

    public Integer getNumParadas() {
        return numParadas;
    }

    public void setNumParadas(Integer numParadas) {
        this.numParadas = numParadas;
    }

    public Tiempo getTiempoDetenido() {
        return tiempoDetenido;
    }

    public void setTiempoDetenido(Tiempo tiempoDetenido) {
        this.tiempoDetenido = tiempoDetenido;
    }

    public Tiempo getTiempoMovimiento() {
        return tiempoMovimiento;
    }

    public void setTiempoMovimiento(Tiempo tiempoMovimiento) {
        this.tiempoMovimiento = tiempoMovimiento;
    }

    public Float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Float velocidad) {
        this.velocidad = velocidad;
    }

    public EstadoCamion getEstadoCamion() {
        return estadoCamion;
    }

    public void setEstadoCamion(EstadoCamion estadoCamion) {
        this.estadoCamion = estadoCamion;
    }

    public LinkedHashMap<Destino, Tiempo> getDestinos() {
        return destinos;
    }

    public void setDestinos(LinkedHashMap<Destino, Tiempo> destinos) {
        this.destinos = destinos;
    }
}

//package main;
//
//import java.util.ArrayList;
//
//public interface InterfazComunicacionSistemaCentral {
//public boolean escribirTiempoAleatorio(Camion c);
//public boolean escribirConfirmacionEntrega(Paquete p);
//public ArrayList<Paquete> solicitarPaquetesAEntregar();
//public Paquete solicitarDevolucion();
//public boolean notificarDetencion(Camion c);
//public ArrayList<String> listarParadas();
//public void confirmarPaquete(Paquete p);
//}
//
//public interface InterfazRepartidor {
//public void anadirPaquete(String id, Destino d, Cliente c, String estado);
//public void borrarPaquete(String id);
//public Paquete buscarPaquete(String id);
//public void modificarPaquete(String id, Destino d, Cliente c, EstadoPaquete estado, String observacions);
//public void aceptarPaquete(String id, boolean firma);
//public void rechazarPaquete(String id, String motivoRechazo);
//public void anadirReceptorAlternativo(String id, String nombre, String dni, boolean firma);
//public void ejecutarLecturaEntregasDia();
//public void escribirConfirmacionEntrega(Paquete p);
//}
//
//public interface InterfazControlador {
//public Paquete solicitarPaquete(String id);
//public void anadirPaquete(Paquete p);
//public void borrarPaquete(String id);
//public Paquete buscarPaquete(String id);
//public void modificarPaquete(Paquete p);
//public void notificarDetencion(Camion c);
//public void enviarCamion(Camion c);
//public ArrayList<Paquete> solicitarListaPaquetes();
//public Tiempo solicitarTiempoEstimadoEntregaPaquete(Paquete p);
//}
//
//public interface InterfazLocalizacionGestionDestino {
//public Camion recibirCamion(); 
//public void anadirDestino(Destino d);
//public void imprimirDestinos();
//public void imprimirDestino(Destino d);
//public void imprimirTiempo(Tiempo t);
//public Tiempo buscarFecha(Destino d);
//public Destino siguienteDestino();
//public ArrayList<Destino> listarDestinos();
//public Tiempo calcularTiempoDetenido();
//public Tiempo calcularTiempoMovimiento();
//public Integer calcularNumeroParadas();
//public Float calcularNumeroKilometros();
//public Float calcularMediaVelocidad();
//public Float calcularMediaParadas();
//public Tiempo calcularMediaTiempoParada();
//public Tiempo calcularMediaTiempoEntrega();
//public Tiempo calcularMediaTiempoRecogida();
//public ArrayList<Tiempo> getTiemposDesplazamiento();
//public ArrayList<Tiempo> getTiemposDesplazamientosAcum();
//public Float getPorcentajeTiempoParadas();
//public Float getPorcentajeTiempoMovimiento();
//public Float getPorcentajeTiempoEntregas();
//}

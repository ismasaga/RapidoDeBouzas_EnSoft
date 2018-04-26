import java.util.ArrayList;

import principal.Paquete;
import repartidor.Repartidor;

public class Main {

	public static void main(String[] args) {
		System.out.println("Inicio aplicación");
		Repartidor rep = new Repartidor();
		ArrayList<Paquete> lista = rep.ejecutarLecturaEntregasDia();
		System.out.println("Cargada lista diaria de paquetes");
		for (Paquete p : lista) {
			System.out.println("Paquete con id " + p.getId() + " e estado " + p.getEstado() + " do cliente con dni " + p.getCliente().getDni());
		}
		
	}

}

package controlador;

import main.*;

public class Controlador implements InterfazControlador {
	public Controlador() {
	}

	@Override
	public void notificarDetencion(Camion c) {
		System.out.println("Camion en controlador: ");
		System.out.println("Posicion camion: (" + c.getLocalizacion().getX() + ", " + c.getLocalizacion().getY() + ")");
	}
}

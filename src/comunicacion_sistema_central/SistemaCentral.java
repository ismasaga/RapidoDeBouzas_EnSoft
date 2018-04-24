package comunicacion_sistema_central;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import principal.*;

public class SistemaCentral implements InterfazComunicacionSistemaCentral {
	private int dev = 0; // akl iniciar la clase es 0, pero se va a ir modificando

	@Override
	public boolean escribirTiempoAleatorio(Camion c) { // fichero de escritura aleatorio de camion
		return escribir_camion(c);
	}

	@Override
	public boolean escribirConfirmacionEntrega(Paquete p) { // se escribe en fichero confirmacion de entrega de paquete
		return escribir_paquete(p);
	}

	@Override
	public ArrayList<Paquete> solicitarPaquetesAEntregar() { // leo todas las entregas y devuelvo un array con las
																// entregas todas
		return leer_archivo_entregas();
	}

	@Override
	public Paquete solicitarDevolucion() { // extrae una devolucion de la lista de devoluciones del fichero
		ArrayList<Paquete> array = leer_archivo_devoluciones();
		dev++;
		return array.get(dev); // cada vez devuelve el array siguiente al que cogio antes

	}

	@Override
	public boolean notificarDetencion(Camion c) {
		System.out.println("Método non implementado");
		System.out.println("Detención de camión en destino");
		System.out.println("Posicion camion: (" + c.getLocalizacion().getX() + ", " + c.getLocalizacion().getY() + ")");
		return false;
	}

	@Override
	public ArrayList<String> listarParadas() {
		System.out.println("Método non implementado");
		return null;
	}

	@Override
	public void confirmarPaquete(Paquete p) {
		p.setEstado(EstadoPaquete.ENTREGADO); // suponiendo que uno de las opciones de estado de paquete sea CONFIRMADO
	}

	private ArrayList<Paquete> leer_archivo_entregas() {
		String archivo = "fichero_entregas.csv";
		ArrayList<Paquete> array = new ArrayList<Paquete>();
		String cadena;
		FileReader f;
		BufferedReader b = null;

		try {
			f = new FileReader(archivo);
			b = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (b != null && (cadena = b.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(cadena, ";");

				String dni = st.nextToken();
				String nombre = st.nextToken();
				float pos_x = Float.parseFloat(st.nextToken());
				float pos_y = Float.parseFloat(st.nextToken());
				String tel = st.nextToken();
				String id_paq = st.nextToken();

				Destino des = new Destino(pos_x, pos_y);
				Cliente cli = new Cliente(dni, nombre, des, tel, true); // Se os paquetes son entregados sempre imos ter
																		// a firma a true

				Paquete p = new Paquete(id_paq, des, cli, null, null, null, false, null);

				array.add(p);

			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	private ArrayList<Paquete> leer_archivo_devoluciones() {
		String archivo = "fichero_devoluciones.csv";
		ArrayList<Paquete> array = new ArrayList<Paquete>();
		Paquete p = null;
		String cadena;
		FileReader f;
		BufferedReader b = null;
		
		try {
			f = new FileReader(archivo);
			b = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (b != null && (cadena = b.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(cadena, ";");

				String dni = st.nextToken();
				String nombre = st.nextToken();
				float pos_x = Float.parseFloat(st.nextToken());
				float pos_y = Float.parseFloat(st.nextToken());
				String tel = st.nextToken();
				String id_paq = st.nextToken();

				Destino des = new Destino(pos_x, pos_y);
				Cliente cli = new Cliente(dni, nombre, des, tel, true); // Se os paquetes son entregados sempre imos ter
																		// a firma a true

				p = new Paquete(id_paq, des, cli, null, null, null, false, null);

				array.add(p);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	private boolean escribir_camion(Camion c) {
		try {
			String filename = "fichero_escritura.csv";
			FileWriter fw = new FileWriter(filename, true); // the true will append the new data
			fw.write(c.getLocalizacion().getX() + "; " + c.getLocalizacion().getY() + "; " + c.getEstadoCamion() + "; "
					+ "1 hora 12 minutos \n");
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
			return false;
		}

		return true;
	}

	private boolean escribir_paquete (Paquete p) {
		try{
		    String filename= "fichero_escritura.csv";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(p.getId() + "; " + p.getCliente().getDni() + "\n");
		    fw.close();
		}
		catch(IOException ioe) {
		    System.err.println("IOException: " + ioe.getMessage());
		    return false;
		}
		
		return true;
	}
}

package comunicacion_sistema_central;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import controlador.InterfazControlador;
import principal.*;

public  class SistemaCentral implements InterfazComunicacionSistemaCentral {
	private int dev=0;	//akl iniciar la clase es 0, pero se va a ir modificando
	HiloEscribirAleatorio fio;

	@Override
	public boolean escribirTiempoAleatorio(Camion c) {		//fichero de escritura aleatorio de camion
		return escribir_camion(c);
	}

	@Override
	public boolean escribirConfirmacionEntrega(Paquete p) {		//se escribe en fichero confirmacion de entrega de paquete
		return escribir_paquete(p);
	}

	@Override
	public ArrayList<Paquete> solicitarPaquetesAEntregar() {		//leo todas las entregas y devuelvo un array con las entregas todas
		return leer_archivo_entregas();
	}

	@Override
	public Paquete solicitarDevolucion() {		//extrae una devolucion de la lista de devoluciones del fichero
		ArrayList<Paquete> array = leer_archivo_devoluciones();
		dev++;
		return array.get(dev);	//cada vez devuelve el array siguiente al que cogio  antes

	}

	@Override
	public boolean notificarDetencion(Camion c) {
		//System.out.println("Detención de camión en destino");
		//System.out.println("Posicion camion: (" + c.getLocalizacion().getX() + ", " + c.getLocalizacion().getY() + ")");
		System.out.println("No implementada");
		return true;
	}

	@Override
	public ArrayList<String> listarParadas(){
		System.out.println("No implementada");
		return null;
	}

	@Override
	public void confirmarPaquete(Paquete p) {
		p.setEstado (EstadoPaquete.ENTREGADO);				//suponiendo que uno de las opciones de estado de paquete sea entregado
	}

	private ArrayList<Paquete> leer_archivo_entregas () {
		String archivo = "fichero_entregas.csv";
		ArrayList <Paquete> array = new ArrayList <Paquete>();
		String cadena;
		FileReader f = null;

		try {
			f = new FileReader(archivo);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader b = new BufferedReader(f);
		try {
			while((cadena = b.readLine())!=null) {

				StringTokenizer st = new StringTokenizer(cadena,";");

				String dni = st.nextToken();
				String nombre = st.nextToken();
				float pos_x = Float.parseFloat(st.nextToken());
				float pos_y = Float.parseFloat(st.nextToken());
				String tel = st.nextToken();
				String id_paq = st.nextToken();

				Destino des = new Destino (pos_x, pos_y);
				Cliente cli = new Cliente (dni, nombre, des, tel, false);		// estoy metiendo la firma a false

				Paquete p = new Paquete(id_paq, des, cli, null, null, null, false, EstadoPaquete.EN_REPARTO);

				array.add(p);

			}
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	private ArrayList<Paquete> leer_archivo_devoluciones () {
		String archivo = "fichero_devoluciones.csv";
		ArrayList<Paquete> array = new ArrayList<Paquete>();
		Paquete p = null;
		String cadena;
		FileReader f = null;

		try {
			f = new FileReader(archivo);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader b = new BufferedReader(f);
		try {
			while((cadena = b.readLine())!=null) {

				StringTokenizer st = new StringTokenizer(cadena,";");

				String dni = st.nextToken();
				String nombre = st.nextToken();
				float pos_x = Float.parseFloat(st.nextToken());
				float pos_y = Float.parseFloat(st.nextToken());
				String tel = st.nextToken();
				String id_paq = st.nextToken();

				Destino des = new Destino (pos_x, pos_y);
				Cliente cli = new Cliente (dni, nombre, des, tel, false);		// estoy metiendo la firma a false

				p = new Paquete(id_paq, des, cli, null, null, null, false, EstadoPaquete.DEVOLUCION);

				array.add(p);
			}
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	private boolean escribir_camion (Camion c) {
		String filename= "fichero_escritura.csv";
		FileWriter fw = null;
		LinkedHashMap<Destino, Tiempo> lista = c.getDestinos();
		Tiempo t = null;
		
		for (Destino d : lista.keySet()) {
			t = lista.get(d);
		}
		if (t == null)
			t = new Tiempo(0, 0, 0);
		try {
			fw = new FileWriter(filename, true);
		} catch (IOException e) {
			e.printStackTrace();
		} try {
			fw.write(c.getLocalizacion().getX() + "; " + c.getLocalizacion().getY() + "; " + c.getEstadoCamion() + "; " + t.getHora() + ":" + t.getMinuto() + ":" + t.getSegundo() + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		} try {
			fw.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		System.err.println("Escrito");
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













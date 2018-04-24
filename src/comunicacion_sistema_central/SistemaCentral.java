package comunicacion_sistema_central;

import java.util.ArrayList;

import principal.*;

public  class SistemaCentral implements InterfazComunicacionSistemaCentral {
	private int dev=0;	//akl iniciar la clase es 0, pero se va a ir modificando
	
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
		System.out.println("Detención de camión en destino");
		System.out.println("Posicion camion: (" + c.getLocalizacion().getX() + ", " + c.getLocalizacion().getY() + ")");
	}
	
	@Override
	public ArrayList<String> listarParadas(){
		
		
	}
	
	@Override
	public void confirmarPaquete(Paquete p) {
		p.setEstado (confirmado);				//suponiendo que uno de las opciones de estado de paquete sea CONFIRMADO
	}
	
	private ArrayList<Paquete> leer_archivo_entregas () throws FileNotFoundException, IOException{
		String archivo = "fichero_entregas.csv";
		ArrayList <Paquete> array = new ArrayList <Paquete>();
		
		String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
        	
        	StringTokenizer st = new StringTokenizer(cadena,";");
        	
        	String dni = st.nextToken();
        	String nombre = st.nextToken();
        	float pos_x = st.nextToken();
        	float pos_y = st.nextToken();
        	String tel = st.nextToken();
        	String id_paq = st.nextToken();
        	
        	Destino des = new Destino (pos_x, pos_y);
        	Cliente cli = new Cliente (dni, nombre, des, tel, null);		// estoy metiendo la firma a null
        	
        	Paquete p = new Paquete(id_paq, des, cli, null, null, null, false, null);
        	
        	array.add(p);
        	
        }
        
        b.close();
        
        return array;
	}
	
	private ArrayList<Paquete> leer_archivo_devoluciones () throws FileNotFoundException, IOException{
		String archivo = "fichero_devoluciones.csv";
		ArrayList<Paquete> array = new ArrayList<Paquete>();
		Paquete p = null;
		
		String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
        	
        	StringTokenizer st = new StringTokenizer(cadena,";");
        	
        	String dni = st.nextToken();
        	String nombre = st.nextToken();
        	float pos_x = st.nextToken();
        	float pos_y = st.nextToken();
        	String tel = st.nextToken();
        	String id_paq = st.nextToken();
        	
        	Destino des = new Destino (pos_x, pos_y);
        	Cliente cli = new Cliente (dni, nombre, des, tel, null);		// estoy metiendo la firma a null
        	
        	p = new Paquete(id_paq, des, cli, null, null, null, false, null);
        	
        	array.add(p);
        }
        
        b.close();
        
        return array;
	}

	private boolean escribir_camion (Camion c) throws IOException {
		try{
		    String filename= "fichero_escritura.csv";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(c.getLocalizacion().getX() + "; " + c.getLocalizacion().getY() + "; " + c.getEstadoCamion() + "; " + "1 hora 12 minutos \n");
		    fw.close();
		}
		catch(IOException ioe) {
		    System.err.println("IOException: " + ioe.getMessage());
		    return false;
		}
		
		return true;
	}

	private boolean escribir_paquete (Paquete p) throws IOException {
		try{
		    String filename= "fichero_escritura.csv";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(p.getId() + "; " + p.getCliente().getDni() "\n");
		    fw.close();
		}
		catch(IOException ioe) {
		    System.err.println("IOException: " + ioe.getMessage());
		    return false;
		}
		
		return true;
	}
}













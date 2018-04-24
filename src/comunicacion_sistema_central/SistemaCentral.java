package comunicacion_sistema_central;

import java.util.ArrayList;

import principal.*;

public  class SistemaCentral implements InterfazComunicacionSistemaCentral {
	
	@Override
	public boolean escribirTiempoAleatorio(Camion c) {
		
	}
	
	@Override
	public boolean escribirConfirmacionEntrega(Paquete p) {
		
	}
	
	@Override
	public ArrayList<Paquete> solicitarPaquetesAEntregar() {
		return leer_archivo_entregas();
	}
	
	@Override
	public Paquete solicitarDevolucion() {
		
	}
	
	@Override
	public boolean notificarDetencion(Camion c) {
		
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
	
	private Paquete leer_archivo_devoluciones () throws FileNotFoundException, IOException{
		String archivo = "fichero_devoluciones.csv";
		
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
        	
        }
        
        b.close();
        
        return p;
	}
}













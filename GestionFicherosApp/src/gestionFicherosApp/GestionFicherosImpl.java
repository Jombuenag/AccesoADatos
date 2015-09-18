package gestionFicherosApp;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

import java.io.File;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	

	
	
	public GestionFicherosImpl(){
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}
	
	private void actualiza(){
		String[] ficheros = carpetaDeTrabajo.list(); //ESTO NOS DARÁ LOS NOMBRES
		filas = ficheros.length / columnas; //CALCULA EL NÚMERO DE FILAS QUE NECESITA
		if (filas * columnas < ficheros.length){
			filas++; //SI HAY RESTO ESTO CREARÁ UNA FILA MÁS
		}
	
		//SE ASIGNA UNA DIMENSION A LA MATRIZ CONTENIDO SEGÚN LOS RESULTADOS
		
		contenido = new String[filas][columnas];
		//ESTO SE LLENARA CON LOS NOMBRES OBTENIDOS
		for(int i = 0; i < columnas; i++){
			for(int j = 0; j < filas; j++){
				int indice = j * columnas + i;
				if (indice < ficheros.length){
					contenido[j][i] = ficheros[indice];
				}else{
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		
		StringBuilder strBuilder = new StringBuilder();
		File file = new File(carpetaDeTrabajo, arg0);
		
		strBuilder.append(" ---INFORMACIÓN DEL SISTEMA--- ");
		strBuilder.append("\n\n");
//		AQUI NOS DIRÁ EL NOMBRE DEL ARCHIVO O CARPETA
		strBuilder.append("NOMBRE: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
//		AQUI SI ES DIRECTORIO O ARCHIVO
		strBuilder.append("TIPO: ");
		if(file.isDirectory()){
			strBuilder.append("Directorio");
		}
		if(file.isFile()){
			strBuilder.append("Archivo");
		}
		strBuilder.append("\n");
//		¿DONDE ESTÁ?
		strBuilder.append("UBICACIÓN: ");
		strBuilder.append(file.getAbsolutePath().toString());
		strBuilder.append("\n");
//		VEAMOS SI ESTÁ OCULTO O NO
		strBuilder.append("OCULTO: ");
		strBuilder.append(file.isHidden());
		
		
		
		
		
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;
	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	
}
 
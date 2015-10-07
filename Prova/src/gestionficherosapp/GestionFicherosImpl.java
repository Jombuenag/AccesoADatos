package gestionficherosapp;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {
		String[] ficheros = carpetaDeTrabajo.list(); //ESTO NOS DARÁ LOS NOMBRES
		filas = ficheros.length / columnas;//CALCULA EL NÚMERO DE FILAS QUE NECESITA
		if (filas * columnas < ficheros.length) {
			filas++;  //SI HAY RESTO ESTO CREARÁ UNA FILA MÁS
		}
//		SE ASIGNA UNA DIMENSION A LA MATRIZ CONTENIDO SEGÚN LOS RESULTADOS
		contenido = new String[filas][columnas];
//		ESTO SE LLENARA CON LOS NOMBRES OBTENIDOS
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int indice = j * columnas + i;
				if (indice < ficheros.length) {
					contenido[j][i] = ficheros[indice];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {
		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}
	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		file.mkdir();
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("No se pudo crear el archivo por falta de permisos");
			e.printStackTrace();
		}
		actualiza();
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
//		PUEDE CAUSAR UN SECURITYEXCEPTION
		file.delete();
		actualiza();	
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
//		 ¿CORRESPONDE EL NOMBRE CON LA CARPETA EXISTENTE?
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
//		 ¿TENGO PERMISOS DE LECTURA?
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
//		NUEVA ASIGNACIÓN DE LA CARPETA DE TRABAJO
		carpetaDeTrabajo = file;
//		NECESITAMOS LLAMAR AL METODO ACTUALIZAR() PARA QUE REALICE SU FUNCIÓN
		actualiza();
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
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
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
//		EN CASO DE QUE FILE NO TENGA PERMISOS DE LECTURA LANZARÁ LA EXCEPCIÓN
			if (!file.canRead()){
				throw new GestionFicherosException("El fichero no tiene permisos de lectura.");
			}
//		POR OTRO LADO SI NO EXISTE LANZARÁ LA MISMA EXCEPCION
			if(!file.exists()){
				throw new GestionFicherosException("El fichero al que intenta acceder no existe.");
			}
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
			if(file.isHidden()){
				strBuilder.append(" Si "+"\n");
			}else{
				strBuilder.append(" No "+"\n");
			}
//		¿CUANDO FUE LA ULTIMA MODIFICACIÓN?
//		PRIMERO NECESITAMOS DAR FORMATO A LA FECHA TAL COMO REQUIERA LA APLICACIÓN
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		strBuilder.append("ULTIMA MODIFICACIÓN: ");
		strBuilder.append(sdf.format(file.lastModified()));		
		strBuilder.append("\n");
//		¿QUE TAMAÑO TIENE EL ARCHIVO?
		strBuilder.append("TAMAÑO: ");
			if(file.isFile()){
				strBuilder.append(file.length()+" bytes");
				strBuilder.append("\n");
			}
//		VEAMOS QUE ESPACIO HAY TOTAL / DISPONIBLE / USABLE. (SE REPRESENTARÁ EN BYTES)
		
			if(file.isDirectory()){
				strBuilder.append(file.list().length + " Archivos");
				strBuilder.append("\n");
				strBuilder.append("ESPACIO TOTAL : ");
				strBuilder.append(file.getTotalSpace()+" bytes");
				strBuilder.append("\n");
				strBuilder.append("ESPACIO DISPONIBLE : ");
				strBuilder.append(file.getFreeSpace()+" bytes");
				strBuilder.append("\n");
				strBuilder.append("ESPACIO USABLE : ");
				strBuilder.append(file.getUsableSpace()+" bytes");
				strBuilder.append("\n");
		}
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
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
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		return null;
	}

	@Override
	public int numRaices() {
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)throws GestionFicherosException {
		File file1 = new File(carpetaDeTrabajo, arg0);
		File file2 = new File(carpetaDeTrabajo, arg1);
//		¿CAN READ / WRITE? ¿EXISTE?
			if(!carpetaDeTrabajo.canRead()){
				throw new GestionFicherosException("No puedo acceder a la información");
			}
			if(!file1.canWrite()){
				throw new GestionFicherosException("No puedo renombrar el archivo");
			}
			if(!file1.exists()){
				throw new GestionFicherosException("El fichero que buscas no existe");
			}
			
			file1.renameTo(file2);
//		SIEMPRE ACTUALIZAR O NO VEREMOS EL CAMBIO POR PANTALLA
		actualiza();
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

//		¿EXISTE Y ES DIRECTORIO?
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}
//		¿HAY PERMISOS PARA LEER LA CARPETA?
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}
//		ACTUALIZAMOS EL CONTENIDO Y LA CARPETA DE TRABAJO
		carpetaDeTrabajo = file;
		actualiza();
	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
	}

}

package vistas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private BarraInferior barraInferior;
	private BotonesEleccion botonesEleccion;
	private String textoHistoria;
	private String textoBienvenida = "Bienvenido a Libro Aventuras\n\n";
	
	public JTextArea textArea;
	
	int fase = 1;
	
	private HashMap<String, Integer> libroTiradas = new HashMap<String, Integer>();
	
	public VistaPrincipal() {
		// definicion de decisiones
		libroTiradas.put("3b", 6);
		libroTiradas.put("4b", 10);

		
		
		
		
		
		//0000
		String cadena;
		setTitle("Libro Aventuras");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 625);
		
		contentPane =  new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(10);
		textArea.setRows(10);
		textArea.setEditable(false);
		textArea.setBounds(20,35, 450, 400);
		
//		Pilla el texto
		try {
			textoHistoria = leerArchivo("Textos/1.txt");	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		}
		
		cadena = textoBienvenida + textoHistoria;
		
		textArea.setText(cadena);

		contentPane.add(textArea);

		barraInferior = new BarraInferior(this);
		barraInferior.setBounds(0,496,500,101);
		contentPane.add(barraInferior);
		
		botonesEleccion = new BotonesEleccion(this);
		botonesEleccion.setBounds(0,440,494,45);
		contentPane.add(botonesEleccion);

	}
	
	
	
	String leerArchivo(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	public void cambiaFase(String decision) {
		fase++;
	
		String faseYDecision = fase+decision;
		String resultadoDados = "";

		// si la decision actual se encuentra dentro del libro de tiradas entonces calculamos el valor de success
		if (libroTiradas.containsKey(faseYDecision)) {
			System.out.println("Encontrado tirada en fase actual:" + faseYDecision);
			int valorMinimoAcierto = libroTiradas.get(faseYDecision);
			
			
				Random r = new Random();
				int iResultado = (r.nextInt(10) + 0);
					System.out.println("Resultado es = "+iResultado);
				if (iResultado >= valorMinimoAcierto) {
					resultadoDados = "_superada";
				} else {
					resultadoDados = "_fallada";
				}
		
		}


//		Pilla el texto
		try {
			textoHistoria = leerArchivo("Textos/"+fase+"_"+decision+resultadoDados+".txt");	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		}
		
		
		textArea.setText(textoHistoria);

		
	}
}

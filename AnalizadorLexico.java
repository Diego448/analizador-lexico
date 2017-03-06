package analizadorLexico;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AnalizadorLexico {
	public enum tablaDeSimbolos {IDENTIFICADOR, CONSTANTE, TIPO_DE_DATO, OPERADOR_ARITMETICO, OPERADOR_DE_COMPARACION, OPERADOR_DE_ASIGNACION, METODO};
	static LeerDocumento leerDocumento = new LeerDocumento();
	static JFrame ventanaPrincipal = new JFrame("Analizador léxico");
	static JTextField rutaDeArchivo = new JTextField();
	static JTextArea resultado = new JTextArea();
	static JButton abrirArchivo = new JButton("Abrir");
	static JButton analizar = new JButton("Analizar");
	static JLabel etiquetaRutaDeArchivo = new JLabel("Nombre de archivo: ");
	
	public static void inicializarVentana() {
		ventanaPrincipal.setSize(300, 350);
		ventanaPrincipal.setLayout(new FlowLayout());	
		rutaDeArchivo.setPreferredSize(new Dimension(200, 30));
		abrirArchivo.setPreferredSize(new Dimension(70, 30));
		analizar.addActionListener(leerDocumento);
		analizar.setPreferredSize(new Dimension(100, 40));
		resultado.setPreferredSize(new Dimension(280, 205));
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.getContentPane().add(etiquetaRutaDeArchivo);
		ventanaPrincipal.getContentPane().add(rutaDeArchivo);		
		//ventanaPrincipal.getContentPane().add(abrirArchivo);
		ventanaPrincipal.getContentPane().add(analizar);
		ventanaPrincipal.getContentPane().add(resultado);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.setVisible(true);
	}
	
	public static String leerArchivo(String archivo) throws IOException {
		BufferedReader entrada = null;
		String instruccion = null;
		try {
			entrada = new BufferedReader(new FileReader(archivo));
			instruccion = entrada.readLine();
		} catch(IOException ex){
			System.out.println("Archivo no encontrado");
			resultado.setText("Archivo no encontrado");
		} finally {
			entrada.close();
		}
		return instruccion;
	}
	
	public static String analizar(String instruccion) {
		String simbolos[] = new String[7];
		StringBuilder resultado = new StringBuilder();
		simbolos = instruccion.split("\\s");
		for(int i = 0; i < simbolos.length; i++) {
			//System.out.println(simbolos[i]);
			switch(simbolos[i]) {
				case "entero": case "fraccionario":
					System.out.println("Tipo de dato" + ", " + simbolos[i]);
					resultado.append("Identificador" + ", " + simbolos[i] + "\n");
					break;
				case "+": case "-": case "*": case "/":
					System.out.println("Operador aritmético" + ", " + simbolos[i]);
					resultado.append("Operador aritmético" + ", " + simbolos[i] + "\n");
					break;
				case "<": case "=<": case "==": case "=>": case ">":
					System.out.println("Operador de comparación" + ", " + simbolos[i]);
					resultado.append("Operador de comparación" + ", " + simbolos[i] + "\n");
					break;
				case "=":
					System.out.println("Operador de asiganación" + ", " + simbolos[i]);
					resultado.append("Operador de asiganación" + ", " + simbolos[i] + "\n");
					break;
				case "imprimir":
					System.out.println("Método" + ", " + simbolos[i]);
					resultado.append("Método" + ", " + simbolos[i] + "\n");
					break;
				default:
					if(simbolos[i].matches("\\d+")) {
						System.out.println("Constante" + ", " + simbolos[i]);
						resultado.append("Constante" + ", " + simbolos[i] + "\n");
					} else if(simbolos[i].matches("\\D\\w*")){
						System.out.println("Identificador" + ", " + simbolos[i]);
						resultado.append("Identificador" + ", " + simbolos[i] + "\n");
					} else {
						System.out.println("Desconocido" + ", " + simbolos[i]);
						resultado.append("Desconocido" + ", " + simbolos[i] + "\n");
					}
					break;
			}
		}
		return resultado.toString();
	}
	
	public static void main(String[] args) throws IOException {
		inicializarVentana();
		System.out.println(leerArchivo("Prueba.txt"));
		analizar(leerArchivo("Prueba.txt"));
	}

}

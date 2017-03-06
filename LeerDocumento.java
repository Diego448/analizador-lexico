package analizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeerDocumento implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nombreDeArchivo = AnalizadorLexico.rutaDeArchivo.getText().trim();
		try {
			AnalizadorLexico.resultado.setText(AnalizadorLexico.analizar(AnalizadorLexico.leerArchivo(nombreDeArchivo)));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}

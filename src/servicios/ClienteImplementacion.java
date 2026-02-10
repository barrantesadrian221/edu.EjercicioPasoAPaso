package servicios;

import controladores.Inicio;
import dtos.ClienteDto;

/*
 * Clase que implementa al cliente
 */
public class ClienteImplementacion {
	/*
	 * Metodo que registra al cliente
	 */
	public void registroCliente() {
		ClienteDto nuevoCliente = new ClienteDto();

		// Introducir nombre y separarlo
		System.out.println("Introduzca su nombre completo de tal forma: nombre-apellido1-apellido2");
		nuevoCliente.setNombreCompleto(Inicio.sc.next());

		String[] nombreSeparado = nuevoCliente.getNombreCompleto().split("-");
		String nombre = nombreSeparado[0];
		nuevoCliente.setNombre(nombre);
		String apellido1 = nombreSeparado[1];
		nuevoCliente.setApellido1(apellido1);
		String apellido2 = nombreSeparado[2];
		nuevoCliente.setApellido2(apellido2);
		boolean dniEsCorrecto = false;

		nuevoCliente.setEsValidado(false);

//Asegurarse de que el dni es correcto 
		extracted(nuevoCliente, dniEsCorrecto);

		
		
		
		// Añadir cliente
		Inicio.listaClientes.add(nuevoCliente);
		System.out.println("Usuario registrado exitosamente");

	}

	private void extracted(ClienteDto nuevoCliente, boolean dniEsCorrecto) {
		do {
			System.out.println("Introduzca su dni de esta forma: 12345678-X");
			String dniCompleto = Inicio.sc.next();

			String[] catalogo = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
					"V", "H", "L", "C", "K", "E" };

			String[] dniSeparado = dniCompleto.split("-");
			int dniNumerico = Integer.parseInt(dniSeparado[0]);
			String dniLetra = dniSeparado[1];

			int posicion = dniNumerico % 23;
			String letraCorrecta = catalogo[posicion];

			if (dniLetra.equalsIgnoreCase(letraCorrecta)) {
				System.out.println("DNI correcto");
				nuevoCliente.setDni(dniCompleto);
				dniEsCorrecto = true;
			} else {
				System.out.println("DNI INCORRECTO. La letra no corresponde. Inténtelo de nuevo.");
			}

		} while (!dniEsCorrecto);
	}
}
	
	

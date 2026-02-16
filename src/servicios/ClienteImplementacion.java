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

	private long generarId() {
		long generarId = 0;

		if (!Inicio.listaClientes.isEmpty()) {
			generarId = Inicio.listaClientes.getLast().getId();

		}
		return generarId + 1;

	}

	public void registroCliente() {
		ClienteDto nuevoCliente = new ClienteDto();

		nuevoCliente.setId(generarId());

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

		// validacion del email
		System.out.println("Introduzca su email");
		String email = Inicio.sc.next();

		if (email.contains("@")) {
			nuevoCliente.setEmail(email);
			System.out.println("Email añadido");

		} else {
			System.out.println("No existe ese email");
			return;
		}

		// Validar contraseña
		System.out.println("Introduzca su contraseña");
		String contraseña1 = Inicio.sc.next();
		System.out.println("Valide la contraseña");
		String contraseña2 = Inicio.sc.next();

		if (contraseña1.equals(contraseña2)) {
			nuevoCliente.setContrasenia(contraseña2);
		} else {
			System.out.println("no es igual");
		}
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
			String dniLetra = dniSeparado[1].toUpperCase();

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

	public void accederCliente() {
		boolean Acceso = false;
		int attemps = 0;
		do {
			System.out.println("Introduzca su email");
			String email = Inicio.sc.next();

			System.out.println("Introduzca su contraseña");
			String contrasenia = Inicio.sc.next();

			for (ClienteDto c : Inicio.listaClientes) {
				if (c.getContrasenia().equals(contrasenia) && c.getEmail().equals(email)) {
					System.out.println("Campos correctos");
					if (c.isEsValidado() == true) {
						System.out.println("INICIO DE SESION CORRECTO.");
						System.out.println("Bienvenido " + c.getNombre());
						Acceso = true;
					} else {
						System.out.println("Asegurese de que un empleado cualificado le haya validado");
						return;
					}

				} else {
					System.out.println("No son correctos vuelva a intentarlo");
					attemps++;
					if (attemps == 3) {
						System.out.println("Intentos maximos alcanzados");
						return;
						
						
					}

				}

			}
		} while (!Acceso);
	}
}

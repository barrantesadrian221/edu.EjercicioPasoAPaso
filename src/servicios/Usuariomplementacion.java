package servicios;

import controladores.Inicio;
import dtos.UsuarioDto;

/*
 * Clase que implementa al cliente
 */
public class Usuariomplementacion {
	/*
	 * Metodo que registra al cliente
	 */

	UsuarioDto elBoss = new UsuarioDto();
public void codigoBoss() {	
	elBoss.setNombreCompleto("Juan-Juanito-Juarez");
	elBoss.setNombre("Juan");
	elBoss.setApellido1("Juanito");
	elBoss.setApellido2("Juarez");
	elBoss.setContrasenia("impre");
	elBoss.setDni("63620204-L");
	elBoss.setEsEmpleado(true);
	elBoss.setEsValidado(true);
	elBoss.setEmail("@@.com");
	Inicio.listaEmpleado.add(elBoss);
}
	private long generarId() {
		long generarId = 0;

		if (!Inicio.listaClientes.isEmpty()) {
			generarId = Inicio.listaClientes.getLast().getId();

		}
		return generarId + 1;

	}

	public void registroUsuario() {
		UsuarioDto nuevoUsuario = new UsuarioDto();

		nuevoUsuario.setId(generarId());

		// Introducir nombre y separarlo
		System.out.println("Introduzca su nombre completo de tal forma: nombre-apellido1-apellido2");
		nuevoUsuario.setNombreCompleto(Inicio.sc.next());

		String[] nombreSeparado = nuevoUsuario.getNombreCompleto().split("-");
		String nombre = nombreSeparado[0];
		nuevoUsuario.setNombre(nombre);
		String apellido1 = nombreSeparado[1];
		nuevoUsuario.setApellido1(apellido1);
		String apellido2 = nombreSeparado[2];
		nuevoUsuario.setApellido2(apellido2);

		nuevoUsuario.setEsValidado(false);

		// validacion del email
		System.out.println("Introduzca su email");
		String email = Inicio.sc.next();

		if (email.contains("@")) {
			nuevoUsuario.setEmail(email);
			System.out.println("Email añadido");

		} else {
			System.out.println("No existe ese email");
			return;
		}

		boolean dniEsCorrecto = false;
		do {
			
			System.out.println("Introduzca su dni de esta forma: 12345678-X");
			String dniCompleto = Inicio.sc.next();

			if (validacionDni(dniCompleto)) {
				dniEsCorrecto = true;
				nuevoUsuario.setDni(dniCompleto);
			} else {
				System.out.println("DNI Incorrecto");
			}
		} while (!dniEsCorrecto);

		// Validar contraseña
		System.out.println("Introduzca su contraseña");
		String contraseña1 = Inicio.sc.next();
		System.out.println("Valide la contraseña");
		String contraseña2 = Inicio.sc.next();

		if (contraseña1.equals(contraseña2)) {
			nuevoUsuario.setContrasenia(contraseña2);
		} else {
			System.out.println("no es igual");
			return;
		}
		// Añadir cliente
		Inicio.listaClientes.add(nuevoUsuario);
		System.out.println("Usuario registrado exitosamente");

	}

	// Asegurarse de que el dni es correcto
	public static boolean validacionDni(String dniCompleto) {
		boolean dniEsCorrecto = false;
		if (!dniCompleto.matches("^[0-9]{8}-[A-Z]$")) {
			return false;
		}
			String[] catalogo = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
					"V", "H", "L", "C", "K", "E" };

			String[] dniSeparado = dniCompleto.split("-");
			int dniNumerico = Integer.parseInt(dniSeparado[0]);
			String dniLetra = dniSeparado[1].toUpperCase();

			int posicion = dniNumerico % 23;
			String letraCorrecta = catalogo[posicion];

			if (dniLetra.equalsIgnoreCase(letraCorrecta)) {
				System.out.println("DNI correcto");
				dniEsCorrecto = true;

			}
		return dniEsCorrecto;
	}

	public void accederUsuario() {
		boolean Acceso = false;
		int attemps = 0;
		UsuarioDto cAux = new UsuarioDto();
		do {
			System.out.println("Introduzca su email");
			String email = Inicio.sc.next();

			System.out.println("Introduzca su contraseña");
			String contrasenia = Inicio.sc.next();
			for (UsuarioDto c : Inicio.listaClientes) {
				if(Inicio.datosSesion.isEmpty()) {
				if (c.getContrasenia().equals(contrasenia) && c.getEmail().equals(email)) {
					System.out.println("Campos correctos");
					if (c.isEsValidado() == true) {
						System.out.println("INICIO DE SESION CORRECTO.");
						c = cAux;
						Inicio.datosSesion.add(c);
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
			}else {
				System.out.println("La sesion esta iniciada por "+c.getNombre() +" debe cerrar la sesion");
				return;
			}
			}
			
			
			
		} while (!Acceso);
	}
	public void cerrarSesion() {
		if(Inicio.datosSesion.isEmpty()) {
			System.out.println("No se puede borrar");
		}else {
		
		
		Inicio.datosSesion.remove(0);
		System.out.println("Sesion Cerrada");
		
		}
	}
}

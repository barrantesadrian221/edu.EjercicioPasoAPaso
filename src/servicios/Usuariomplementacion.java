package servicios;

import controladores.Inicio;
import dtos.UsuarioDto;

/**
 * Clase que gestiona la lógica de los usuarios (clientes)
 * Incluye registro, validación de DNI, acceso y gestión de sesión.
 */
public class Usuariomplementacion {

	/**
	 * Crea un usuario administrador por defecto (Boss) al iniciar la app
	 */
	public void codigoBoss() {
		UsuarioDto elBoss = new UsuarioDto();
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
		
		Inicio.escribirLog("INFO: Usuario Administrador (Boss) cargado en el sistema.");
	}

	/**
	 * Calcula el siguiente ID disponible basándose en el último cliente de la lista
	 * @return el nuevo ID de tipo long
	 */
	private long generarId() {
		long generarId = 0;
		if (!Inicio.listaClientes.isEmpty()) {
			generarId = Inicio.listaClientes.getLast().getId();
		}
		return generarId + 1;
	}

	/**
	 * Gestiona el formulario de registro de un nuevo cliente
	 */
	public void registroUsuario() {
		UsuarioDto nuevoUsuario = new UsuarioDto();
		nuevoUsuario.setId(generarId());

		// Nombre completo y split
		System.out.println("Introduzca su nombre completo (nombre-apellido1-apellido2):");
		nuevoUsuario.setNombreCompleto(Inicio.sc.next());

		String[] nombreSeparado = nuevoUsuario.getNombreCompleto().split("-");
		nuevoUsuario.setNombre(nombreSeparado[0]);
		nuevoUsuario.setApellido1(nombreSeparado[1]);
		nuevoUsuario.setApellido2(nombreSeparado[2]);

		nuevoUsuario.setEsValidado(false);

		// Validación Email
		System.out.println("Introduzca su email:");
		String email = Inicio.sc.next();
		if (email.contains("@")) {
			nuevoUsuario.setEmail(email);
			Inicio.escribirLog("INFO: Email registrado: " + email);
		} else {
			System.out.println("Error: Formato de email no válido.");
			Inicio.escribirLog("ERROR: Intento de registro con email inválido: " + email);
			return;
		}

		// Validación DNI con bucle
		boolean dniEsCorrecto = false;
		do {
			System.out.println("Introduzca su DNI (12345678-X):");
			String dniCompleto = Inicio.sc.next();
			if (validacionDni(dniCompleto)) {
				dniEsCorrecto = true;
				nuevoUsuario.setDni(dniCompleto);
			} else {
				System.out.println("DNI incorrecto, inténtelo de nuevo.");
				Inicio.escribirLog("WARN: DNI introducido incorrecto en registro.");
			}
		} while (!dniEsCorrecto);

		// Validación Contraseña
		System.out.println("Introduzca su contraseña:");
		String contra1 = Inicio.sc.next();
		System.out.println("Repita la contraseña:");
		String contra2 = Inicio.sc.next();

		if (contra1.equals(contra2)) {
			nuevoUsuario.setContrasenia(contra2);
			Inicio.listaClientes.add(nuevoUsuario);
			System.out.println("Usuario registrado exitosamente. Espere a ser validado.");
			Inicio.escribirLog("INFO: Nuevo usuario registrado con éxito. DNI: " + nuevoUsuario.getDni());
		} else {
			System.out.println("Error: Las contraseñas no coinciden.");
			Inicio.escribirLog("ERROR: Fallo en registro: las contraseñas no coincidían.");
		}
	}

	/**
	 * Comprueba si un DNI es real calculando su letra
	 * @param dniCompleto el DNI con formato 12345678-X
	 * @return true si el formato y la letra son correctos
	 */
	public static boolean validacionDni(String dniCompleto) {
		if (!dniCompleto.matches("^[0-9]{8}-[A-Z]$")) {
			return false;
		}
		
		String[] catalogo = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E" };
		String[] dniSeparado = dniCompleto.split("-");
		int dniNumerico = Integer.parseInt(dniSeparado[0]);
		String dniLetra = dniSeparado[1].toUpperCase();

		int posicion = dniNumerico % 23;
		return dniLetra.equals(catalogo[posicion]);
	}

	/**
	 * Gestiona el inicio de sesión del cliente comprobando credenciales y estado de validación
	 */
	public void accederUsuario() {
		if (!Inicio.datosSesion.isEmpty()) {
			System.out.println("Ya hay una sesión iniciada. Ciérrela primero.");
			Inicio.escribirLog("WARN: Intento de acceso con sesión ya activa.");
			return;
		}

		int intentos = 0;
		do {
			System.out.println("Email:");
			String email = Inicio.sc.next();
			System.out.println("Contraseña:");
			String contrasenia = Inicio.sc.next();

			for (UsuarioDto c : Inicio.listaClientes) {
				if (c.getContrasenia().equals(contrasenia) && c.getEmail().equals(email)) {
					if (c.isEsValidado()) {
						System.out.println("Bienvenido " + c.getNombre());
						Inicio.datosSesion.add(c); // Guardamos el usuario en sesión
						Inicio.escribirLog("INFO: Sesión iniciada correctamente por: " + email);
						return;
					} else {
						System.out.println("Su cuenta aún no ha sido validada por un empleado.");
						Inicio.escribirLog("WARN: Intento de acceso de cuenta no validada: " + email);
						return;
					}
				}
			}

			intentos++;
			System.out.println("Credenciales incorrectas. Intento " + intentos + " de 3.");
			Inicio.escribirLog("WARN: Fallo de login. Email: " + email + ". Intento: " + intentos);

		} while (intentos < 3);

		System.out.println("Has agotado los intentos.");
	}

	/**
	 * Elimina el usuario de la lista de sesión activa
	 */
	public void cerrarSesion() {
		if (Inicio.datosSesion.isEmpty()) {
			System.out.println("No hay ninguna sesión activa que cerrar.");
			Inicio.escribirLog("WARN: Intento de cierre de sesión sin haber ninguna activa.");
		} else {
			String nombre = Inicio.datosSesion.get(0).getNombre();
			Inicio.datosSesion.remove(0);
			System.out.println("Sesión cerrada correctamente. ¡Adiós!");
			Inicio.escribirLog("INFO: Sesión cerrada por el usuario: " + nombre);
		}
	}
}
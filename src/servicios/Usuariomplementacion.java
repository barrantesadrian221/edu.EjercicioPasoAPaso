package servicios;

import controladores.Inicio;
import dtos.UsuarioDto;

/**
 * Lógica de negocio para la gestión de usuarios y sesiones.
 */
public class Usuariomplementacion {

	/**
	 * Registra un usuario administrador predeterminado en el sistema.
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
	}

	/**
	 * Genera un nuevo ID incremental basado en la lista de clientes.
	 * 
	 * @return nuevo identificador de tipo long.
	 */
	private long generarId() {
		long generarId = 0;
		if (!Inicio.listaClientes.isEmpty()) {
			generarId = Inicio.listaClientes.getLast().getId();
		}
		return generarId + 1;
	}

	/**
	 * Registra un nuevo cliente solicitando datos por consola y validando
	 * DNI/Email.
	 */
	public void registroUsuario() {
		UsuarioDto nuevoUsuario = new UsuarioDto();
		nuevoUsuario.setId(generarId());

		System.out.println("Introduzca su nombre completo (nombre-apellido1-apellido2):");
		nuevoUsuario.setNombreCompleto(Inicio.sc.next());

		String[] nombreSeparado = nuevoUsuario.getNombreCompleto().split("-");
		nuevoUsuario.setNombre(nombreSeparado[0]);
		nuevoUsuario.setApellido1(nombreSeparado[1]);
		nuevoUsuario.setApellido2(nombreSeparado[2]);

		nuevoUsuario.setEsValidado(false);

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
	 * Valida la integridad del DNI y comprueba que la letra sea correcta.
	 * 
	 * @param dniCompleto DNI con formato 12345678-X.
	 * @return true si el DNI es válido, false en caso contrario.
	 */
	public static boolean validacionDni(String dniCompleto) {
		if (!dniCompleto.matches("^[0-9]{8}-[A-Z]$")) {
			return false;
		}

		String[] catalogo = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E" };
		String[] dniSeparado = dniCompleto.split("-");
		int dniNumerico = Integer.parseInt(dniSeparado[0]);
		String dniLetra = dniSeparado[1].toUpperCase();

		int posicion = dniNumerico % 23;
		return dniLetra.equals(catalogo[posicion]);
	}

	/**
	 * Gestiona el acceso al sistema verificando credenciales y estado de
	 * validación.
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
						Inicio.datosSesion.add(c);
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
	 * Finaliza la sesión actual eliminando el usuario de la lista de sesión activa.
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
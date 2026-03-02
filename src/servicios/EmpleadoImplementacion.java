package servicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import controladores.Inicio;
import dtos.UsuarioDto;

public class EmpleadoImplementacion {

	public boolean entrarEmpleado() { 
	    System.out.println("Introduzca su contraseña");
	    String contra = Inicio.sc.next();
	    
	    for (UsuarioDto ce : Inicio.listaEmpleado) {
	        if (contra.equals(ce.getContrasenia())) {
	            if (ce.isEsEmpleado()) {
	            	System.out.println("Bienvenido " + ce.getNombre());
	            	Inicio.escribirLog("INFO: Acceso exitoso al menú de empleado. Usuario: " + ce.getNombre());
	                return true;
	            }
	        }
	    }
	    
	    System.out.println("Acceso denegado.");
	    Inicio.escribirLog("WARN: Intento de acceso fallido al menú de empleado.");
	    return false;
	}

	public void validarCliente() {
		System.out.println("--- Clientes pendientes de validación ---");
		boolean hayPendientes = false;
		for (UsuarioDto c : Inicio.listaClientes) {
			if (!c.isEsValidado()) {
				System.out.println(c.toString());
				hayPendientes = true;
			}
		}

		if (!hayPendientes) {
			System.out.println("No hay clientes esperando validación.");
			Inicio.escribirLog("INFO: Se consultó lista de validación pero estaba vacía.");
			return;
		}

		System.out.println("Escriba el DNI del cliente a validar:");
		String dniIntroducido = Inicio.sc.next();
		
		for (UsuarioDto c : Inicio.listaClientes) {
			if (c.getDni().equals(dniIntroducido)) {
				c.setEsValidado(true);
				System.out.println("Cliente " + c.getNombre() + " validado correctamente.");
				Inicio.escribirLog("INFO: Cliente validado. DNI: " + dniIntroducido);
				return;
			}
		}

		System.out.println("Error: El DNI introducido no coincide con ningún cliente.");
		Inicio.escribirLog("WARN: Intento de validación fallido. DNI no encontrado: " + dniIntroducido);
	}

	public void borrarCliente() {
		System.out.println("Introduzca el DNI de la persona a eliminar:");
		String dni = Inicio.sc.next();
		
		// Primero validamos formato del DNI
		if (!Usuariomplementacion.validacionDni(dni)) {
			System.out.println("El formato del DNI es incorrecto.");
			Inicio.escribirLog("ERROR: Formato de DNI inválido en intento de borrado: " + dni);
			return;
		}

		UsuarioDto cAux = null;
		// Buscamos al cliente
		for (UsuarioDto c : Inicio.listaClientes) {
			if (c.getDni().equals(dni)) {
				cAux = c;
				break; // IMPORTANTE: El break va DENTRO del IF
			}
		}

		// Evaluamos el resultado de la búsqueda
		if (cAux == null) {
			System.out.println("Error: No se encuentra ningún cliente con ese DNI.");
			Inicio.escribirLog("WARN: Intento de borrado fallido. DNI inexistente: " + dni);
		} else if (!cAux.isEsValidado()) {
			System.out.println("No se puede borrar: El usuario no está validado por un empleado.");
			Inicio.escribirLog("WARN: Intento de borrado denegado. Usuario no validado. DNI: " + dni);
		} else {
			Inicio.listaClientes.remove(cAux);
			System.out.println("Usuario " + cAux.getNombre() + " borrado exitosamente.");
			Inicio.escribirLog("INFO: Usuario eliminado del sistema. DNI: " + dni);
		}
	}

	public void mostrarClientes() {
		if (Inicio.listaClientes.isEmpty()) {
			System.out.println("La lista de clientes está vacía.");
			return;
		}

		System.out.println("¿Orden Ascendente (A) o Descendente (D)?");
		String resultado = Inicio.sc.next().toUpperCase();

		if (resultado.equals("A")) {
			mostrarAscendente();
			Inicio.escribirLog("INFO: Listado de clientes mostrado en orden ascendente.");
		} else if (resultado.equals("D")) {
			mostrarDescendente();
			Inicio.escribirLog("INFO: Listado de clientes mostrado en orden descendente.");
		} else {
			System.out.println("Opción incorrecta.");
			Inicio.escribirLog("WARN: Opción de ordenación inválida seleccionada: " + resultado);
		}
	}

	private void mostrarAscendente() {
		List<UsuarioDto> copia = new ArrayList<>(Inicio.listaClientes);
		copia.sort(Comparator.comparing(UsuarioDto::getApellido1));
		for(UsuarioDto u : copia) System.out.println(u.toString());
	}

	private void mostrarDescendente() {
		List<UsuarioDto> copia = new ArrayList<>(Inicio.listaClientes);
		copia.sort(Comparator.comparing(UsuarioDto::getApellido1).reversed());
		for(UsuarioDto u : copia) System.out.println(u.toString());
	}

	public void asignarRol() {
		System.out.println("--- Cambio de Rol (Cliente a Empleado) ---");
		System.out.println("Introduzca el DNI del usuario:");
		String dni = Inicio.sc.next();
		
		UsuarioDto usuarioAEncontrar = null;

		for (UsuarioDto c : Inicio.listaClientes) {
			if (dni.equals(c.getDni())) {
				usuarioAEncontrar = c;
				break;
			}
		}

		if (usuarioAEncontrar != null) {
			// Lo quitamos de una lista y lo pasamos a la otra
			Inicio.listaClientes.remove(usuarioAEncontrar);
			usuarioAEncontrar.setEsEmpleado(true);
			Inicio.listaEmpleado.add(usuarioAEncontrar);
			
			System.out.println("Rol actualizado: " + usuarioAEncontrar.getNombre() + " ahora es empleado.");
			Inicio.escribirLog("INFO: Cambio de ROL exitoso. DNI: " + dni);
		} else {
			System.out.println("No se encontró a ninguna persona con ese DNI.");
			Inicio.escribirLog("WARN: Fallo al asignar ROL. DNI no encontrado: " + dni);
		}
	}

	public void listarEmpleadoPrueba() {
		System.out.println("--- Lista de Empleados en el Sistema ---");
		for (UsuarioDto ce : Inicio.listaEmpleado) {
			System.out.println(ce.toString());
		}
		Inicio.escribirLog("INFO: Se ha listado la plantilla de empleados por consola.");
	}
}
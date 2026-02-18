package servicios;

import controladores.Inicio;
import dtos.ClienteDto;

public class EmpleadoImplementacion {

	/*
	 * Metodo que hace la funcion de listar los usuarios no validados y validarlos
	 * mediante DNI
	 */
	public void validarCliente() {
		System.out.println("Clientes pendientes de validación:");
		for (ClienteDto c : Inicio.listaClientes) {
			if (!c.isEsValidado()) {
				System.out.println(c.toString());
			}
		}

		// Recoge el dni
		System.out.println("Escriba el DNI del cliente a validar:");
		String dniIntroducido = Inicio.sc.next();
		boolean usuarioExistente = false;
		// Comprueba si algun usuario tiene dicho dni
		for (ClienteDto c : Inicio.listaClientes) {
			if (c.getDni().equals(dniIntroducido)) {
				c.setEsValidado(true);
				System.out.println("Usuario validado");
				usuarioExistente = true;
				return;
			}
		}

		if (usuarioExistente == false) {
			System.out.println("El usuario no existe");
		}

	}

	public void borrarCliente() {
		ClienteDto cAux = new ClienteDto();
		boolean Borrado = false;
		System.out.println("Introduzca el dni de la persona a eliminar");
		String dni = Inicio.sc.next();
		do {
			for (ClienteDto c : Inicio.listaClientes) {
				c.getDni();
				if (c.getDni().equals(dni)) {
					System.out.println("Todo pasado");
					cAux = c;
					break;
				}
			}

			if (!cAux.getDni().equals(dni)) {
				System.out.println("Error: No se encuentra ningún cliente con ese DNI.");
				return;
			} else if (!cAux.isEsValidado()) {
				System.out.println("No se puede borrar: El usuario no está validado.");
			} else {
				Inicio.listaClientes.remove(cAux);
				System.out.println("Usuario " + cAux.getNombre() + " borrado exitosamente.");
				return;
			}

		} while (!Borrado);

	}

	public void mostrarClientes() {
		for (ClienteDto c : Inicio.listaClientes) {
			if (c.isEsValidado()) {
				System.out.println(c.toString());
			}

		}
	}
}

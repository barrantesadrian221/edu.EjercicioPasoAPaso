package servicios;

import controladores.Inicio;
import dtos.ClienteDto;
import servicios.SubMenuEmpleado;
public class EmpleadoImplementacion {

	/**
	 * Metodo que hace la funcion de listar los usuarios no validados y validarlos
	 * mediante DNI
	 */
	
	
	public void entrarEmpleado() {
	}
	    
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
/**
 * Metodo que borra el cliente de la app
 */
	public void borrarCliente() {
		ClienteDto cAux = new ClienteDto();
		boolean Borrado = false;
		
		
		
		
		
		
		
		do {
			System.out.println("Introduzca el dni de la persona a eliminar");
			String dni = Inicio.sc.next();
			if(ClienteImplementacion.validacionDni(dni)) {
			for (ClienteDto c : Inicio.listaClientes) {
				c.getDni();
				if (c.getDni().equals(dni)) {
					System.out.println("Todo pasado");
					cAux = c;
				}
				break;}
			Borrado = true;
			
			if (!cAux.getDni().equals(dni)) {
				System.out.println("Error: No se encuentra ningún cliente con ese DNI.");
				
			} else if (!cAux.isEsValidado()) {
				System.out.println("No se puede borrar: El usuario no está validado.");
			} else {
				Inicio.listaClientes.remove(cAux);
				System.out.println("Usuario " + cAux.getNombre() + " borrado exitosamente.");
				return;
			}
			}
		} while (!Borrado);

	}
/**
 * Metodo que muestra a los clientes validados de la app
 */
	public void mostrarClientes() {

		for (ClienteDto c : Inicio.listaClientes) {
			if (c.isEsValidado()) {
				System.out.println(c.toString());
			}

		}
	}
	/**
	 * Metodo que asigna el rol de algun usuario
	 */
	public void asignarRol() {
		System.out.println("Introduzca su contraseña");
		String cRol = Inicio.sc.next();
		System.out.println("Introduzca su dni");
		String dniRol = Inicio.sc.next();
		for (ClienteDto c : Inicio.listaClientes) {
			if(cRol.equals(c.getContrasenia()) && dniRol.equals(c.getDni())) {
				if(c.isEsEmpleado()) {
					System.out.println("Hola " +c.getNombre()+" diga a que usuario desea asignar rol");
					System.out.println(c.toString());
				}
			}
				System.out.println(c.toString());
			}
	}
	
	
	
	
}

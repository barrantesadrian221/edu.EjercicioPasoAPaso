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
		System.out.println("Introduzca su contraseña");
		String contra = Inicio.sc.next();
		ClienteDto ceAux = new ClienteDto();
		for (ClienteDto ce : Inicio.listaEmpleado) {
			if(contra.equals(ce.getContrasenia())) {
				ceAux = ce;
				break;
			}else {
				System.out.println("Usted no es un empleado");
			}
		}

		if(ceAux.isEsEmpleado()) {
			System.out.println("Bienvenido "+ ceAux.getNombre());
		}else {System.out.println("No es un empleado");
		return;}
	
	
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
		boolean cerrarAsignar = false;
		ClienteDto cAux = new ClienteDto();
		do {
			for ( ClienteDto cc : Inicio.listaClientes) {
				System.out.println("LISTA CLIENTES");
				System.out.println(cc.toString());
				System.out.println("Introduzca el dni del que quiere cambiar de rol");
				String dni = Inicio.sc.next();
				if(dni.equals(cc.getDni())) {
					Inicio.listaClientes.remove(cc);
					cc.setEsEmpleado(true);
					Inicio.listaEmpleado.add(cc);
					System.out.println("Usuario cambiado correctamente");
					cerrarAsignar=true;
					
					break;
					
				}else {
					System.out.println("No se encontro a dicha persona");
					cerrarAsignar=true;	
				}
				
			}
			
		}while(!cerrarAsignar);
	 
	}
	
	public void listarEmpleadoPrueba() {
		for(ClienteDto ce : Inicio.listaEmpleado) {
			System.out.println(ce.toString());
		}
		
	}
	
	
	
	
}

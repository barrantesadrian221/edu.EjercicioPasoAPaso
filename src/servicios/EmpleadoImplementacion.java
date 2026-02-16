package servicios;

import controladores.Inicio;
import dtos.ClienteDto;

public class EmpleadoImplementacion {

	
	
	/*
	 * Metodo que hace la funcion de listar los usuarios no validados y validarlos mediante DNI
	 */
	public void validarCliente() {
		System.out.println("Clientes pendientes de validación:");
	    for (ClienteDto c : Inicio.listaClientes) {
	        if (!c.isEsValidado()) { 
	            System.out.println(c.toString());
	        }
	    }

	    
	    //Recoge el dni
	    System.out.println
	   ("Escriba el DNI del cliente a validar:");
	    String dniIntroducido = Inicio.sc.next();
	    boolean usuarioExistente = false;
	    //Comprueba si algun usuario tiene dicho dni
	    for (ClienteDto c : Inicio.listaClientes) {
	        if (c.getDni().equals(dniIntroducido)) {
	                                 c.setEsValidado(true);
	            System.out.println("Usuario validado");
	            usuarioExistente = true;
	            return;
	        }
	    }

	    if(usuarioExistente == false) {
	System.out.println("El usuario no existe");
}
	    
	}

	
	public void borrarCliente() {
		ClienteDto cAux = new ClienteDto();
		boolean Borrado = false;
		System.out.println("Introduzca el dni de la persona a eliminar");
		String dni = Inicio.sc.next();
		do {
		for(ClienteDto c : Inicio.listaClientes) {
			c.getDni();
			if(c.getDni()!=dni){
				System.out.println("No va eso no se encuentra dni");
				return;
			}
			
			else if(c.isEsValidado()==false) {
				System.out.println("No valido, validar dni");
				
			}
			
			else {
				System.out.println("Todo pasado");
				cAux=c;
			}
		}
		Inicio.listaClientes.remove(cAux);
		System.out.println("Usuario "+cAux.getNombre()+" borrado exitosamente");
		
		}while(!Borrado);
	}
public void mostrarClientes(){
}
}
	    
	    
	    
	    
	    
	    
	    
	    

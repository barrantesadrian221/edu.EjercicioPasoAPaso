package servicios;

import controladores.Inicio;
import dtos.ClienteDto;

public class EmpleadoImplementacion {

	
	
	/*
	 * Metodo que hace la funcion de listar los usuarios no validados y validarlos mediante DNI
	 */
	public void validarCliente() {
		
		
		
		
		
		//Muestra clientes sin validar
		System.out.println("Clientes sin validar:");
	    for (ClienteDto c : Inicio.listaClientes) {
	        if (c.isEsValidado()) {
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

	    if(usuarioExistente = false) {
	System.out.println("El usuario no existe");
}
	    
	}

public void listarIdPrueba() {
	 for (ClienteDto c : Inicio.listaClientes) {
		 System.out.println(c.toString());
	 }
	
	
}


}
	    
	    
	    
	    
	    
	    
	    
	    

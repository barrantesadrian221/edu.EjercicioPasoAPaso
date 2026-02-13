package servicios;

import controladores.Inicio;

public class SubMenuCliente implements MenuInterfaz {

	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("SUB MENU CLIENTES");
		System.out.println("---------");
		System.out.println("0.Volver al Menu");
		System.out.println("1.Registro cliente ");
		System.out.println("2.Acceso Cliente");
		
		System.out.println("---------");
	
	}
		
		
		
	

	@Override
	public byte seleccionarOpcion() {
		// TODO Auto-generated method stub
		byte opcionUsuario;
		opcionUsuario = Inicio.sc.nextByte();
		return opcionUsuario;
	}
	
	public void accionarMenuCliente() {
		ClienteImplementacion ci = new ClienteImplementacion();
		boolean esCerradoSubCl = false;
		byte opcionCl;
		
		do {
			mostrarMenu();
			opcionCl = seleccionarOpcion();
		
		switch(opcionCl){
			
		case 0:
			System.out.println("Volviendo al Menu");
			esCerradoSubCl = true;
			break;
			
		case 1:
			ci.registroCliente();
			esCerradoSubCl = true;
			break;
			
		case 2:
			ci.accederCliente();
		break;
	
		default:
			System.out.println("Opcion no validation");
			break;
			
			
		}
		
		
		
		
		}while(!esCerradoSubCl);
		
		
		
		
	}

}

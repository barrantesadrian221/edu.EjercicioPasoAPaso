package servicios;

import controladores.Inicio;

public class SubMenuEmpleado implements MenuInterfaz {

	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("SUB MENU EMPLEADOS");
		System.out.println("---------");
		System.out.println("0.Volver al Menu");
		System.out.println("1.Validar Cliente");
		System.out.println("2.Borrar cliente");
		System.out.println("3.Mostrar cliente");
		System.out.println("4.Asignar Usuario");
		System.out.println("---------");
	
	}
		
		
		
	

	@Override
	public byte seleccionarOpcion() {
		// TODO Auto-generated method stub
		byte opcionUsuario;
		opcionUsuario = Inicio.sc.nextByte();
		return opcionUsuario;
	}
	
	public void accionarMenuEmpleado() {
		
		EmpleadoImplementacion ei = new EmpleadoImplementacion();
		boolean esCerradoSubEm = false;
		byte opcionEm;
		
		do {
			mostrarMenu();
			opcionEm = seleccionarOpcion();
		
		switch(opcionEm){
			
		case 0:
			System.out.println("Volviendo al Menu");
			esCerradoSubEm = true;
			break;
			
		case 1:
			ei.validarCliente();
			esCerradoSubEm = true;  
			break;
			
		case 2:
		ei.borrarCliente();
		esCerradoSubEm = true;
		break;
		
		case 3:
			ei.mostrarClientes();
		break;
		
		case 4:
			ei.asignarRol();
			break;
		
		default:
			System.out.println("Opcion no validation");
			break;
			
			
		}
		
		
		
		
		}while(!esCerradoSubEm);
		
		
		
		
	}

}

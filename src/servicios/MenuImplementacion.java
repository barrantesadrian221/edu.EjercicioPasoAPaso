package servicios;

import controladores.Inicio;

public class MenuImplementacion implements MenuInterfaz {

	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("MENUZAOH");
		System.out.println("---------");
		System.out.println("0.Cerrar app");
		System.out.println("1.Version empleado");
		System.out.println("2.Version cliente");
		System.out.println("3.Cerrar Sesion");
		System.out.println("---------");
	
	}

	@Override
	public byte seleccionarOpcion() {
		byte opcionUsuario;
		opcionUsuario = Inicio.sc.nextByte();
		return opcionUsuario;
		// TODO Auto-generated method stub
	}
	
	
	
	
		
		
		
	}




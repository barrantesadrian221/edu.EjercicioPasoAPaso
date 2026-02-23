package controladores;

import java.util.ArrayList;
import java.util.Scanner;

import dtos.ClienteDto;
import servicios.MenuImplementacion;
import servicios.SubMenuCliente;
import servicios.SubMenuEmpleado;

public class Inicio {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList <ClienteDto>listaClientes = new ArrayList<>();



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		MenuImplementacion mi = new MenuImplementacion();
		SubMenuEmpleado sme = new SubMenuEmpleado();
		SubMenuCliente smc = new SubMenuCliente();
		boolean esCerrado = false;
		byte opcionInicial;
		
		do {
			mi.mostrarMenu();
			opcionInicial=mi.seleccionarOpcion();
			switch(opcionInicial) {
			
			case 0:
				
				System.out.println("Cerrando Menu");
				esCerrado = true;
				break;
				
			case 1:
				sme.accionarMenuEmpleado();
				break;
				
			case 2:
				smc.accionarMenuCliente();
				break;
				
			default:
				System.out.println("No existe la opcion");
			
			
			
			}
		}while(!esCerrado);
		
		
		
		sc.close();
	}

}

package controladores;

import java.util.ArrayList;
import java.util.Scanner;

import dtos.ClienteDto;
import servicios.ClienteImplementacion;
import servicios.EmpleadoImplementacion;
import servicios.MenuImplementacion;
import servicios.SubMenuCliente;
import servicios.SubMenuEmpleado;

public class Inicio {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList <ClienteDto>listaClientes = new ArrayList<>();
	public static ArrayList <ClienteDto>listaEmpleado = new ArrayList<>();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoImplementacion ei = new EmpleadoImplementacion();
		ClienteImplementacion ci = new ClienteImplementacion();
		MenuImplementacion mi = new MenuImplementacion();
		SubMenuEmpleado sme = new SubMenuEmpleado();
		SubMenuCliente smc = new SubMenuCliente();
		boolean esCerrado = false;
		byte opcionInicial;
		ci.codigoBoss();
		do {
			mi.mostrarMenu();
			opcionInicial=mi.seleccionarOpcion();
			switch(opcionInicial) {
			
			case 0:
				
				System.out.println("Cerrando Menu");
				esCerrado = true;
				break;
				
			case 1:
				ei.entrarEmpleado();
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

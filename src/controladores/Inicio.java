package controladores;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dtos.UsuarioDto;
import servicios.Usuariomplementacion;
import servicios.EmpleadoImplementacion;
import servicios.MenuImplementacion;
import servicios.SubMenuUsuario;
import servicios.SubMenuEmpleado;
/**
 * Clase Inicial de la aplicacion
 */
public class Inicio {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList <UsuarioDto>listaClientes = new ArrayList<>();
	public static ArrayList <UsuarioDto>listaEmpleado = new ArrayList<>();
	public static ArrayList <UsuarioDto>datosSesion = new ArrayList<>();
/**
 * 
 * @param args
 * Metodo Principal que controla  la aplicacion
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoImplementacion ei = new EmpleadoImplementacion();
		Usuariomplementacion ci = new Usuariomplementacion();
		MenuImplementacion mi = new MenuImplementacion();
		SubMenuEmpleado sme = new SubMenuEmpleado();
		SubMenuUsuario smc = new SubMenuUsuario();
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
				
			case 3:
				ci.cerrarSesion();
				break;
				
			default:
				System.out.println("No existe la opcion");
			
			
			
			}
		}while(!esCerrado);
		
		
		
		sc.close();
	}
}

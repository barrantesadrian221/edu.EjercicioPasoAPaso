package controladores;

import java.io.FileWriter;
import java.io.PrintWriter; // Añadido para facilitar la escritura
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dtos.UsuarioDto;
import servicios.Usuariomplementacion;
import servicios.EmpleadoImplementacion;
import servicios.MenuImplementacion;
import servicios.SubMenuUsuario;
import servicios.SubMenuEmpleado;

public class Inicio {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList <UsuarioDto>listaClientes = new ArrayList<>();
	public static ArrayList <UsuarioDto>listaEmpleado = new ArrayList<>();
	public static ArrayList <UsuarioDto>datosSesion = new ArrayList<>();

	public static void main(String[] args) {
		
		EmpleadoImplementacion ei = new EmpleadoImplementacion();
		Usuariomplementacion ci = new Usuariomplementacion();
		MenuImplementacion mi = new MenuImplementacion();
		SubMenuEmpleado sme = new SubMenuEmpleado();
		SubMenuUsuario smc = new SubMenuUsuario();
		
		boolean esCerrado = false;
		byte opcionInicial;
		
		// Registro inicial del sistema
		escribirLog("INFO: Aplicación iniciada.");
		
		ci.codigoBoss();
		
		do {
			mi.mostrarMenu();
			opcionInicial = mi.seleccionarOpcion();
			
			switch(opcionInicial) {
			
			case 0:
				escribirLog("INFO: El usuario ha solicitado cerrar el menú principal.");
				System.out.println("Cerrando Menu");
				esCerrado = true;
				break;
				
			case 1:
				escribirLog("INFO: Intento de acceso a la versión empleado.");
				if (ei.entrarEmpleado()) {
					escribirLog("INFO: Acceso concedido a empleado.");
			        sme.accionarMenuEmpleado();
				} else {
					escribirLog("WARN: Acceso denegado a empleado.");
				}
				break; // Corregido: El break ahora está fuera del IF para evitar que salte al case 2
				
			case 2:
				escribirLog("INFO: El usuario ha entrado en la versión cliente.");
				smc.accionarMenuCliente();
				break;
				
			case 3:
				escribirLog("INFO: El usuario ha solicitado cerrar sesión.");
				ci.cerrarSesion();
				break;
				
			default:
				escribirLog("WARN: Opción no válida introducida: " + opcionInicial);
				System.out.println("No existe la opcion");
				break;
			}
			
		} while(!esCerrado);
		
		escribirLog("INFO: Aplicación finalizada correctamente.");
		sc.close();
	}

	/**
	 * MÉTODO PARA ESCRIBIR EN EL LOG
	 * Se puede llamar desde cualquier clase usando Inicio.escribirLog("mensaje")
	 */
	public static void escribirLog(String mensaje) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("generalLog.txt", true))) {
			pw.println(mensaje);
		} catch (IOException e) {
			System.err.println("Error al escribir en el log: " + e.getMessage());
		}
	}
}
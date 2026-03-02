package controladores;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
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
	public static ArrayList<UsuarioDto> listaClientes = new ArrayList<>();
	public static ArrayList<UsuarioDto> listaEmpleado = new ArrayList<>();
	public static ArrayList<UsuarioDto> datosSesion = new ArrayList<>();

	public static void main(String[] args) {

		EmpleadoImplementacion ei = new EmpleadoImplementacion();
		Usuariomplementacion ci = new Usuariomplementacion();
		MenuImplementacion mi = new MenuImplementacion();
		SubMenuEmpleado sme = new SubMenuEmpleado();
		SubMenuUsuario smc = new SubMenuUsuario();

		boolean esCerrado = false;
		byte opcionInicial;

		escribirLog("INFO: Aplicación iniciada.");

		ci.codigoBoss();

		do {
			mi.mostrarMenu();
			opcionInicial = mi.seleccionarOpcion();

			switch (opcionInicial) {

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
				break;

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

		} while (!esCerrado);

		// --- EL ÚLTIMO PASO: REVISAR LAS 50 LÍNEAS ANTES DE CERRAR ---
		System.out.println("Revisando registros del sistema...");

		// 1. Buscamos todos los archivos en la carpeta del proyecto
		File carpeta = new File(".");
		File[] listaArchivos = carpeta.listFiles();

		if (listaArchivos != null) {
			for (File archivo : listaArchivos) {
				// 2. Si es un archivo de texto (.txt), contamos sus líneas
				if (archivo.getName().endsWith(".txt")) {
					try {
						// Usamos Files.lines para contar los renglones rápidamente
						long totalLineas = Files.lines(archivo.toPath()).count();

						// 3. Si se pasa de 50, lanzamos el aviso
						if (totalLineas > 50) {
							System.out.println("AVISO: El archivo " + archivo.getName() + " ha superado las 50 líneas ("
									+ totalLineas + ").");
						}
					} catch (IOException e) {
						// Si hay un error leyendo un archivo concreto, seguimos con el siguiente
					}
				}
			}
		}

		escribirLog("INFO: Aplicación finalizada correctamente.");
		System.out.println("Aplicación cerrada.");
		sc.close();
	}

	/**
	 * Escribe mensajes en el log general o en el del usuario si hay sesión activa.
	 */
	public static void escribirLog(String mensaje) {
		String rutaFichero = "generalLog.txt";

		if (!datosSesion.isEmpty()) {
			String dniUsuario = datosSesion.get(0).getDni();
			rutaFichero = dniUsuario + "log.txt";
		}

		try (PrintWriter pw = new PrintWriter(new FileWriter(rutaFichero, true))) {
			pw.println(mensaje);
		} catch (IOException e) {
			System.err.println("Error al escribir en el log: " + e.getMessage());
		}
	}
}
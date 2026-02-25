package dtos;

public class UsuarioDto {
	
	
	private String dni;
	private String nombreCompleto;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String contrasenia;
	private boolean esValidado;
	private String rol;
	private boolean esEmpleado;
	
	public boolean isEsEmpleado() {
		return esEmpleado;
	}
	public void setEsEmpleado(boolean esEmpleado) {
		this.esEmpleado = esEmpleado;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public boolean isEsValidado() {
		return esValidado;
	}
	public void setEsValidado(boolean esValidado) {
		this.esValidado = esValidado;
	}
	@Override
	public String toString() {
		return"Id: "+ id+" DNI: " + dni + "\n"+ "NOMBRE: " + nombreCompleto +"\n"+ "ESTADO VALIDACION: " + ((esValidado == true) ? "si" : "no") +"\n"+"%%%%%%%%%%";
	}
	
	
	
	
}

package model;

public class Empleado {
	private String nIF;
	private String nombre;
	private String apellido;
	private double salario;
	
	
	
	public Empleado(String nIF, String nombre, String apellido, double salario) {
		super();
		this.nIF = nIF;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
	}
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	public String getnIF() {
		return nIF;
	}
	public void setnIF(String nIF) {
		this.nIF = nIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return this.getnIF()+" "+this.getNombre()+" "+
	this.getApellido()+" "+ this.getSalario()+"€";
	}
	
	
}

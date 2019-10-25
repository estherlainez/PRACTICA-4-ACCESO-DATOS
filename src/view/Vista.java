package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.EmpleadoController;
import model.Empleado;

public class Vista {

	public static void main(String[] args) {
		Scanner stdin= new Scanner(System.in);
		int op=0;
		do {
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Modificar");
			System.out.println("4. Borrar");
			System.out.println("5. Listar Todos");
			System.out.println("6. Salir");
			op=stdin.nextInt();
		
		
		switch (op) {
		case 1:
			//Creariamos una funcion
			stdin.nextLine();
			Empleado e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			System.out.println("Introduce Nombre");
			e.setNombre(stdin.nextLine());
			System.out.println("Introduce Apellidos");
			e.setApellido(stdin.nextLine());
			System.out.println("Introduce Salario");
			e.setSalario(stdin.nextDouble());
			if(new EmpleadoController().insertarEmpleado(e)) {
				System.out.println("Empleado insertado");
			}else {
				System.out.println("Empleado no insertado");
			}
			stdin.nextLine();
			break;
		case 2:
			stdin.nextLine();
			System.out.println("Introduce NIF");
			System.out.println(new EmpleadoController().consultarEmpleado(stdin.nextLine()));
			break;
			
		case 3:
			stdin.nextLine();
			e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			System.out.println("Introduce Nombre");
			e.setNombre(stdin.nextLine());
			System.out.println("Introduce Apellidos");
			e.setApellido(stdin.nextLine());
			System.out.println("Introduce Salario");
			e.setSalario(stdin.nextDouble());
			if(new EmpleadoController().modificarEmpleado(e)) {
				System.out.println("Empleado insertado");
			}else {
				System.out.println("Empleado no insertado");
			}
			stdin.nextLine();
			break;
		case 4:
			stdin.nextLine();
			e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			
			if(new EmpleadoController().borrarEmpleado(e)) {
				System.out.println("Empleado borrado");
			}else {
				System.out.println("Empleado no borrado");
			}
			//stdin.nextLine();
			break;
		case 5:
			ArrayList<Empleado>empleados = new EmpleadoController().listarEmpleados();
			for (Empleado empleado : empleados) {
				System.out.println(empleado);
			}
			break;
		case 6:
			System.out.println("Que vaya bien");
			break;

			
		default:
			break;
		}
		
		}while(op!=6);
	}

}

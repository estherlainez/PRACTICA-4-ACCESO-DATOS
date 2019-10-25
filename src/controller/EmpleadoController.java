package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import model.Empleado;

public class EmpleadoController {
	
	private final int tamReg=66;
	private RandomAccessFile raf;
	private String path;
	
	public EmpleadoController() {
		path="./misEmpleadosa.dat";
		File f= new File(path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean insertarEmpleado(Empleado e) {
		
		//Nuestro fichero tiene un max de 10 emp. 
		//La existencia o no de un emp venia dada por su posicion en el mismo
		if(!posOcupada(getPosicion(e.getnIF()))) {
			try {
				//Inserto al empleado
				raf=new RandomAccessFile(new File(this.path), "rw");
				if(raf.length()<=0)
					raf.write(0);
				raf.seek(getPosicion(e.getnIF())*tamReg);
				
				//Escribo las cosas
				StringBuffer sb =new StringBuffer(e.getnIF());
				sb.setLength(9);
				raf.writeChars(sb.toString());
				sb =new StringBuffer(e.getNombre());
				sb.setLength(10);
				raf.writeChars(sb.toString());
				sb =new StringBuffer(e.getApellido());
				sb.setLength(10);
				raf.writeChars(sb.toString());
				raf.writeDouble(e.getSalario());
				raf.close();
				return true;
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
			
		}else {
			return false;
			
			
		}
		
	}
	
	//modificar
	
	public boolean modificarEmpleado(Empleado e) {
		if(posOcupada(getPosicion(e.getnIF()))) {
			try {
				//Inserto al empleado
				raf=new RandomAccessFile(new File(this.path), "rw");
				/*Si quisiera modificar todo menos el dni seria cuestion de
				 * añadir los 18 bytes que ocupa el dni a la hora de
				 * situarnos en la posicion
				 * raf.seek(getPosicion(e.getnIF())*tamReg +18);
				 */
				raf.seek(getPosicion(e.getnIF())*tamReg);
				
				//Escribo las cosas
				StringBuffer sb =new StringBuffer(e.getnIF());
				sb.setLength(9);
				raf.writeChars(sb.toString());
				sb =new StringBuffer(e.getNombre());
				sb.setLength(10);
				raf.writeChars(sb.toString());
				sb =new StringBuffer(e.getApellido());
				sb.setLength(10);
				raf.writeChars(sb.toString());
				raf.writeDouble(e.getSalario());
				raf.close();
				return true;
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				
				return false;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				return false;
			}
			
		}else {
			return false;
			
			
		}
		
	}
	//Borrar
	public boolean borrarEmpleado(Empleado e) {
		if(posOcupada(getPosicion(e.getnIF()))) {
			try {
				//Inserto al empleado
				raf=new RandomAccessFile(new File(this.path), "rw");
				raf.seek(getPosicion(e.getnIF())*tamReg);
				
				//Escribo las cosas
				StringBuffer sb =new StringBuffer(" ");
				sb.setLength(9);
				raf.writeChars(sb.toString());
				
				raf.close();
				return true;
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				
				return false;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				return false;
			}
			
		}else {
			return false;
			
			
		}
	}
	//Consultar 1
	
	public Empleado consultarEmpleado(String nif) {
		if(posOcupada(getPosicion(nif))) {
			try {
				//Inserto al empleado
				raf=new RandomAccessFile(new File(this.path), "r");
				raf.seek(getPosicion(nif)*tamReg);
				
				//DNI
				String dni="";
				for(int i=0; i<9;i++)
					dni+=raf.readChar();
				//NOMBRE
				String nombre="";
				for(int i=0; i<10;i++)
					nombre+=raf.readChar();
				//APELLIDO
				String apellidos="";
				for(int i=0; i<10;i++)
					apellidos+=raf.readChar();
				
				//SALARIO
				double sal= raf.readDouble();
				return new Empleado(dni, nombre, apellidos, sal);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				
				return null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				return null;
			}
			
		}else {
			return null;
			
			
		}
	}
	//Consultar todos
	public ArrayList<Empleado> listarEmpleados(){
		
		try {
			ArrayList<Empleado> empleados =new ArrayList<Empleado>();
			raf=new RandomAccessFile(new File(this.path), "r");
			for(int pos=0; pos<raf.length(); pos+=this.tamReg) {
				//DNI
				String dni="";
				for(int i=0; i<9;i++)
					dni+=raf.readChar();
				if(dni.trim().length()<=0)
					continue;
				//NOMBRE
				String nombre="";
				for(int i=0; i<10;i++)
					nombre+=raf.readChar();
				//APELLIDO
				String apellidos="";
				for(int i=0; i<10;i++)
					apellidos+=raf.readChar();
				
				//SALARIO
				double sal= raf.readDouble();
				empleados.add(new Empleado(dni, nombre, apellidos, sal));
			}
			return empleados;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private int getPosicion(String nif) {
		return Integer.valueOf(nif.substring(0, nif.length()-1))%10;
	}
	
	
	
	private boolean posOcupada(int pos) {
		try {
			raf=new RandomAccessFile(new File(this.path), "r");
			raf.seek(pos*this.tamReg);
			if(raf.readChar()==' ') {
				raf.close();
				return false;
			}else {
				raf.close();
				return true;
			}
		} catch (FileNotFoundException e) {
			
		
		} catch (IOException e) {
			
			
			return false;
		}
		return true;
	}

	
	
	
	
	
	
	
	
	
	
}

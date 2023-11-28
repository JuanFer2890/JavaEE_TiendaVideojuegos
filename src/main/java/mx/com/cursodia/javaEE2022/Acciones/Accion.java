package mx.com.cursodia.javaEE2022.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Accion 
{
	//metodo abstracto que regresa un String. Este es el punto en comun que van a tener todas las acciones
	//el cual va a recibir un parametro del tipo ↓ y va a entregar un ↓
	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse responese);
	
	public static Accion getAccion(String tipo)
	{
		Accion accion = null;
		
		String text = (Accion.class.getPackage()+"."+tipo.substring(1,tipo.lastIndexOf(".do"))+"Accion").substring(8);
		//System.out.println(text); → mx.com.cursodia.javaEE2022.Acciones.MostrarVideojuegosAccion
		
		//System.out.println(text);
		try {
			Class c = Class.forName(text);
			accion = (Accion) c.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accion;
	}
}
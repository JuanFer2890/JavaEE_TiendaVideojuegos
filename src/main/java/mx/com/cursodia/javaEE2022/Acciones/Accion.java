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
		if(tipo.equals("/MostrarVideojuegos.do"))
		{
			accion = new MostrarVideojuegosAccion();
		}
		else if(tipo.equals("/FiltrarVideojuegos.do"))
		{
			accion = new FiltrarVideojuegoAccion();
		}
		else if(tipo.equals("/Editar-NuevoVideojuego.do"))
		{
			accion = new Editar_NuevoVideojuegoAccion();
		}
		else if(tipo.equals("/EjecutarInserccionVideojuego.do"))
		{
			accion = new EjecutarInserccionVideojuegoAccion();
		}
		else if(tipo.equals("/SeleccionarJuegoParaBorrar.do"))
		{
			accion = new SeleccionarJuegoParaBorrarAccion();
		}
		return accion;
	}
}
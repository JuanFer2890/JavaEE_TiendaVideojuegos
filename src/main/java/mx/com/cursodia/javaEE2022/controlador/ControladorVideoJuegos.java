package mx.com.cursodia.javaEE2022.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Acciones.Accion;

public class ControladorVideoJuegos extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	//	capturar los do que lanzamos desde jsp va a ser capturado por el controlador
	//			recibe:		peticion				y			respuesta			que son del tipo HttpServletRequest
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher despachador = null;//← se ocupa de un despachador → elemento que atiende las peticiones  
		
		//esta accion va a tomar un tipo dependiendo de la cual va a ejecutar
		Accion accion = null;
				
		if(request.getServletPath().equals("/MostrarVideojuegos.do"))
		{
			despachador = request.getRequestDispatcher(accion.getAccion("/MostrarVideojuegos.do").ejecutar(request, response));
			despachador.forward(request, response);
		} 
		else if(request.getServletPath().equals("/FiltrarVideojuegos.do"))
		{//ESTO ES LO QUE SE LANZA AL FILTRAR
			despachador = request.getRequestDispatcher(accion.getAccion("/FiltrarVideojuegos.do").ejecutar(request, response));
			despachador.forward(request, response);
		}
		else if(request.getServletPath().equals("/Editar-NuevoVideojuego.do"))
		{//AQUI LLEGA DESDE MostrarVideojuegos.jsp
			despachador = request.getRequestDispatcher(accion.getAccion("/Editar-NuevoVideojuego.do").ejecutar(request, response));
			despachador.forward(request, response);
		}
		else if(request.getServletPath().equals("/EjecutarInserccionVideojuego.do"))
		{	
			//de este action, se manda a llamar a otro .do, por lo que no requiere de un despachador
			response.sendRedirect(accion.getAccion("/EjecutarInserccionVideojuego.do").ejecutar(request, response));	
		}
		else if(request.getServletPath().equals("/SeleccionarJuegoParaBorrar.do"))
		{//AQUI LLEGA DESDE MostrarVideojuegos.jsp
			response.sendRedirect(accion.getAccion("/SeleccionarJuegoParaBorrar.do").ejecutar(request, response));
		}
		
		
	}
}

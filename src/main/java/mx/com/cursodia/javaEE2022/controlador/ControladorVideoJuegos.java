package mx.com.cursodia.javaEE2022.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class ControladorVideoJuegos extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	//	capturar los do que lanzamos desde jsp va a ser capturado por el controlador
	//			recibe:		peticion				y			respuesta			que son del tipo HttpServletRequest
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher despachador = null;//← se ocupa de un despachador → elemento que atiende las peticiones  
		System.out.println("Entro al controlador");
		try {
			List<Videojuego> listaDeVideojuegos = Videojuego.buscarTodos();
			List<Proveedor> listaDeProveedores = Proveedor.buscarTodos();
			
			//A la peticion (request) le vamos a asignar un atributo que es la lista de videojuegos que sacamos.
			//Este atributo es la forma en la que va a poder acceder a ello como si empaquetaramos las cosas en 
			//		   →	→	→	→	→	↓
			//objeto respuesta el cual en 　　↓　　este caso seria el despachador el que lo regresa
			request.setAttribute("listaDeVideojuegos", listaDeVideojuegos);
			request.setAttribute("listaDeProveedores", listaDeProveedores);
//IMPORTANTE le decimos que puede acceder con ↑ atributo
			
			//para eso se necesita un despachador (alguien que atienda las peticiones)
			despachador = request.getRequestDispatcher("MostrarVideojuegos.jsp");
			//													↑
//IMPORTANTE luego le decimos al despachador cual va a ser su pagina (vista objetivo luego de hacer su trabajo) (redireccion)
			despachador.forward(request, response);
//IMPORTANTE se le hace un ↑ forward, es decir, se le agrega todo lo que empaquetamos en el request para que lo procese
			//y el response es que ya hizo su trabajo y ahora ejecuta lo de mandarlo a MostrarVideojuegos.jsp
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
	}
	
}

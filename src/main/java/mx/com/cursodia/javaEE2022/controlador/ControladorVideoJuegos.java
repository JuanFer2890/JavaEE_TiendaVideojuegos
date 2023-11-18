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
		
		try
		{  //									Esto ↓ viene desde el web.xml
			if(request.getServletPath().equals("/MostrarVideojuegos.do"))
			{
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
			} 
			else if(request.getServletPath().equals("/FiltrarVideojuegos.do"))
			{//ESTO ES LO QUE SE LANZA AL FILTRAR
				List<Videojuego> listaDeVideojuegos = Videojuego.buscarTodos();
				List<Proveedor> listaDeProveedores = Proveedor.buscarTodos();
				
				if(!request.getParameter("Proveedor").equals("MostrarTodos"))
				{//mostrar todos los juegos al seleccionar un proveedor en el combobox
					int cveprov = Integer.parseInt(request.getParameter("Proveedor"));

					//"Proveedor" es porque <select name="Proveedor">...</select>
					listaDeVideojuegos = Videojuego.filtrarPorProveedor(cveprov);	
				}
				
				request.setAttribute("listaDeVideojuegos", listaDeVideojuegos);
				request.setAttribute("listaDeProveedores", listaDeProveedores);
				despachador = request.getRequestDispatcher("MostrarVideojuegos.jsp");
				despachador.forward(request, response);
			}
			else if(request.getServletPath().equals("/Editar-NuevoVideojuego.do"))
			{//AQUI LLEGA DESDE MostrarVideojuegos.jsp
				List<Proveedor> listaDeProveedores = Proveedor.buscarTodos();
				request.setAttribute("listaDeProveedores", listaDeProveedores);
				despachador = request.getRequestDispatcher("FormularioInsertarVideojuego.jsp");
				despachador.forward(request, response);
			}
			else if(request.getServletPath().equals("/EjecutarInserccionVideojuego.do"))
			{//AQUI LLEGA DESDE EL FORMULARIO para insertar videojuego
				//Con esto se extraen los datos
				//OJO, PARA ENLAZAR LOS DATOS, SE TIENE QUE TENER EL ATRIBUTO name="..."
				int cve = Integer.parseInt(request.getParameter("CVE"));
				String titulo = request.getParameter("TIT");
				float precio = Float.parseFloat(request.getParameter("PRE"));
				int cveprov = Integer.parseInt(request.getParameter("CVEPROV"));
				int inventario = Integer.parseInt(request.getParameter("INV"));
				
				if(Boolean.parseBoolean(request.getParameter("MOD")))
				{
					Videojuego.actualizarVideoJuego(cve, titulo, precio, cveprov, inventario);
				}
				else
				{
					Videojuego.insertar(cve, titulo, precio, cveprov, inventario);
				}
				//AQUI NO SE USA UN DESPACHADOR PORQUE HACEMOS UNA COSA APARTE QUE ES ACTUALIZAR
				response.sendRedirect("MostrarVideojuegos.do");
			}
			else if(request.getServletPath().equals("/SeleccionarJuegoParaBorrar.do"))
			{//AQUI LLEGA DESDE MostrarVideojuegos.jsp
				
				new Videojuego().borrarVideojuego(Integer.parseInt(request.getParameter("CVE")));
				
				response.sendRedirect("MostrarVideojuegos.do");
			}
		
		
			

		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		
	}
}

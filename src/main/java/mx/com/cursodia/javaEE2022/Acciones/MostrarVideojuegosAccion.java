package mx.com.cursodia.javaEE2022.Acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.ProveedorDAO;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;

public class MostrarVideojuegosAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {

		try {
			List<Videojuego> listaDeVideojuegos = new VideojuegoDAO().buscarTodos();
			List<Proveedor> listaDeProveedores = new ProveedorDAO().buscarTodos();
			
			//A la peticion (request) le vamos a asignar un atributo que es la lista de videojuegos que sacamos.
			//Este atributo es la forma en la que va a poder acceder a ello como si empaquetaramos las cosas en 
			//		   →	→	→	→	→	↓
			//objeto respuesta el cual en 　　↓　　este caso seria el despachador el que lo regresa
			request.setAttribute("listaDeVideojuegos", listaDeVideojuegos);
			request.setAttribute("listaDeProveedores", listaDeProveedores);
//IMPORTANTE le decimos que puede acceder con ↑ atributo
		}	
		catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarVideojuegos.jsp";
	}

}

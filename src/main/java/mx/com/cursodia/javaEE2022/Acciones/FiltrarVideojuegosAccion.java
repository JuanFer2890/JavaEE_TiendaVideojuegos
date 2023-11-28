package mx.com.cursodia.javaEE2022.Acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class FiltrarVideojuegosAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {
		try {//ESTO ES LO QUE SE LANZA AL FILTRAR
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
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "MostrarVideojuegos.jsp";
	}

}

package mx.com.cursodia.javaEE2022.Acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.ProveedorDAO;

public class Editar_NuevoVideojuegoAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {
		List<Proveedor> listaDeProveedores;
		try {
			listaDeProveedores = new ProveedorDAO().buscarTodos();
			request.setAttribute("listaDeProveedores", listaDeProveedores);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "FormularioInsertarVideojuego.jsp";
	}

}

package mx.com.cursodia.javaEE2022.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAOJPAImpl;

// HEREDAR DE Accion Y AGREGAR METODOS SIN IMPLEMENTAR
public class EjecutarInserccionVideojuegoAccion extends Accion
{

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) 
	{
		int cve = Integer.parseInt(request.getParameter("CVE"));
		String titulo = request.getParameter("TIT");
		float precio = Float.parseFloat(request.getParameter("PRE"));
		int cveprov = Integer.parseInt(request.getParameter("CVEPROV"));
		int inventario = Integer.parseInt(request.getParameter("INV"));
		
		try {
			if(Boolean.parseBoolean(request.getParameter("MOD")))
			{
				new VideojuegoDAOJPAImpl().actualizarVideoJuego(cve, titulo, precio, cveprov, inventario);
				
			}
			else
			{
				new VideojuegoDAOJPAImpl().insertar(cve, titulo, precio, cveprov, inventario);
			}
		} 
		catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//returnear hacia donde queremos ir luego de ejecutar esta clase
		return "MostrarVideojuegos.do";
	}

}

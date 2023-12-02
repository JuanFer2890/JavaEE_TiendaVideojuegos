package mx.com.cursodia.javaEE2022.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.IOC.VideojuegoDAOFactory;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;

public class SeleccionarJuegoParaBorrarAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {
		int cve = Integer.parseInt(request.getParameter("CVE"));
		
		VideojuegoDAO vidDAO = VideojuegoDAOFactory.getInstance();
		vidDAO.borrarObjeto(vidDAO.seleccionarObjeto(cve));
		
		return "MostrarVideojuegos.do";
	}

}

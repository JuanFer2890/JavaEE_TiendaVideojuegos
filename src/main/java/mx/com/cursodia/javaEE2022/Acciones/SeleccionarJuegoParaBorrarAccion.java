package mx.com.cursodia.javaEE2022.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.dao.VideojuegoDAOJPAImpl;

public class SeleccionarJuegoParaBorrarAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {
		int cve = Integer.parseInt(request.getParameter("CVE"));
		
		VideojuegoDAOJPAImpl vidDAO = new VideojuegoDAOJPAImpl();
		vidDAO.borrarObjeto(vidDAO.seleccionarObjeto(cve));
		
		return "MostrarVideojuegos.do";
	}

}

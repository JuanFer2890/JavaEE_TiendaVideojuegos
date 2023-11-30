package mx.com.cursodia.javaEE2022.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;

public class SeleccionarJuegoParaBorrarAccion extends Accion
{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse responese) {
		try {
			new VideojuegoDAO().borrarVideojuego(Integer.parseInt(request.getParameter("CVE")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarVideojuegos.do";
	}

}

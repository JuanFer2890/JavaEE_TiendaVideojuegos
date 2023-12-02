package mx.com.cursodia.javaEE2022.servicios;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;

public interface ServicioVideojuego 
{
	public abstract void insertarObjeto(Videojuego videojuego);

	public abstract List<Videojuego> buscarTodos();
	
	public abstract void actualizarObjeto(Videojuego videojuego);

	public abstract void borrarObjeto(Videojuego videojuego);
	
	public abstract Videojuego seleccionarObjeto(Integer cve);
	
	public abstract List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException;
	
	//esto lo va a utilizar spring para eliminar las repeticiones de codigo en los aspectos respetando DRY
	public VideojuegoDAO getVideojuegoDAO();
	public void setVideojuegoDAO(VideojuegoDAO videojuegoDAO);
}

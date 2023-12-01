package mx.com.cursodia.javaEE2022.dao;

import java.util.List;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public interface VideojuegoDAO
{
	public abstract void insertarObjeto(Videojuego videojuego);

	public abstract List<Videojuego> buscarTodos();
	
	public abstract void actualizarObjeto(Videojuego videojuego);

	public abstract void borrarObjeto(Videojuego videojuego);
	
	public abstract Videojuego seleccionarObjeto(Integer cve);
	
	public abstract List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException;

}



package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO  <T, Id extends Serializable>
{
	T seleccionarObjeto(Id id);
	List<T> buscarTodos();
	void borrarObjeto(T objeto);
	void insertarObjeto(T objeto);
	void actualizarObjeto(T objeto);
}

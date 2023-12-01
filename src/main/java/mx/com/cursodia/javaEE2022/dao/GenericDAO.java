package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO  <T, Id extends Serializable>
{
	void insertarObjeto(T objeto);
	
	List<T> buscarTodos();
	
	void actualizarObjeto(T objeto);
	
	void borrarObjeto(T objeto);
	
	T seleccionarObjeto(Id id);
}


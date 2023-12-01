package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDAOJPAImpl <T, Id extends Serializable> implements GenericDAO<T, Id>
{
	private Class<T> claseDePersistencia;
	
	@SuppressWarnings("unchecked")
	public GenericDAOJPAImpl()
	{
		this.claseDePersistencia = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}

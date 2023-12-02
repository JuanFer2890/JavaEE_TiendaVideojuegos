package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;

public class GenericDAOjdbcImpl <T, Id extends Serializable> implements GenericDAO<T, Id>
{
	private Class<T> claseDePersistencia;
	
	@SuppressWarnings("unchecked")
	public GenericDAOjdbcImpl()
	{
		//AQUI SE OBTIENE LA CLASE QUE SE RECIBE COMO Videojuego.class o Proveedor.class y se asigna al atributo de clase
		this.claseDePersistencia = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void insertarObjeto(T objeto) 
	{/*
		String query = "INSERT INTO videojuegos (cve_vid, tit_vid, pre_vid, cveprov_vid, inv_vid) VALUES ";
		query += "("+cve+",'"+titulo+"',"+precio+","+cveprov+","+inventario+")";
		
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);*/
	}

	@Override
	public List<T> buscarTodos() 
	{//									('videojuego'+s)
		String query = "SELECT * FROM "+(claseDePersistencia.getSimpleName().toLowerCase())+"s";
		DataBaseHelper dbh = new DataBaseHelper();
		
		try {
			return dbh.seleccionarBean(query);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void actualizarObjeto(T objeto) 
	{
		/* UTILIZANDO JDBC
		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
		
		DataBaseHelper dbh = new DataBaseHelper();
		int n =dbh.modificarBean(query);
		return n;*/
	}

	@Override
	public void borrarObjeto(T objeto) 
	{
		/*String query = "DELETE FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);*/
	}

	@Override
	public T seleccionarObjeto(Id id) 
	{
		String query = "SELECT * FROM videojuegos WHERE cve_vid="+id;
		DataBaseHelper dbh = new DataBaseHelper();
		List<T> lista = null;
		
		try {
			lista = dbh.seleccionarBean(query);
		} 
		catch (DataBaseException e) 
		{
			e.printStackTrace();
		}
		//ESTO NOS PUEDE DAR UN UNCHECKED EXCEPTION
		return lista.get(0);
	}
	
}

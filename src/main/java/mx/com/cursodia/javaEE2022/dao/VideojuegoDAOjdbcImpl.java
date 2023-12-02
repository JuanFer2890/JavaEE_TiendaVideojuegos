package mx.com.cursodia.javaEE2022.dao;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;

public class VideojuegoDAOjdbcImpl extends GenericDAOjdbcImpl<Videojuego, Integer> implements VideojuegoDAO
{
	@Override
	public List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException 
	{
		String query = "SELECT * FROM videojuegos WHERE cveprov_vid="+cveprov;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		return lista;
	}
	
	//ESTOS METODOS DE ABAJO LOS SOBREESCRIBI AQUI PORQUE AUN TENGO QUE INVESTIGAR COMO HACER ESTO CON OBJETOS GENERICOS
	//EN LA CLASE GenericDAOjdbcImpl.java
	
	@Override
	public void insertarObjeto(Videojuego videojuego) 
	{
		String query = "INSERT INTO videojuegos (cve_vid, tit_vid, pre_vid, cveprov_vid, inv_vid) VALUES ";
		query += "("+videojuego.getCve_vid()+",'"+videojuego.getTit_vid()+"',"+videojuego.getPre_vid()+","
				+videojuego.getCveprov_vid()+","+videojuego.getInv_vid()+")";
		
		DataBaseHelper dbh = new DataBaseHelper();
		try {
			dbh.modificarBean(query);
		}
		catch (DataBaseException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarObjeto(Videojuego videojuego) 
	{
		String query = "UPDATE videojuegos SET tit_vid ='"+videojuego.getTit_vid()+"',pre_vid="+videojuego.getPre_vid()+","
				+ "cveprov_vid="+videojuego.getCveprov_vid()+",inv_vid="+videojuego.getInv_vid()+" WHERE cve_vid ="+videojuego.getCve_vid();
		
		DataBaseHelper dbh = new DataBaseHelper();
		try {
			int n =dbh.modificarBean(query);
		} 
		catch (DataBaseException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void borrarObjeto(Videojuego videojuego) 
	{
		String query = "DELETE FROM videojuegos WHERE cve_vid="+videojuego.getCve_vid();
		DataBaseHelper dbh = new DataBaseHelper();
		try {
			dbh.modificarBean(query);
		} 
		catch (DataBaseException e) 
		{
			e.printStackTrace();
		}
	}


}

package mx.com.cursodia.javaEE2022.DataBaseH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;

public class DataBaseHelper <T>
{
	//private significa que hay que hacer una instancia de esta clase para acceder a esa variable
	//static significa que ya existe desde que arranca el sistema
	//final permite que una ves que se haya asignado el valor, no se pueda cambiar
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/gamers";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	
	private Connection con = null;
	private Statement stm = null;
	
	public Connection getCon() {
		return con;
	}

	public Statement getStm() {
		return stm;
	}

	public int modificarBean(String query) throws DataBaseException
	{
		
		int filas = 0;
		
		try {
			Class.forName(DRIVER);					//	usuario,contrasena
			con = DriverManager.getConnection(URL,USUARIO,CLAVE);
			stm = con.createStatement();
			filas = stm.executeUpdate(query);
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("Clase no encontrada "+e.getMessage());
			throw new DataBaseException("Clase no encontrada");
		}
		catch(SQLException e)
		{
			System.out.println("Error accediendo a la BD "+e.getMessage());
			throw new DataBaseException("Error de SQL",e);
		}
		finally
		{
				try 
				{
					if(stm != null) stm.close();
					if(con != null) con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
		return filas;
	}
	
	//private T gen;
	//USANDO GENERICOS, REGRESA UN BEAN SIN IMPORTAR DE QUE TIPO SEA
	public List<T> seleccionarBean(String query) throws DataBaseException
	{
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		//List<Videojuego> lista = new ArrayList<Videojuego>();
		ArrayList<T> lista = new ArrayList<T>();
		
		try {
			Class.forName(DRIVER);					//	usuario,contrasena
			con = DriverManager.getConnection(URL,USUARIO,CLAVE);
			stm = con.createStatement();
			rs = stm.executeQuery(query);
			
			if(query.contains("videojuegos"))
			{
				while(rs.next())
				{//poblamos la lista
					lista.add((T) new Videojuego(rs.getInt("cve_vid"),rs.getString("tit_vid"),rs.getFloat("pre_vid"),
							rs.getInt("cveprov_vid"), rs.getInt("inv_vid")));
				}
			}
			else if(query.contains("proveedores"))
			{
				while(rs.next())
				{//poblamos la lista
					lista.add((T) new Proveedor(rs.getInt("cve_prov"),rs.getString("nom_prov"),
							rs.getString("email_prov"),rs.getString("tel_prov")));
				}

			}
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("Clase no encontrada "+e.getMessage());
			throw new DataBaseException("Clase no encontrada");
		}
		catch(SQLException e)
		{
			System.out.println("Error accediendo a la BD "+e.getMessage());
			throw new DataBaseException("Error de SQL");
		}
		finally
		{
				try 
				{
					if(stm != null) stm.close();
					if(con != null) con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
		return lista;
	}
	
}

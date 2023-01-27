package mx.com.cursodia.javaEE2022.DataBaseH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper 
{
	//private significa que hay que hacer una instancia de esta clase para acceder a esa variable
	//static significa que ya existe desde que arranca el sistema
	//final permite que una ves que se haya asignado el valor, no se pueda cambiar
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/gamers";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	
	Connection con = null;
	Statement stm = null;
	
	public Connection getCon() {
		return con;
	}

	public Statement getStm() {
		return stm;
	}

	public int modificarVideojuego(String query) throws SQLException
	{
		
		int filas = 0;
		
		try {
			Class.forName(DRIVER);					//	usuario,contrasena
			con = DriverManager.getConnection(URL,USUARIO,CLAVE);
			stm = con.createStatement();
			filas = stm.executeUpdate(query);
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("Error al cargar el driver "+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("Error accediendo a la BD "+e.getMessage());
		}
		finally
		{
			if(stm != null) stm.close();
			if(con != null) con.close();
		}
		return filas;
	}
	
	public ResultSet seleccionarVideojuegos(String query) throws SQLException
	{
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);					//	usuario,contrasena
			con = DriverManager.getConnection(URL,USUARIO,CLAVE);
			stm = con.createStatement();
			rs = stm.executeQuery(query);
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("Error al cargar el driver "+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("Error accediendo a la BD "+e.getMessage());
		}
		finally
		{
			//if(stm != null) stm.close();
			//if(con != null) con.close();
		}
		return rs;
	}
	
}

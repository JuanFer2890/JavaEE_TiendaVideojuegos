package mx.com.cursodia.javaEE2022.IOC;

import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAOHibernateImpl;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAOJPAImpl;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAOjdbcImpl;

public class VideojuegoDAOFactory 
{
	//Todas las instancias que se van a devolver implementan VideojuegoDAO lo cual permite
	//que se pueda devolver como si fuera del mismo tipo
	public static VideojuegoDAO getInstance()
	{
		String type = "JDBC";
		if(type.equals("Hibernate")) 
		{
			return new VideojuegoDAOHibernateImpl();
		}
		else if(type.equals("JPA"))
		{
			return new VideojuegoDAOJPAImpl();
		}
		else if(type.equals("JDBC"))
		{
			return new VideojuegoDAOjdbcImpl();
		}
		else
		{
			System.out.println("VideojuegoDAOFactory.java: Error al seleccionar el tipo de persistencia");
			return null;
		}
		
	}
}

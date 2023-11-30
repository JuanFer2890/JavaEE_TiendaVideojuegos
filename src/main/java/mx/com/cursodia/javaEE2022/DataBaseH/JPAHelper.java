package mx.com.cursodia.javaEE2022.DataBaseH;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper 
{
	private static final EntityManagerFactory emf = buildEntityManagerFactory();
	private static EntityManagerFactory buildEntityManagerFactory()
	{
		try
		{
			return Persistence.createEntityManagerFactory("JavaEE2022");
		}catch(Throwable ex)
		{
			System.out.println(ex.getMessage());
			throw new RuntimeException("Error al crear el factory de JPA");
		}
	}
	
	public static EntityManagerFactory getJPAFactory()
	{
		System.out.println("Se solicita un JPAFactory");
		return emf;
	}
	
}
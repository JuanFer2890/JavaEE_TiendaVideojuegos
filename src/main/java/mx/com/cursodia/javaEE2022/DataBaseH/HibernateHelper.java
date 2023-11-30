package mx.com.cursodia.javaEE2022.DataBaseH;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper 
{
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory()
	{
		try 
		{
		return new Configuration().configure().buildSessionFactory();	
		} catch (Throwable e) 
		{
			System.err.println("DatabaseH/HibernateHelper: Error al crear el factory de session "+e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//Que solamente se pueda usar una configuracion para que no se duplique
	public static SessionFactory getSessionFactory()
	{
		System.out.println("Se entrega un Hibernate sessionFactory");
		return sessionFactory;
	}
}
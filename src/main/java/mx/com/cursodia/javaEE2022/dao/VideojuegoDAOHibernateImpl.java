package mx.com.cursodia.javaEE2022.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

public class VideojuegoDAOHibernateImpl extends GenericDAOHibernateImpl<Videojuego,Integer> implements VideojuegoDAO
{

	@Override
	public List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException {
		SessionFactory factoriaS;
		Session session = null;
		List<Videojuego> lista = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			//		   								  tipo      tabla en MySQL
			Query query = session.createQuery("from Videojuego videojuegos WHERE videojuegos.cveprov_vid = "+cveprov);
			lista = query.list();
			
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
			System.out.println("VideojuegoDAOHibernateImpl.java: Error al filtrar un objeto");
		}finally
		{
			session.close();
		}
		return lista;
	}

}

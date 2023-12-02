package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

public abstract class GenericDAOHibernateImpl <T,Id extends Serializable> implements GenericDAO<T, Id>
{
	private Class<T> claseDePersistencia;
	
	@SuppressWarnings("unchecked")
	public GenericDAOHibernateImpl()
	{
		//AQUI SE OBTIENE LA CLASE QUE SE RECIBE COMO Videojuego.class o Proveedor.class y se asigna al atributo de clase
		this.claseDePersistencia = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public T seleccionarObjeto(Id id)
	{
		SessionFactory factoriaS;
		Session session = null;
		T objeto = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			
			//la cve LO DESIGNAMOS EN Videojuego.xml PARA QUE FUERA EL CAMPO cve_vid
			objeto = session.get(claseDePersistencia, id);
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
			System.out.println("GenericDAOHibernateImpl.java: Error al seleccionar objeto");
		}finally
		{
			session.close();
		}

		return objeto;
	}
	
	@Override
	public List<T> buscarTodos()
	{
		SessionFactory factoriaS = HibernateHelper.getSessionFactory();
		Session session = factoriaS.openSession();  //"FROM Videojuego videojuegos"
		Query query = session.createQuery("FROM "+claseDePersistencia.getSimpleName()+" "+claseDePersistencia.getSimpleName().toLowerCase());
		List<T> listaDeObjetos = query.list();
		session.close();
		
		return listaDeObjetos;
	}
	
	@Override
	public void borrarObjeto(T objeto)
	{
		SessionFactory factoriaS;
		Session session = null;
		Transaction transaction = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			transaction = session.beginTransaction();
			session.remove(objeto);
			transaction.commit();
			
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
			System.out.println("GenericDAOHibernateImpl.java: Error al borrar un objeto");
			//regresar a como estaba
			transaction.rollback();
		}finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void insertarObjeto(T objeto)
	{
		SessionFactory factoriaS;
		Session session = null;
		Transaction transaction = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			transaction = session.beginTransaction();
			session.persist(objeto);
			transaction.commit();
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
			//regresar a como estaba
			transaction.rollback();
		}finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void actualizarObjeto(T objeto)
	{
		SessionFactory factoriaS;
		Session session = null;
		Transaction transaction = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			transaction = session.beginTransaction();
			session.update(objeto);
			transaction.commit();
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
			System.out.println("GenericDAOHibernateImpl.java: Error al actualizar un objeto");
			transaction.rollback();
		}finally
		{
			session.close();
		}
		
	}
}

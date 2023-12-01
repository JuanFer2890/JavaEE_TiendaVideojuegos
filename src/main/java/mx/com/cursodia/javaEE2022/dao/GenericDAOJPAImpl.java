package mx.com.cursodia.javaEE2022.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

public abstract class GenericDAOJPAImpl <T, Id extends Serializable> implements GenericDAO<T, Id>
{
	private Class<T> claseDePersistencia;
	
	@SuppressWarnings("unchecked")
	public GenericDAOJPAImpl()
	{
		//AQUI SE OBTIENE LA CLASE QUE SE RECIBE COMO Videojuego.class o Proveedor.class y se asigna al atributo de clase
		this.claseDePersistencia = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public T seleccionarObjeto(Id id)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		T objeto = null;
		
		try {
			//	       em.find(Videojuego.class,cve);
			objeto = em.find(claseDePersistencia, id);
			em.close();
		}
		catch (PersistenceException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}

		return objeto;
	}
	
	@Override
	public List<T> buscarTodos()
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		
		List<T> listaDeObjetos = null;
		
		
		try {
		//TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v JOIN FETCH v.proveedor", Videojuego.class);
			TypedQuery<T> query = em.createQuery("SELECT o FROM "+claseDePersistencia.getSimpleName()+" o", claseDePersistencia);
			listaDeObjetos = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		
		return listaDeObjetos;
	}
	
	@Override
	public void borrarObjeto(T objeto)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = null;
		
		try 
		{
			//em.getTransaction().begin();
			tx = em.getTransaction();
			tx.begin();
			
			em.remove(em.merge(objeto));
			
			tx.commit();
			//em.getTransaction().commit();
		} 
		catch (PersistenceException e) 
		{
			tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		
	}
	
	@Override
	public void insertarObjeto(T objeto)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		
		try 
		{
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
		} 
		catch (PersistenceException e) 
		{
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		
	}
	
	@Override
	public void actualizarObjeto(T objeto)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		
		try 
		{
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
		} 
		catch (PersistenceException e) 
		{
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
	}
	
}
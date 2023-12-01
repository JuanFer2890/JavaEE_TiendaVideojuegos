package mx.com.cursodia.javaEE2022.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

public class VideojuegoDAOJPAImpl extends GenericDAOJPAImpl<Videojuego, Integer> implements VideojuegoDAO
{
	
	public List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos WHERE cveprov_vid="+cveprov;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		return lista;*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
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
		}finally
		{
			session.close();
		}
		return lista;-------------------------------------------------------------------------------------------*/
		
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.cveprov_vid ="+cveprov, Videojuego.class);
		List<Videojuego> lista = query.getResultList();
		//emf.close();
		em.close();
		return lista;
	}
	
//VERSION ANTERIOR DEL VideojuegoDAOJPAImpl----------------------------------------------------------------------------
//	public void insertar(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
//	{
//		/* UTILIZANDO JDBC
//		String query = "INSERT INTO videojuegos (cve_vid, tit_vid, pre_vid, cveprov_vid, inv_vid) VALUES ";
//		query += "("+cve+",'"+titulo+"',"+precio+","+cveprov+","+inventario+")";
//		
//		DataBaseHelper dbh = new DataBaseHelper();
//		dbh.modificarBean(query);*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS;
//		Session session = null;
//		Transaction transaction = null;
//		try {
//			factoriaS = HibernateHelper.getSessionFactory();
//			session = factoriaS.openSession();
//			transaction = session.beginTransaction();
//			Videojuego V = new Videojuego(cve, titulo, precio, cveprov, inventario);
//			
//			session.persist(V);
//			transaction.commit();
//		} catch (HibernateException e) 
//		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
//			System.out.println(e.getMessage());
//			//regresar a como estaba
//			transaction.rollback();
//		}finally
//		{
//			session.close();
//		}--------------------------------------------------------------------------------------------------------*/
//	
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		em.merge(new Videojuego(cve,titulo,precio,cveprov,inventario));
//		em.getTransaction().commit();
//		em.close();
//		//emf.close();
//		
//		
//	}
//	
//	public List<Videojuego> buscarTodos() throws DataBaseException
//	{
//		/* UTILIZANDO JDBC
//		String query = "SELECT * FROM videojuegos";
//		DataBaseHelper dbh = new DataBaseHelper();
//		return dbh.seleccionarBean(query);*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS = HibernateHelper.getSessionFactory();
//		Session session = factoriaS.openSession();
//		Query query = session.createQuery("FROM Videojuego videojuegos");
//		List<Videojuego> lista = query.list();
//		session.close();
//		return lista;-----------------------------------------------------------------------------------------------*/
//		
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
//		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v JOIN FETCH v.proveedor",Videojuego.class);
//													//el fetch es para respetar la integridad referencial
//													//"proveedor" es el nombre del proxy en la linea 44
//		
//		List<Videojuego> lista = null;
//		try {
//			lista = query.getResultList();
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			em.close();
//		}
//		
//		return lista.stream() //este stream es para ordenarlos por id porque jpa los trae desordenados
//				.sorted(Comparator.comparingInt(Videojuego::getCve_vid))
//				.collect(Collectors.toList());
//	}
//	
//	
//	public Videojuego seleccionarVideojuego(int cve) throws DataBaseException
//	{
//		/* UTILIZANDO JDBC
//		String query = "SELECT * FROM videojuegos WHERE cve_vid="+cve;
//		DataBaseHelper dbh = new DataBaseHelper();
//		List<Videojuego> lista = dbh.seleccionarBean(query);
//		//ESTO NOS PUEDE DAR UN UNCHECKED EXCEPTION
//		return lista.get(0);*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS;
//		Session session = null;
//		Videojuego V = null;
//		try {
//			factoriaS = HibernateHelper.getSessionFactory();
//			session = factoriaS.openSession();
//			
//			//la cve LO DESIGNAMOS EN Videojuego.xml PARA QUE FUERA EL CAMPO cve_vid
//			V = (Videojuego) session.get(Videojuego.class, cve);
//		} catch (HibernateException e) 
//		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
//			System.out.println(e.getMessage());
//		}finally
//		{
//			session.close();
//		}
//		return V;--------------------------------------------------------------------------------------------------*/
//		
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();
//		Videojuego V = em.find(Videojuego.class,cve);
//		em.close();
//		//emf.close();
//		return V;
//	}
//	
//	public List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException
//	{//cveprov es la clave del proveedor por la cual se va a filtrar
//		/* UTILIZANDO JDBC
//		String query = "SELECT * FROM videojuegos WHERE cveprov_vid="+cveprov;
//		DataBaseHelper dbh = new DataBaseHelper();
//		List<Videojuego> lista = dbh.seleccionarBean(query);
//		return lista;*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS;
//		Session session = null;
//		List<Videojuego> lista = null;
//		try {
//			factoriaS = HibernateHelper.getSessionFactory();
//			session = factoriaS.openSession();
//			//		   								  tipo      tabla en MySQL
//			Query query = session.createQuery("from Videojuego videojuegos WHERE videojuegos.cveprov_vid = "+cveprov);
//			lista = query.list();
//			
//		} catch (HibernateException e) 
//		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
//			System.out.println(e.getMessage());
//		}finally
//		{
//			session.close();
//		}
//		return lista;-------------------------------------------------------------------------------------------*/
//		
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
//		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.cveprov_vid ="+cveprov, Videojuego.class);
//		List<Videojuego> lista = query.getResultList();
//		//emf.close();
//		em.close();
//		return lista;
//	}
//	
//	public void borrarVideojuego(int cve) throws DataBaseException
//	{//cveprov es la clave del proveedor por la cual se va a filtrar
//		/* UTILIZANDO JDBC
//		String query = "DELETE FROM videojuegos WHERE cve_vid="+cve;
//		DataBaseHelper dbh = new DataBaseHelper();
//		dbh.modificarBean(query);*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS;
//		Session session = null;
//		Transaction transaction = null;
//		try {
//			factoriaS = HibernateHelper.getSessionFactory();
//			session = factoriaS.openSession();
//			transaction = session.beginTransaction();
//			Videojuego V = (Videojuego) session.get(Videojuego.class, cve);
//			session.remove(V);
//			transaction.commit();
//			
//		} catch (HibernateException e) 
//		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
//			System.out.println(e.getMessage());
//			//regresar a como estaba
//			transaction.rollback();
//		}finally
//		{
//			session.close();
//		}
//		--------------------------------------------------------------------------------------------------------*/
//		
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		try {
//			Videojuego V = em.find(Videojuego.class,cve);
//			em.remove(V);
//		} catch (HibernateException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		em.getTransaction().commit();
//		em.close();
//		//emf.close();
//	}
//	
//	public void actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
//	{
//		/* UTILIZANDO JDBC
//		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
//				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
//		
//		DataBaseHelper dbh = new DataBaseHelper();
//		int n =dbh.modificarBean(query);
//		return n;*/
//		
//		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
//		SessionFactory factoriaS;
//		Session session = null;
//		Transaction transaction = null;
//		try {
//			factoriaS = HibernateHelper.getSessionFactory();
//			session = factoriaS.openSession();
//			transaction = session.beginTransaction();
//			Videojuego V = new Videojuego(cve, titulo, precio, cveprov, inventario);
//			session.update(V);
//			transaction.commit();
//		} catch (HibernateException e) 
//		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
//			System.out.println(e.getMessage());
//			//regresar a como estaba
//			transaction.rollback();
//		}finally
//		{
//			session.close();
//		}
//		--------------------------------------------------------------------------------------------------------*/
//		
//		//UTILIZANDO JPA
//		EntityManagerFactory emf = JPAHelper.getJPAFactory();
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		
//		try {
//			Videojuego juegoEnBD = em.find(Videojuego.class,cve);
//			
//			juegoEnBD.setTit_vid(titulo);
//			juegoEnBD.setPre_vid(precio);
//			juegoEnBD.setCveprov_vid(cveprov);
//			juegoEnBD.setInv_vid(inventario);
//			
//		} catch (HibernateException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		//No es necesario llamar explícitamente al método persist() 
//		//para actualizar una entidad en JPA, ya que los cambios realizados 
//		//en la entidad dentro del contexto de persistencia serán rastreados automáticamente
//		
//		//em.merge(juego);
//		em.getTransaction().commit();
//		em.close();
//		//emf.close();
//	}
}

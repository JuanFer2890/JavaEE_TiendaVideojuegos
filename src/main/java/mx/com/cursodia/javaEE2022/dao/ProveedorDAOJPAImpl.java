package mx.com.cursodia.javaEE2022.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

public class ProveedorDAOJPAImpl implements ProveedorDAO
{

	public void insertar(int cve, String nom, String email, String tel) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "INSERT INTO proveedores (cve_prov, nom_prov, email_prov, tel_prov) VALUES ";
		query += "("+cve+",'"+nom+"','"+email+"','"+tel+"')";
		
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
		SessionFactory factoriaS;
		Session session = null;
		Transaction transaction = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			transaction = session.beginTransaction();
			Proveedor V = new Proveedor(cve, nom, email, tel);
			
			session.persist(V);
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
		--------------------------------------------------------------------------------------------------------*/
		
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(new Proveedor(cve, nom, email, tel));
		em.getTransaction().commit();
		em.close();
		//emf.close();
	}
	
	public List<Proveedor> buscarTodos() throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM proveedores";
		DataBaseHelper dbh = new DataBaseHelper();
		return dbh.seleccionarBean(query);*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
		SessionFactory factoriaS = HibernateHelper.getSessionFactory();
		Session session = factoriaS.openSession();
		Query query = session.createQuery("FROM Proveedor proveedores");
		List<Proveedor> lista = query.list();
		session.close();
		return lista;
		--------------------------------------------------------------------------------------------------------*/
		
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p",Proveedor.class);
		//en esta query no se agrega un Fetch porque de proveedor no hay una referencia a videojuego
		List<Proveedor> lista = query.getResultList();
		//emf.close();
		em.close();
		return lista;
	}
}

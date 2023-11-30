package mx.com.cursodia.javaEE2022.Beans;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

@Entity
@Table(name="videojuegos") //nombre en SQL
public class Videojuego 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_vid") //en SQL
	private int cve_vid;
	private String tit_vid;
	private float pre_vid;
	private int cveprov_vid;
	private int inv_vid;
	
	//ESTO ES UN PROXY----------------------------------------------------------------------
	@ManyToOne											//si me permite crear un nuevo proveedor desde aqui
	@JoinColumn(name = "cveprov_vid", referencedColumnName="cve_prov", insertable=false, updatable = false, nullable = false)
	private Proveedor proveedor;
	//ESTO ES PARA QUE HIBERNATE AUTOMATICAMENTE INYECTE SUS INSTANCIAS DE PROVEEDOR---------
	public Proveedor getProveedor()
	{
		return this.proveedor;
	}
	public void setProveedor(Proveedor proveedor)
	{
		this.proveedor = proveedor;
	}
	//--------------------------------------------------------------------------------------
	
	public Videojuego()
	{
	}
	
	public Videojuego(int cve_vid, String tit_vid, float pre_vid, int cvepro_vid, int inv_vid) 
	{
		this.cve_vid = cve_vid;
		this.tit_vid = tit_vid;
		this.pre_vid = pre_vid;
		this.cveprov_vid = cvepro_vid;
		this.inv_vid = inv_vid;
	}
	public void setCve_vid(int cve_vid) 
	{
		this.cve_vid = cve_vid;
	}

	public String getTit_vid() 
	{
		return tit_vid;
	}
	public void setTit_vid(String tit_vid) 
	{
		this.tit_vid = tit_vid;
	}
	public float getPre_vid() 
	{
		return pre_vid;
	}
	public void setPre_vid(float pre_vid) 
	{
		this.pre_vid = pre_vid;
	}
	public int getCveprov_vid()
	{
		return cveprov_vid;
	}
	
	public void setCveprov_vid(int cvepro_vid) 
	{
		this.cveprov_vid = cvepro_vid;
	}
	public int getInv_vid() 
	{
		return inv_vid;
	}
	public void setInv_vid(int inv_vid) 
	{
		this.inv_vid = inv_vid;
	}
	public int getCve_vid() 
	{
		return cve_vid;
	}
	
	@Override
	public String toString() {
		return "Videojuego [cve_vid=" + cve_vid + ", tit_vid=" + tit_vid + ", pre_vid=" + pre_vid + ", cvepro_vid="
				+ cveprov_vid + ", inv_vid=" + inv_vid + "]";
	}
	
	public static void insertar(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "INSERT INTO videojuegos (cve_vid, tit_vid, pre_vid, cveprov_vid, inv_vid) VALUES ";
		query += "("+cve+",'"+titulo+"',"+precio+","+cveprov+","+inventario+")";
		
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
			Videojuego V = new Videojuego(cve, titulo, precio, cveprov, inventario);
			
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
		}--------------------------------------------------------------------------------------------------------*/
	
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(new Videojuego(cve,titulo,precio,cveprov,inventario));
		em.getTransaction().commit();
		em.close();
		//emf.close();
		
		
	}
	
	public static List<Videojuego> buscarTodos() throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos";
		DataBaseHelper dbh = new DataBaseHelper();
		return dbh.seleccionarBean(query);*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
		SessionFactory factoriaS = HibernateHelper.getSessionFactory();
		Session session = factoriaS.openSession();
		Query query = session.createQuery("FROM Videojuego videojuegos");
		List<Videojuego> lista = query.list();
		session.close();
		return lista;-----------------------------------------------------------------------------------------------*/
		
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v JOIN FETCH v.proveedor",Videojuego.class);
		List<Videojuego> lista = query.getResultList();//el fetch es para respetar la integridad referencial
		em.close();									   //"proveedor" es el nombre del proxy en la linea 44
		
		return lista.stream() //este stream es para ordenarlos por id porque jpa los trae desordenados
				.sorted(Comparator.comparingInt(Videojuego::getCve_vid))
				.collect(Collectors.toList());
	}
	
	
	public static Videojuego seleccionarVideojuego(int cve) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		//ESTO NOS PUEDE DAR UN UNCHECKED EXCEPTION
		return lista.get(0);*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
		SessionFactory factoriaS;
		Session session = null;
		Videojuego V = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			
			//la cve LO DESIGNAMOS EN Videojuego.xml PARA QUE FUERA EL CAMPO cve_vid
			V = (Videojuego) session.get(Videojuego.class, cve);
		} catch (HibernateException e) 
		{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
			System.out.println(e.getMessage());
		}finally
		{
			session.close();
		}
		return V;--------------------------------------------------------------------------------------------------*/
		
		//UTILIZANDO JPA
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		Videojuego V = em.find(Videojuego.class,cve);
		em.close();
		//emf.close();
		return V;
	}
	
	public static List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException
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
	
	public static void borrarVideojuego(int cve) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		/* UTILIZANDO JDBC
		String query = "DELETE FROM videojuegos WHERE cve_vid="+cve;
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
			Videojuego V = (Videojuego) session.get(Videojuego.class, cve);
			session.remove(V);
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
		try {
			Videojuego V = em.find(Videojuego.class,cve);
			em.remove(V);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		em.getTransaction().commit();
		em.close();
		//emf.close();
	}
	
	public static void actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
		
		DataBaseHelper dbh = new DataBaseHelper();
		int n =dbh.modificarBean(query);
		return n;*/
		
		/*UTILIZANDO HIBERNATE-------------------------------------------------------------------------------------
		SessionFactory factoriaS;
		Session session = null;
		Transaction transaction = null;
		try {
			factoriaS = HibernateHelper.getSessionFactory();
			session = factoriaS.openSession();
			transaction = session.beginTransaction();
			Videojuego V = new Videojuego(cve, titulo, precio, cveprov, inventario);
			session.update(V);
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
		
		
		try {
			Videojuego juegoEnBD = em.find(Videojuego.class,cve);
			
			juegoEnBD.setTit_vid(titulo);
			juegoEnBD.setPre_vid(precio);
			juegoEnBD.setCveprov_vid(cveprov);
			juegoEnBD.setInv_vid(inventario);
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		//No es necesario llamar explícitamente al método persist() 
		//para actualizar una entidad en JPA, ya que los cambios realizados 
		//en la entidad dentro del contexto de persistencia serán rastreados automáticamente
		
		//em.merge(juego);
		em.getTransaction().commit();
		em.close();
		//emf.close();
	}
	
}

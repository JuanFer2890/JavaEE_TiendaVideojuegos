package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

@Entity
@Table(name="videojuegos")
public class Videojuego 
{
	@Id
	private int cve_vid;
	private String tit_vid;
	private float pre_vid;
	private int cveprov_vid;
	private int inv_vid;
	
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
		
		//UTILIZANDO HIBERNATE
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
		}
	
		
		
	}
	
	public static List<Videojuego> buscarTodos() throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos";
		DataBaseHelper dbh = new DataBaseHelper();
		return dbh.seleccionarBean(query);*/
		
		//UTILIZANDO HIBERNATE
		SessionFactory factoriaS = HibernateHelper.getSessionFactory();
		Session session = factoriaS.openSession();
		Query query = session.createQuery("FROM Videojuego videojuegos");
		List<Videojuego> lista = query.list();
		session.close();
		return lista;
	}
	
	
	public static Videojuego seleccionarVideojuego(int cve) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		//ESTO NOS PUEDE DAR UN UNCHECKED EXCEPTION
		return lista.get(0);*/
		
		//UTILIZANDO HIBERNATE
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
		return V;
	}
	
	public static List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		/* UTILIZANDO JDBC
		String query = "SELECT * FROM videojuegos WHERE cveprov_vid="+cveprov;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		return lista;*/
		
		//UTILIZANDO HIBERNATE
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
		return lista;
	}
	
	public static void borrarVideojuego(int cve) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		/* UTILIZANDO JDBC
		String query = "DELETE FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);*/
		
		//UTILIZANDO HIBERNATE
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
	}
	
	public static void actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		/* UTILIZANDO JDBC
		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
		
		DataBaseHelper dbh = new DataBaseHelper();
		int n =dbh.modificarBean(query);
		return n;*/
		
		//UTILIZANDO HIBERNATE
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
	}
	
}

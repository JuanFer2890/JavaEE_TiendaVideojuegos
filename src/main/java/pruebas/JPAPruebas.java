package pruebas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

public class JPAPruebas {
	
	public static void main(String[] args) 
	{
		JPAPruebas pruebas = new JPAPruebas();
		//pruebas.insertar(new Videojuego(3, "Katana Zero", 250, 1, 26));
		//pruebas.actualizar(new Videojuego(1,"Mario Wonder", 1000,1,130));
		//pruebas.borrarRegistro(412);
		//System.out.println(pruebas.buscarVideojuegos());
		System.out.println(pruebas.obtenerUnRegistro(1));
	}
	
	private List<Videojuego> buscarVideojuegos()
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v",Videojuego.class);
		//SELECT v FROM Videojuego v WHERE v.nombre = 'Super Mario'
		List<Videojuego> lista = query.getResultList();
		emf.close();
		em.close();
		return lista;
	}
	
	private void insertar(Videojuego V)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(V);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	private void actualizar(Videojuego V)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Videojuego juegoEnBD = em.find(Videojuego.class,V.getCve_vid());
		
		juegoEnBD.setTit_vid(V.getTit_vid());
		juegoEnBD.setPre_vid(V.getPre_vid());
		juegoEnBD.setCveprov_vid(V.getCveprov_vid());
		juegoEnBD.setInv_vid(V.getInv_vid());
		
		//No es necesario llamar explícitamente al método persist() 
		//para actualizar una entidad en JPA, ya que los cambios realizados 
		//en la entidad dentro del contexto de persistencia serán rastreados automáticamente
		
		//em.merge(juego);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	private Videojuego obtenerUnRegistro(int cve)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		
		//em.getTransaction().begin();
		Videojuego V = em.find(Videojuego.class,cve);
		//System.out.println(V.getTit_vid());
		//Finaliza la transacción mediante el método commit() del EntityManager
		//em.getTransaction().commit();
		em.close();
		emf.close();
		return V;
		//System.out.println(V.getTit_vid());
	}
	
	private void borrarRegistro(int cve)
	{
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Videojuego V = em.find(Videojuego.class,cve);

		em.remove(V);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		//System.out.println(V.getTit_vid());
	}

}

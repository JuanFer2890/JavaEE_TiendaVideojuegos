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
		
		EntityManagerFactory emf = JPAHelper.getJPAFactory();
		EntityManager em = emf.createEntityManager();//"v" es un alias que se utiliza para referenciar la entidad Videojuego
		TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.cveprov_vid ="+cveprov, Videojuego.class);
		List<Videojuego> lista = query.getResultList();
		//emf.close();
		em.close();
		return lista;
	}
}

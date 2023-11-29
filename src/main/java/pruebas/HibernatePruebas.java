package pruebas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;

public class HibernatePruebas {


		//importar todo desde Hibernate
		Session session = null;
		Transaction transaction = null;
		
		public static void main(String[] args) 
		{
			HibernatePruebas hp = new HibernatePruebas();
			//hp.mostrarVideojuegos();
			//hp.seleccionarVideojuego(13);
			//hp.borrarVideojuego(8);
			hp.filtrarVideojuego(2);
			//hp.nuevoVideojuego();
		}
		
		private void nuevoVideojuego()
		{
			
			try {
				//						para que cargue la configuracion que escribimos
				SessionFactory factoria = new Configuration().configure().buildSessionFactory();
				session = factoria.openSession();
				transaction = session.beginTransaction();
				
				//se le pasan los parametros
				Videojuego V = new Videojuego(6, "Super Hot 2", 300.5f, 2, 18);
				
				//Si el objeto no existe lo guarda y si ya existe lo actualiza
				//session.saveOrUpdate(v); //ESTE DE DEPRECATED
				
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
		
		private void mostrarVideojuegos()
		{
			try {
				//						para que cargue la configuracion que escribimos
				SessionFactory factoria = new Configuration().configure().buildSessionFactory();
				session = factoria.openSession();
				//											   	 tabla en MySQL
				Query query = session.createQuery("from Videojuego videojuegos");
				List<Videojuego> lista = query.list();
				
				for(Videojuego V:lista)
				{
					System.out.println(V);
				  /*System.out.print(V.getCve_vid()+"\t\t");
					System.out.print(V.getTit_vid()+"\t\t");
					System.out.print(V.getPre_vid()+"\t\t");
					System.out.print(V.getCveprov_vid()+"\t\t");
					System.out.println(V.getInv_vid()+"\t\t");*/
				}
				
			} catch (HibernateException e) 
			{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
				System.out.println(e.getMessage()); 
				//AQUI NO SE OCUPA UN ROLLBACK PORQUE NO HUBO UNA TRANSACCION, SOLAMENTE SE LEYO Y SE CERRO LA SESION
			}finally
			{
				session.close();
			}
		}
		
		private void seleccionarVideojuego(int id)
		{
			try {
				//						para que cargue la configuracion que escribimos
				SessionFactory factoria = new Configuration().configure().buildSessionFactory();
				session = factoria.openSession();
				
				//EL ID LO DESIGNAMOS EN Videojuego.xml PARA QUE FUERA EL CAMPO cve_vid
				Videojuego V = (Videojuego) session.get(Videojuego.class, id);
				System.out.println(V);/*
				System.out.print(V.getCve_vid()+"\t\t");
				System.out.print(V.getTit_vid()+"\t\t");
				System.out.print(V.getPre_vid()+"\t\t");
				System.out.print(V.getCveprov_vid()+"\t\t");
				System.out.println(V.getInv_vid()+"\t\t");*/
				
			} catch (HibernateException e) 
			{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
				System.out.println(e.getMessage());
			}finally
			{
				session.close();
			}
		}
		
		private void borrarVideojuego(int id)
		{
			try {
				//						para que cargue la configuracion que escribimos
				SessionFactory factoria = new Configuration().configure().buildSessionFactory();
				session = factoria.openSession();
				transaction = session.beginTransaction();
				
				Videojuego V = (Videojuego) session.get(Videojuego.class, id);
				
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

		private void filtrarVideojuego(int cveProv)
		{
			try {
				//						para que cargue la configuracion que escribimos
				SessionFactory factoria = new Configuration().configure().buildSessionFactory();
				session = factoria.openSession();
				//											   	 tabla en MySQL
				Query query = session.createQuery("from Videojuego videojuegos WHERE videojuegos.cveprov_vid = "+cveProv);
				List<Videojuego> lista = query.list();
				
				for(Videojuego V:lista)
				{
					System.out.println(V);/*
					System.out.print(V.getCve_vid()+"\t\t");
					System.out.print(V.getTit_vid()+"\t\t");
					System.out.print(V.getPre_vid()+"\t\t");
					System.out.print(V.getCveprov_vid()+"\t\t");
					System.out.println(V.getInv_vid()+"\t\t");*/
				}
				
			} catch (HibernateException e) 
			{//NO NOS PIDIO UN TRY CATCH PERO ES NECESARIO PARA PODER DEBUGGEAR
				System.out.println(e.getMessage()); 
				//AQUI NO SE OCUPA UN ROLLBACK PORQUE NO HUBO UNA TRANSACCION, SOLAMENTE SE LEYO Y SE CERRO LA SESION
			}finally
			{
				session.close();
			}
		}
	}
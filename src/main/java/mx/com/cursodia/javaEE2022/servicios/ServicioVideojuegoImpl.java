package mx.com.cursodia.javaEE2022.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.ProveedorDAO;
import mx.com.cursodia.javaEE2022.dao.VideojuegoDAO;
import mx.com.cursodia.javaEE2022.repositorios.RepositorioVideojuego;

public class ServicioVideojuegoImpl implements ServicioVideojuego
{
	private VideojuegoDAO videojuegoDAO = null;
	private ProveedorDAO proveedorDAO = null;

	@Autowired //el framework lo va a injectar automaticamente cuando sea necesario nosotros no le vamos a 
	//dar valor nunca, sino que el mismo framework ya sabe que debe de tener control de este objeto y crear la instancia
	private RepositorioVideojuego repositorio;
	
	public ServicioVideojuegoImpl()
	{
		ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		videojuegoDAO = (VideojuegoDAO) factoria.getBean("VideojuegoDAO");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarObjeto(Videojuego videojuego) 
	{
		videojuegoDAO.insertarObjeto(videojuego);
	}

	@Override
	@Transactional(readOnly=true) //le indica al framework spring que una vez incertado, necesita propagar los cambios a travez de 
	//la persistencia y la base de datos. Al ser transactional, quiere decir que el cambio va a ser a la BD
	//En este caso, al ser readOnly, le indica que solamente va a ser lectura
	public List<Videojuego> buscarTodos() 
	{
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public void actualizarObjeto(Videojuego videojuego) 
	{
		videojuegoDAO.actualizarObjeto(videojuego);
	}

	@Override
	@Transactional
	public void borrarObjeto(Videojuego videojuego) 
	{
		videojuegoDAO.borrarObjeto(videojuego);
	}

	@Override
	@Transactional(readOnly=true)
	public Videojuego seleccionarObjeto(Integer cve) 
	{
		return videojuegoDAO.seleccionarObjeto(cve);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException 
	{
		return videojuegoDAO.filtrarPorProveedor(cveprov);
	}

	@Override
	@Transactional
	public VideojuegoDAO getVideojuegoDAO() 
	{
		return videojuegoDAO;
	}

	@Override
	@Transactional
	public void setVideojuegoDAO(VideojuegoDAO videojuegoDAO) 
	{
		this.videojuegoDAO = videojuegoDAO;
	}
	
}

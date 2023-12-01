package mx.com.cursodia.javaEE2022.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

public interface ProveedorDAO 
{
	public void insertar(int cve, String nom, String email, String tel) throws DataBaseException;
	
	public List<Proveedor> buscarTodos() throws DataBaseException;
}

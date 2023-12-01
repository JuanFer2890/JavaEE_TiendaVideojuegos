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

public interface VideojuegoDAO 
{
	public abstract void insertar(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException;

	public abstract List<Videojuego> buscarTodos() throws DataBaseException;

	public abstract Videojuego seleccionarVideojuego(int cve) throws DataBaseException;

	public abstract List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException;

	public abstract void borrarVideojuego(int cve) throws DataBaseException;

	public abstract void actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException;
}

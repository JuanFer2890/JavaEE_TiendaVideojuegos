package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.query.Query;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;
import mx.com.cursodia.javaEE2022.DataBaseH.JPAHelper;

@Entity
@Table(name="proveedores")
public class Proveedor
{
	@Id
	@PrimaryKeyJoinColumn(name = "cve_prov")
	private int cve_prov;
	private String nom_prov;
	private String email_prov;
	private String tel_prov;
	
	//ESTO ES UN PROXI----------------------------------------------------------------------
	@OneToMany //tipo de relacion
	@Fetch(value = FetchMode.SELECT)//como se van a ir leyendo cada uno de los registros (agarrarlos uno a uno)
	@JoinColumn(name = "cve_prov")
	private List<Videojuego> listaVideojuegos;
	//--------------------------------------------------------------------------------------
	
	public Proveedor()
	{
		
	}

	public Proveedor(int cve_prov, String nom_prov, String email_prov, String tel_prov) 
	{
		this.cve_prov = cve_prov;
		this.nom_prov = nom_prov;
		this.email_prov = email_prov;
		this.tel_prov = tel_prov;
	}

	public String getNom_prov() 
	{
		return nom_prov;
	}

	public void setNom_prov(String nom_prov) 
	{
		this.nom_prov = nom_prov;
	}
	
	public String getEmail_prov() 
	{
		return email_prov;
	}

	public void setEmail_prov(String email_prov) 
	{
		this.email_prov = email_prov;
	}

	public String getTel_prov()
	{
		return tel_prov;
	}

	public void setTel_prov(String tel_prov) 
	{
		this.tel_prov = tel_prov;
	}

	public int getCve_prov() 
	{
		return cve_prov;
	}

	@Override
	public String toString() {
		return "Proveedor [cve_prov=" + cve_prov + ", nom_prov=" + nom_prov + ", email_prov=" + email_prov
				+ ", tel_prov=" + tel_prov + "]";
	}

	//CHECAR POR QUE ES NECESARIO ESTE METODO-------------------------------------------------
	/*public static List<Proveedor> buscarTodosLosProveedores() throws DataBaseException
	{
		String query = "SELECT DISTINCT cveprov_vid FROM proveedores";
		DataBaseHelper dbh = new DataBaseHelper(); 
		return dbh.seleccionarBean(query);
	}*/
	//----------------------------------------------------------------------------------------
	
}

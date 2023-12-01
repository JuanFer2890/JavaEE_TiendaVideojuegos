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
import javax.persistence.PersistenceException;
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
	private Integer cve_vid;
	private String tit_vid;
	private float pre_vid;
	private Integer cveprov_vid;
	private Integer inv_vid;
	
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
	
	public Videojuego(Integer cve_vid, String tit_vid, float pre_vid, Integer cvepro_vid, Integer inv_vid) 
	{
		this.cve_vid = cve_vid;
		this.tit_vid = tit_vid;
		this.pre_vid = pre_vid;
		this.cveprov_vid = cvepro_vid;
		this.inv_vid = inv_vid;
	}
	public void setCve_vid(Integer cve_vid) 
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
	public Integer getCveprov_vid()
	{
		return cveprov_vid;
	}
	
	public void setCveprov_vid(Integer cvepro_vid) 
	{
		this.cveprov_vid = cvepro_vid;
	}
	public Integer getInv_vid() 
	{
		return inv_vid;
	}
	public void setInv_vid(Integer inv_vid) 
	{
		this.inv_vid = inv_vid;
	}
	public Integer getCve_vid() 
	{
		return cve_vid;
	}
	
	@Override
	public String toString() {
		return "Videojuego [cve_vid=" + cve_vid + ", tit_vid=" + tit_vid + ", pre_vid=" + pre_vid + ", cvepro_vid="
				+ cveprov_vid + ", inv_vid=" + inv_vid + "]";
	}
	
	
}

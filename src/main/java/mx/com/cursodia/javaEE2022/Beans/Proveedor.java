package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;

public class Proveedor 
{
	private int cve_prov;
	private String nom_prov;
	private String email_prov;
	private String tel_prov;
	
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

	public static List<Proveedor> buscarTodosLosProveedores() throws DataBaseException
	{
		String query = "SELECT DISTINCT cveprov_vid FROM proveedores";
		DataBaseHelper dbh = new DataBaseHelper(); 
		return dbh.seleccionarBean(query);
	}
	
	public static void insertar(int cve, String nom, String email, String tel) throws DataBaseException
	{
		String query = "INSERT INTO proveedores (cve_prov, nom_prov, email_prov, tel_prov) VALUES ";
		query += "("+cve+",'"+nom+"','"+email+"','"+tel+"')";
		
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);
		
	}
	
	public static List<Proveedor> buscarTodos() throws DataBaseException
	{
		String query = "SELECT * FROM proveedores";
		DataBaseHelper dbh = new DataBaseHelper();
		return dbh.seleccionarBean(query);
	}
	
	public static Videojuego seleccionarVideojuego(int cve) throws DataBaseException
	{
		String query = "SELECT * FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		//ESTO NOS PUEDE DAR UN UNCHECKED EXCEPTION
		return lista.get(0);
	}
	
	public int actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
		
		DataBaseHelper dbh = new DataBaseHelper();
		
		int n =dbh.modificarBean(query);
		
		return n;
	}
	
}

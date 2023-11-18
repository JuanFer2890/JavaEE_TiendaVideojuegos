package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseHelper;

public class Videojuego 
{
	private int cve_vid;
	private String tit_vid;
	private float pre_vid;
	private int cvepro_vid;
	private int inv_vid;
	
	public Videojuego()
	{
	}
	
	public Videojuego(int cve_vid, String tit_vid, float pre_vid, int cvepro_vid, int inv_vid) 
	{
		this.cve_vid = cve_vid;
		this.tit_vid = tit_vid;
		this.pre_vid = pre_vid;
		this.cvepro_vid = cvepro_vid;
		this.inv_vid = inv_vid;
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
	public int getCvepro_vid() 
	{
		return cvepro_vid;
	}
	public void setCvepro_vid(int cvepro_vid) 
	{
		this.cvepro_vid = cvepro_vid;
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
				+ cvepro_vid + ", inv_vid=" + inv_vid + "]";
	}

	public static List<Videojuego> buscarTodosLosProveedores() throws DataBaseException
	{
		String query = "SELECT DISTINCT cveprov_vid FROM videojuegos";
		DataBaseHelper dbh = new DataBaseHelper(); 
		return dbh.seleccionarBean(query);
	}
	
	public static void insertar(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		String query = "INSERT INTO videojuegos (cve_vid, tit_vid, pre_vid, cveprov_vid, inv_vid) VALUES ";
		query += "("+cve+",'"+titulo+"',"+precio+","+cveprov+","+inventario+")";
		
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);
		
	}
	
	public static List<Videojuego> buscarTodos() throws DataBaseException
	{
		String query = "SELECT * FROM videojuegos";
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
	
	public static List<Videojuego> filtrarPorProveedor(int cveprov) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		String query = "SELECT * FROM videojuegos WHERE cveprov_vid="+cveprov;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Videojuego> lista = dbh.seleccionarBean(query);
		return lista;
	}
	
	public static void borrarVideojuego(int cve) throws DataBaseException
	{//cveprov es la clave del proveedor por la cual se va a filtrar
		String query = "DELETE FROM videojuegos WHERE cve_vid="+cve;
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.modificarBean(query);
	}
	
	public static int actualizarVideoJuego(int cve, String titulo, float precio, int cveprov, int inventario) throws DataBaseException
	{
		String query = "UPDATE videojuegos SET tit_vid ='"+titulo+"',pre_vid="+precio+","
				+ "cveprov_vid="+cveprov+",inv_vid="+inventario+" WHERE cve_vid ="+cve;
		
		DataBaseHelper dbh = new DataBaseHelper();
		
		int n =dbh.modificarBean(query);
		
		return n;
	}
	
}

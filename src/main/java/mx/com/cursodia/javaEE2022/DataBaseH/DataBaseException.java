package mx.com.cursodia.javaEE2022.DataBaseH;

public class DataBaseException extends Exception
{
	private static final long serialVersionUID = 1L;

	Exception cause;
	public DataBaseException() 
	{
		//asi se invoca el constructor de la clase padre
		super();
	}
	
	public DataBaseException(String message, Throwable cause)
	{
		super(message, cause);
		this.cause = (Exception) cause;
	}
	
	//este ya no se ocupa porque simplificamos el InsertarVideojuego.jsp
	public Exception getException()
	{
		return this.cause;
	}
	
	public DataBaseException(String message)
	{
		super(message);
	}
	
	public DataBaseException(Throwable cause)
	{
		super(cause);
	}
}

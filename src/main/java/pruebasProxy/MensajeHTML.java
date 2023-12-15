package pruebasProxy;

public class MensajeHTML implements Mensaje
{

	@Override
	public void hola() 
	{
		System.out.println("<html>Hola mundo</html>");
	}
	
}

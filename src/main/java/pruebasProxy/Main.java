package pruebasProxy;

public class Main 
{
	public static void main(String[] args) 
	{
		MensajeFactory msgFactory = new MensajeFactory();
		Mensaje msg = msgFactory.getMensaje();
		
		msg.hola();
		System.out.println();
		
		MensajeProxy msgProxy = new MensajeProxy();
		msgProxy.hola();
	}
}

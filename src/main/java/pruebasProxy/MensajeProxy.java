package pruebasProxy;

public class MensajeProxy implements Mensaje
{
	private Mensaje msg;
	
	public Mensaje getMensaje()
	{
		return msg;
	}

	//CONSTRUCTOR
	public MensajeProxy()
	{
		this.msg = MensajeFactory.getMensaje();
	}
	
	@Override
	public void hola() 
	{
		System.out.println("Funcionalidad extra proxy");
		msg.hola();
		System.out.println("Funcionalidad extra proxy");
	}

}

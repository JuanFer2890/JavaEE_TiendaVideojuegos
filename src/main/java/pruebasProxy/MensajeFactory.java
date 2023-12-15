package pruebasProxy;

public class MensajeFactory 
{
	public static Mensaje getMensaje()
	{
		return new MensajeHTML();
	}
	
	/*Si quisieramos añadir una nueva responsabilidad a la clase de tal forma que al invocar el metodo
	 * hola() se imprima algun mensaje extra antes y despues del html, habria que escribir (hardcode)
	 * en la clase MensajeHTML. 
	 * 
	 * Al hacer esto, la funcionalidad que añadimos se queda ligada al codigo de forma estatica y permanente.
	 * Siempre que invoquemos el metodo MensajeHTML(), se va a imprimir ese texto extra.
	 * 
	 * Si queremos que la funcionalidad añadida sea dinamica y poder decidir cuando ejecutarla o no, hay que 
	 * extraerla de la clase en la cual la acabamos de ubicar.*/
}

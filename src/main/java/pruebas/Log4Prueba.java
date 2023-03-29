package pruebas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class Log4Prueba 
{
//Esto es para testear que este funcionando bien el archivo de configuracion
	public static void main(String[] args) 
	{
		
		Logger logger = (Logger) LogManager.getRootLogger();
		logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4gj.configurationFile"));
		

	}

}

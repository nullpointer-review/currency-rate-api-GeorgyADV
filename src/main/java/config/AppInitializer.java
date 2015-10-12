package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Georgy aka ADV
 */
public class AppInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();  
        context.scan("config");  
        context.setServletContext(servletContext);    
        
        Dynamic dynamic = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));  
        dynamic.addMapping("/");  
        dynamic.setLoadOnStartup(1);  
	}

}

package config;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

/**
 * @author Georgy aka ADV
 */
@Configuration
@ComponentScan(basePackages={"rest", "soap"})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter
{
	@Bean
	public SaajSoapMessageFactory getSoapMessageFactory() 
	{
		SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
		factory.setSoapVersion(SoapVersion.SOAP_12);
		return factory;
	}
	
	@Bean
	public WebServiceTemplate getWebServiceTemplate() 
	{
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("soap.wsdl_gen");
		
		WebServiceTemplate template = new WebServiceTemplate(getSoapMessageFactory());
		template.setMarshaller(marshaller);
		template.setUnmarshaller(marshaller);
		template.setDefaultUri("http://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx?WSDL");
		return template;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		converters.add(new MappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
	
	
	
	
}

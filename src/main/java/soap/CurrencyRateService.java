package soap;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import soap.wsdl_gen.GetExchangeRate;
import soap.wsdl_gen.GetExchangeRateResponse;
import soap.wsdl_gen.ObjectFactory;

/**
 * @author Georgy aka ADV
 */
@Service
public class CurrencyRateService
{
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public BigDecimal getRate(String code, String date)
	{
		GetExchangeRate eRate = new ObjectFactory().createGetExchangeRate();
		eRate.setCurrency(code);
		eRate.setDate(date);
		GetExchangeRateResponse response = (GetExchangeRateResponse) webServiceTemplate.marshalSendAndReceive(eRate);
		
		return response.getGetExchangeRateResult();
	}
}

package rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rest.model.CurrencyRate;
import soap.CurrencyRateService;

/**
 * @author Georgy aka ADV
 */
@RestController
@RequestMapping(value="/api/rate")
public class CurrencyController
{
	private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private CurrencyRateService currencyRateService;
	
	@RequestMapping(
			method=RequestMethod.GET, 
			value={"/{code:[a-z]{3}}"}, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public CurrencyRate getCurrencyRate(@PathVariable String code) 
	{
		// we purposely delete 1 year here, i.e. next day but in 2014  
		String nextDay = dFormat.format(new Date().getTime() + TimeUnit.DAYS.toMillis(1) - TimeUnit.DAYS.toMillis(365)); 
		
		CurrencyRate cRate = new CurrencyRate();
		cRate.setCode(code);
		cRate.setRate(currencyRateService.getRate(code, nextDay));
		cRate.setDate(nextDay);
		
		return cRate;
	}
	
	@RequestMapping(
			method=RequestMethod.GET, 
			value={"/{code:[a-z]{3}}/{date:\\d{4}-\\d{2}-\\d{2}}"}, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public CurrencyRate getCurrencyRateByDate(@PathVariable String code, @PathVariable String date) 
	{
		CurrencyRate cRate = new CurrencyRate();
		cRate.setCode(code);
		cRate.setRate(currencyRateService.getRate(code, date));
		cRate.setDate(date);
		
		return cRate;
	}
}

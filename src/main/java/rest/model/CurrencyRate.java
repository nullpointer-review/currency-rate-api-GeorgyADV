package rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Georgy aka ADV
 */
public class CurrencyRate implements Serializable
{
	private String code;
	private BigDecimal rate;
	private String date;
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}

	public BigDecimal getRate()
	{
		return rate;
	}

	public void setRate(BigDecimal rate)
	{
		this.rate = rate;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}
	
	
}

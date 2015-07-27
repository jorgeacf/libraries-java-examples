package com.jorgefigueiredo.finance.data.entities;

import java.net.URI;

public class InstrumentData {

	
	private URI uri;
	private String ticker;
	private String companyName;
	private String exchangeName;
	
	private String timezone;
	private String currency;
	
	private Double previousClose;
	
	private TickerData[] data;
	
	public URI getURI() { return uri; }
	public String getTicker() { return ticker; }
	public String getCompanyName() { return companyName; }
	public String getExchangeName() { return exchangeName; }
	public String getTimeZone() { return timezone; }
	public String getCurrency() { return currency; }
	
	public Double getPreviousClose() { return previousClose; }
	
	public TickerData[] getData() { return data; }
	
	public InstrumentData(URI uri, String ticker, String companyName, String exchangeName, String timezone, String currency, Double previousClose, TickerData[] data) {
		
		this.uri = uri;
		this.ticker = ticker;
		this.companyName = companyName;
		this.exchangeName = exchangeName;
		this.timezone = timezone;
		this.currency = currency;
		this.previousClose = previousClose;
		this.data = data;
		
	}
	
}

package com.jorgefigueiredo.java.io.finance;



public class TickerData {

	private String timestamp;
	private Double close;
	private Double high;
	private Double low;
	private Double open;
	private Integer volume;
	
	public String getTimeStamp() { return timestamp; }
	public Double getClose() { return close; }
	public Double getHigh() { return high; }
	public Double getLow() { return low; }
	public Double getOpen() { return open; }
	public Integer getVolume() { return volume; }
	
	public TickerData(String timestamp, Double close, Double high, Double low, Double open, Integer volume) {
		
		this.timestamp = timestamp;
		this.close = close;
		this.high = high;
		this.low = low;
		this.open = open;
		this.volume = volume;
		
	}
	
	public TickerData(ITickerDataParser parser, String input) {
		
		TickerData tickerData = parser.parse(input);
		
		this.timestamp = tickerData.getTimeStamp();
		this.close = tickerData.getClose();
		this.high = tickerData.getHigh();
		this.low = tickerData.getLow();
		this.open = tickerData.getOpen();
		this.volume = tickerData.getVolume();
		
	}
	
}

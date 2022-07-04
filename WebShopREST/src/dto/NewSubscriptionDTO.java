package dto;

import java.util.Date;

import enums.SubscriptionType;

public class NewSubscriptionDTO {

	private SubscriptionType type;
	private Date payDay;
	private Date expirationDate;
	private double price;
	private String username;
	private long dailyAppearance;
	private boolean finite;
	
	public NewSubscriptionDTO(SubscriptionType type, Date payDay, Date expirationDate, double price,
			long dailyAppearance, boolean finite) {
		super();
		this.type = type;
		this.payDay = payDay;
		this.expirationDate = expirationDate;
		this.price = price;
		this.dailyAppearance = dailyAppearance;
		this.finite = finite;
	}
	
	public SubscriptionType getType() {
		return type;
	}
	public void setType(SubscriptionType type) {
		this.type = type;
	}
	public Date getPayDay() {
		return payDay;
	}
	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getDailyAppearance() {
		return dailyAppearance;
	}
	public void setDailyAppearance(long dailyAppearance) {
		this.dailyAppearance = dailyAppearance;
	}
	public boolean isFinite() {
		return finite;
	}
	public void setFinite(boolean finite) {
		this.finite = finite;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}

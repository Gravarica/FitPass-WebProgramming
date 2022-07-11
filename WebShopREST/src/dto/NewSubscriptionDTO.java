package dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import enums.SubscriptionType;

public class NewSubscriptionDTO {

	private SubscriptionType type;
	private double price;
	private double priceWithDiscount;
	private String username;
	private long dailyAppearance;
	private int totalAppearances;
	
	public NewSubscriptionDTO() {}
	
	public SubscriptionType getType() {
		return type;
	}
	public void setType(SubscriptionType type) {
		this.type = type;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalAppearances() {
		return totalAppearances;
	}

	public void setTotalAppearances(int totalAppearances) {
		this.totalAppearances = totalAppearances;
	}

	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}

	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}

	
	
	
	
}

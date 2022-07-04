package dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import enums.SubscriptionType;

public class NewSubscriptionDTO {

	private SubscriptionType type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime payDay;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime expirationDate;
	private double price;
	private String username;
	private long dailyAppearance;
	private boolean finite;
	private int totalAppearances;
	
	public NewSubscriptionDTO(SubscriptionType type, LocalDateTime payDay, LocalDateTime expirationDate, double price,
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
	public LocalDateTime getPayDay() {
		return payDay;
	}
	public void setPayDay(LocalDateTime payDay) {
		this.payDay = payDay;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
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

	public int getTotalAppearances() {
		return totalAppearances;
	}

	public void setTotalAppearances(int totalAppearances) {
		this.totalAppearances = totalAppearances;
	}
	
	
	
}

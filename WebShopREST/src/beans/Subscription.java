package beans;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import dto.NewSubscriptionDTO;
import enums.SubscriptionType;
import src.util.BusinessUtil;

public class Subscription extends Entity {
	
	private String code; //ograniciti na 10 karaktera
	private SubscriptionType type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime payDay;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime expirationDate;
	private double price;
	private String username;
	private boolean active;
	private long dailyAppearance;
	private int totalAppearances;
	private boolean finite;
	
	public Subscription() {}
	
	public Subscription(int id,String code, SubscriptionType type, LocalDateTime payDay, LocalDateTime expirationDate, double price, String customer,
			boolean active, long dailyAppearance, boolean finite) {
		super(id);
		this.code = code;
		this.type = type;
		this.payDay = payDay;
		this.expirationDate = expirationDate;
		this.price = price;
		this.username = customer;
		this.active = active;
		this.dailyAppearance = dailyAppearance;
		this.finite = finite;
	}
		
	public Subscription(NewSubscriptionDTO dto) {
		this.code = BusinessUtil.generateRandomString();
		this.username = dto.getUsername();
		this.payDay = LocalDateTime.now();
		this.expirationDate = BusinessUtil.concludeExpirationDate(dto.getType());
		this.price = dto.getPrice();
		this.active = true;
		this.totalAppearances = dto.getTotalAppearances();
		this.finite = false;		/// OVO SAM HARDKODOVAO SAD, IZMENITI
		this.type = dto.getType();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SubscriptionType getType() {
		return type;
	}

	public void setType(SubscriptionType type) {
		this.type = type;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public LocalDateTime getPayDay() {
		return payDay;
	}

	public void setPayDay(LocalDateTime payDay) {
		this.payDay = payDay;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationLocalDateTime(java.time.LocalDateTime expirationLocalDateTime) {
		this.expirationDate = expirationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public boolean checkForCode(String code) {
		return code.length() == 10;	
	}
	
	public boolean exists(String username) {
		return this.username.equals(username) && active;
	}
	
	public boolean isFinished() {
		return LocalDateTime.now().isAfter(expirationDate);
	}
	
	public int getTotalAppearances() {
		return totalAppearances;
	}

	public void setTotalAppearances(int totalAppearances) {
		this.totalAppearances = totalAppearances;
	}

	
}

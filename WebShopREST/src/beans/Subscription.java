package beans;

import java.util.Date;

import dto.NewSubscriptionDTO;
import enums.SubscriptionType;
import src.util.BusinessUtil;

public class Subscription extends Entity {
	
	private String code; //ograniciti na 10 karaktera
	private SubscriptionType type;
	private Date payDay;
	private Date  expirationDate;
	private double price;
	private String username;
	private boolean active;
	private long dailyAppearance;
	private boolean finite;
	
	public Subscription() {}
	
	public Subscription(int id,String code, SubscriptionType type, Date payDay, Date expirationDate, double price, String customer,
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

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
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
}

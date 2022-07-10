package beans;

import java.time.LocalDate;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dto.NewSubscriptionDTO;
import enums.SubscriptionType;
import src.util.BusinessUtil;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class Subscription extends Entity {
	
	private String code; //ograniciti na 10 karaktera
	private SubscriptionType type;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate payDay;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate expirationDate;
	private double price;
	private double priceWithDiscount;
	private String username;
	private boolean active;
	private long dailyAppearance;
	private int totalAppearances;
	private int doneTrainings;
	private boolean finite;
	
	public Subscription() {}
	
	public Subscription(int id,String code, SubscriptionType type, LocalDate payDay, LocalDate expirationDate, double price, String customer,
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
		this.payDay = LocalDate.now();
		this.expirationDate = BusinessUtil.concludeExpirationDate(dto.getType());
		this.price = dto.getPrice();
		this.priceWithDiscount = dto.getPrice();
		this.active = true;
		this.doneTrainings = 0;
		this.dailyAppearance = dto.getDailyAppearance();
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

	public LocalDate getPayDay() {
		return payDay;
	}

	public void setPayDay(LocalDate payDay) {
		this.payDay = payDay;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
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
	
	public boolean hasExpired() {
		return LocalDate.now().isAfter(expirationDate) && active;
	}
	
	public int getTotalAppearances() {
		return totalAppearances;
	}

	public void setTotalAppearances(int totalAppearances) {
		this.totalAppearances = totalAppearances;
	}

	public int getDoneTrainings() {
		return doneTrainings;
	}

	public void setDoneTrainings(int doneTrainings) {
		this.doneTrainings = doneTrainings;
	}

	public void trainingDone() {
		this.doneTrainings++;
	}

	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}

	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}
	
	
}

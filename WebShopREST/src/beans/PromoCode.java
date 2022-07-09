package beans;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dto.NewPromoCodeDTO;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class PromoCode extends Entity{

	private String code;
	private double discount;
	private int numberOfUsages;
	private boolean used;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate expirationDate;
	
	public PromoCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromoCode(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public PromoCode(String code, double discount, int numberOfUsages, LocalDate expirationDate) {
		super();
		this.code = code;
		this.discount = discount;
		this.numberOfUsages = numberOfUsages;
		this.expirationDate = expirationDate;
	}
	
	public PromoCode(NewPromoCodeDTO dto) {
		super();
		this.code = dto.getCode();
		this.discount = dto.getDiscount();
		this.expirationDate = dto.getExpirationDate();
		this.numberOfUsages = dto.getNumberOfUsages();
		this.used = false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getNumberOfUsages() {
		return numberOfUsages;
	}

	public void setNumberOfUsages(int numberOfUsages) {
		this.numberOfUsages = numberOfUsages;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public boolean exists(String code) {
		return this.code.toLowerCase().equals(code.toLowerCase());
	}
	
	public boolean hasExpired() {
		return LocalDate.now().isAfter(expirationDate);
	}
	
	public void redeemedOnce() {
		
		numberOfUsages--;
		if(numberOfUsages == 0) {
			used = true;
		}
	}
}

package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class NewPromoCodeDTO {

	private String code;
	private double discount;
	private int numberOfUsages;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate expirationDate;
	
	public NewPromoCodeDTO() {
		super();
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
	
	
	
}

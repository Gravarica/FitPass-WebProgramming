package beans;

import java.util.Date;

public class PromoCode extends Entity{

	private String code;
	private double discount;
	private int numberOfUsages;
	private boolean used;
	private Date expirationDate;
	
	public PromoCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromoCode(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public PromoCode(String code, double discount, int numberOfUsages, Date expirationDate) {
		super();
		this.code = code;
		this.discount = discount;
		this.numberOfUsages = numberOfUsages;
		this.expirationDate = expirationDate;
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	
	
}

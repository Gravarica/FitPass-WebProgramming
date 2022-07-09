package dto;

public class ReturnPromoCodeDTO {

	private double discount;
	private String message;
	
	
	
	public ReturnPromoCodeDTO(double discount, String message) {
		super();
		this.discount = discount;
		this.message = message;
	}

	public ReturnPromoCodeDTO() {
		super();
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

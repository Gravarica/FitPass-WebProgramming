package dto;

import enums.CustomerTypeName;

public class CustomerTypeDTO {

	private CustomerTypeName name;
	private double discount;
	private boolean show;
	
	public CustomerTypeDTO(CustomerTypeName name, double discount, boolean show) {
		super();
		this.name = name;
		this.discount = discount;
		this.show = show;
	}
	
	public CustomerTypeName getName() {
		return name;
	}
	
	public void setName(CustomerTypeName name) {
		this.name = name;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public boolean isShow() {
		return show;
	}
	
	public void setShow(boolean show) {
		this.show = show;
	}
	
	
}

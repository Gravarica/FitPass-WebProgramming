package beans;

public class Address extends Entity {

	private String street;
	private String number;
	private String city;
	private String postalNumber;
	
	public Address() {}
	
	public Address(int id,String street, String number, String city, String postalNumber) {
		super(id);
		this.street = street;
		this.number = number;
		this.city = city;
		this.postalNumber = postalNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalNumber() {
		return postalNumber;
	}

	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}
}

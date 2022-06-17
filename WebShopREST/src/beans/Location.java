package beans;

public class Location extends Entity {

	private Address address;
	private double longitude;
	private double latitude;
	
	public Location() {
		super();
	}

	public Location(int id,double longitude, double latitude, Address address) {
		super(id);
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}

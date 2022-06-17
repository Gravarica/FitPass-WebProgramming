package beans;

public class Comment extends Entity {

	private Customer customer;
	private SportObject object;
	private String text;
	private int grade;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(int id,Customer buyer, SportObject object, String text, int grade) {
		super(id);
		this.customer = buyer;
		this.object = object;
		this.text = text;
		this.grade = grade;
	}

	public Customer getBuyer() {
		return customer;
	}

	public void setBuyer(Customer buyer) {
		this.customer = buyer;
	}

	public SportObject getObject() {
		return object;
	}

	public void setObject(SportObject object) {
		this.object = object;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	private boolean gradeIsValid(double grade) {
		return grade >= 1 && grade <= 5;
	}
}

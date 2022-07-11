package beans;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import enums.CommentState;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class Comment extends Entity {

	private User customer;
	private SportObject object;
	private String text;
	private int grade;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate time;
	private CommentState state;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(int id,User customer, SportObject object, String text, int grade) {
		super(id);
		this.customer = customer;
		this.object = object;
		this.text = text;
		this.grade = grade;
		this.time = LocalDate.now();
		this.state = CommentState.PENDING;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
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
	
	public CommentState getState() {
		return this.state;
	}
	
	public void setState(CommentState state) {
		this.state = state;
	}
	
	private boolean gradeIsValid(double grade) {
		return grade >= 1 && grade <= 5;
	}
	
	public boolean objectMatches(SportObject obj) {
		return object.getId() == obj.getId();
	}
	
	public boolean userMatches(String username) {
		return customer.getUsername().equals(username);
	}
	
	public void approve() {
		this.state = CommentState.ACCEPTED;
	}
	
	public void decline() {
		this.state = CommentState.DENIED;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}
	
	
}

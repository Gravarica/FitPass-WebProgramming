package beans;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import enums.CommentState;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class Comment extends Entity {
	
	private String username;
	private int objectId;
	private String objectName;
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
	
	public Comment(int id,String username, int objectId , String text, int grade) {
		super(id);
		this.username = username;
		this.objectId = objectId;
		this.text = text;
		this.grade = grade;
		this.time = LocalDate.now();
		this.state = CommentState.PENDING;
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
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	private boolean gradeIsValid(double grade) {
		return grade >= 1 && grade <= 5;
	}
	
	public boolean objectMatches(SportObject obj) {
		return objectId == obj.getId();
	}
	
	public boolean userMatches(String username) {
		return username.equals(username);
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
	
	public boolean hasBeenApproved() {
		return state == CommentState.ACCEPTED;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	
	
}

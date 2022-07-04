package dto;

import enums.SportObjectContent;

public class NewContentDTO {
	
	private String name;
	private String imgUrl;
	private SportObjectContent contentType;
	private int duration;
	private String description;
	
	public NewContentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewContentDTO(String name, String imgUrl, SportObjectContent contentType, int duration, String description) {
		super();
		this.name = name;
		this.imgUrl = imgUrl;
		this.contentType = contentType;
		this.duration = duration;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public SportObjectContent getContentType() {
		return contentType;
	}
	public void setContentType(SportObjectContent contentType) {
		this.contentType = contentType;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}

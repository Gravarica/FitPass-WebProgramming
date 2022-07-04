package beans;

import dto.NewContentDTO;
import enums.SportObjectContent;

public class Content {

	private String name;
	private String imgUrl;
	private SportObjectContent contentType;
	private int duration;
	private String description;
	
	public Content() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Content(NewContentDTO dto) {
		super();
		this.name = dto.getName();
		this.imgUrl = dto.getImgUrl();
		this.contentType = dto.getContentType();
		this.duration = dto.getDuration();
		this.description = dto.getDescription();
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
	
	public void modify(NewContentDTO dto) {
		this.contentType = dto.getContentType();
		this.duration = dto.getDuration();
		this.description = dto.getDescription();
		this.imgUrl = dto.getImgUrl();
	}
}

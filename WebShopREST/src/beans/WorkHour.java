package beans;

import java.time.DayOfWeek;
import java.util.Date;

public class WorkHour {
	
	private DayOfWeek dayOfWeek;
	private Date startTime;
	private Date endTime;
	
	public WorkHour() {
		super();
	}
	
	public WorkHour(int id, DayOfWeek dayOfWeek, Date startTime, Date endTime) {
		super(id);
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}

package dto;

public class AverageGradeDTO {

	private int sum;
	private int count;
	
	public AverageGradeDTO(int sum, int count) {
		super();
		this.sum = sum;
		this.count = count;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getAverage() {
		return (double) sum/count;
	}
	
}

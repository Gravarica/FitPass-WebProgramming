package beans;

public class Entity {

	protected int id;
	protected boolean deleted = false;

	public Entity() {}
	
	public Entity(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

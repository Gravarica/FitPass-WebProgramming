package dao;

import java.util.ArrayList;
import java.util.Collection;

import beans.Entity;

public interface GenericDAO {

	public Collection<Entity> getAll();
	
	public Entity getById(int id);
	
	public Entity create(Entity entity);
	
	public Entity delete(Entity entity);
	
	public Entity update(Entity entity);
}

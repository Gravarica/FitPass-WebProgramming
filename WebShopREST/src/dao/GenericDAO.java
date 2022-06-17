package dao;

import java.util.ArrayList;

import beans.Entity;

public interface GenericDAO<TEntity> {

	public ArrayList<Entity> getAll();
	
	public Entity getById(int id);
	
	public Entity create(Entity entity);
	
	public Entity delete(Entity entity);
	
	public Entity update(Entity entity);
}

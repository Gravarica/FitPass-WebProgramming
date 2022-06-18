package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import beans.Entity;
import beans.WebContext;

public class GenericDAOImpl implements GenericDAO {

	protected HashMap<Integer, Entity> entities;
	
	public GenericDAOImpl() {
	}
	
	@Override
	public Collection<Entity> getAll() {
		return entities.values();
	}

	@Override
	public Entity getById(int id) {
		return entities.get(id);
	}

	@Override
	public Entity create(Entity entity) {
		return entities.put(entity.getId(), entity);
	}

	@Override
	public Entity delete(Entity entity) {
		return entities.remove(entity.getId());
	}

	@Override
	public Entity update(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

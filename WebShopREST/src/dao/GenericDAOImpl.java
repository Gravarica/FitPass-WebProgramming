package dao;

import java.util.ArrayList;

import beans.Entity;
import beans.WebContext;

public class GenericDAOImpl<TEntity> implements GenericDAO {

	@Override
	public ArrayList<Entity> getAll() {
		// TODO Auto-generated method stub
		return WebContext.getInstance().get(this);
	}

	@Override
	public Entity getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity create(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity delete(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity update(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

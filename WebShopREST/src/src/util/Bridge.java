package src.util;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import beans.CustomerType;
import dao.CustomerTypeDAO;
import enums.CustomerTypeName;

public class Bridge {
	
	
	public static CustomerType getCustomerType(CustomerTypeName name) {
		CustomerTypeDAO dao = new CustomerTypeDAO();
		return dao.get(name);
	}
	
	public static int getPointsNeeded(CustomerTypeName name) {
		CustomerTypeDAO dao = new CustomerTypeDAO();
		return dao.get(name).getPointsRequired();
	}
	
	public static CustomerType getBasedOnPoints(double points) {
		CustomerTypeDAO dao = new CustomerTypeDAO();
		return dao.getBasedOnPoints(points);
	}
	
}

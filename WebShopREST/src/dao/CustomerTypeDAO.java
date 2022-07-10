package dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.CustomerType;
import enums.CustomerTypeName;

public class CustomerTypeDAO {

	private File file;
	private Map<CustomerTypeName, CustomerType> customerTypes = new HashMap<>();
	
	private static final CustomerTypeName BRONZE = CustomerTypeName.BRONZE;
	private static final CustomerTypeName SILVER = CustomerTypeName.SILVER;
	private static final CustomerTypeName GOLD = CustomerTypeName.GOLD;
	private static final CustomerTypeName PLATINUM = CustomerTypeName.PLATINUM;
	
	public CustomerTypeDAO() {
		file = new File("WebShopREST\\WebContent\\Resources\\Data\\customerTypes.json");
		init();
	}
	
	private void init() {
		CustomerType ct1 = new CustomerType(BRONZE, 0.0, 500);
		CustomerType ct2 = new CustomerType(SILVER, 3.0, 1000);
		CustomerType ct3 = new CustomerType(GOLD, 7.0, 1500);
		CustomerType ct4 = new CustomerType(PLATINUM, 10.0, 7000);
		customerTypes.put(BRONZE, ct1);
		customerTypes.put(SILVER, ct2);
		customerTypes.put(GOLD, ct3);
		customerTypes.put(PLATINUM, ct4);
	}
	
	private void loadCustomerTypes() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<CustomerType> customerTypeList = mapper.readValue(file, new TypeReference<List<CustomerType>>() {});
			convertListToMap(customerTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void convertListToMap(List<CustomerType> list) {
		for(CustomerType t : list) {
			customerTypes.put(t.getName(), t);
		}
	}
	
	public CustomerType get(CustomerTypeName name) {
		return customerTypes.get(name);
	}
	
	public CustomerType getBasedOnPoints(double points) {
		if(between(BRONZE,SILVER, points)) {
			return get(SILVER);
		} else if(between(SILVER, GOLD, points)) {
			return get(GOLD);
		} else if (between(GOLD,PLATINUM,points)) {
			return get(PLATINUM);
		}
		
		return get(BRONZE);
	}
	
	private boolean between(CustomerTypeName a, CustomerTypeName b, double points) {
		return points >= customerTypes.get(a).getPointsRequired() && points <= customerTypes.get(b).getPointsRequired();
	}
}

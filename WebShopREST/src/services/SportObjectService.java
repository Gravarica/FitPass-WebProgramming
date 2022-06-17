package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.SportObject;
import dao.SportObjectDAO;

@Path("/sport_objects")
public class SportObjectService {
	
	@Context
	ServletContext ctx;

	public SportObjectService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("sportObjectDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("sportObjectDAO", new SportObjectDAO(contextPath));
		}
	}
	
	private SportObjectDAO getSportObjectDAO() {
		return (SportObjectDAO) ctx.getAttribute("sportObjectDAO");
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> getAll(){
		return getSportObjectDAO().getAll();
	}
	
}

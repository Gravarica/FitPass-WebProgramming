package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.SportObject;
import dao.SportObjectDAO;
import dto.NewContentDTO;
import dto.SportObjectSearchDTO;

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
		System.out.println("EEEEEEE BURAZERU");
		return getSportObjectDAO().getAll();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject getById(@PathParam("id") int id) {
		return getSportObjectDAO().getById(id);
	}
	
	@GET
	@Path("/search/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByName(@PathParam("name") String parameter){
		return getSportObjectDAO().search(parameter);
	}
	
	@GET
	@Path("/search/city/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByCity(@PathParam("city") String parameter){
		if(parameter.equals("none")) {return getSportObjectDAO().getAll();}
		return getSportObjectDAO().searchByCity(parameter);
	}
	
	@GET
	@Path("/search/grade/{grade}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByGrade(@PathParam("grade") double parameter){
		return getSportObjectDAO().searchByAverageGrade(parameter);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByName(SportObjectSearchDTO dto){
		return getSportObjectDAO().searchByMultipleParameters(dto);
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject delete(@PathParam("id") int id) {
		return getSportObjectDAO().delete(id);
	}
	
	@PUT
	@Path("/addContent/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject addContent(@PathParam("id") int id, NewContentDTO dto) {
		return getSportObjectDAO().addContent(dto, id);
	}
	
	@PUT
	@Path("/changeContent/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject changeContent(@PathParam("id") int id, NewContentDTO dto) {
		return getSportObjectDAO().changeContent(dto, id);
	}
	
	
}

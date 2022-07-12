package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.SportObject;
import dao.SportObjectDAO;
import dao.UserDAO;
import dto.SportObjectCreateDTO;
import dto.NewContentDTO;
import dto.SportObjectSearchDTO;
import enums.SportObjectType;

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
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> getAll(){
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
	@Path("/search/type/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByType(@PathParam("type") SportObjectType type){
		return getSportObjectDAO().searchByType(type);
	}
	
	@GET
	@Path("/search/{name}/{type}/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> searchByMultiple(@PathParam("name") String parameter, @PathParam("type") SportObjectType type, @PathParam("city") String city){
		SportObjectSearchDTO dto = new SportObjectSearchDTO(city,type,parameter);
		return getSportObjectDAO().searchByMultipleParameters(dto);
	}
  
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportObject createSportObject(SportObjectCreateDTO dto) {
		System.out.println("POZVAN SAM");
		SportObject newObject = getSportObjectDAO().createSportObject(dto);
		getUserDAO().setSportObject(newObject, dto.getManagerUsername());
		return newObject;
	}
  
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject delete(@PathParam("id") int id) {
		getUserDAO().unemployManager(id);
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

	@GET
	@Path("/types")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getSportObjectTypes(){
		System.out.println("POZVAN SAM");
		return getSportObjectDAO().getSportObjectTypes();
	}
	
	@GET
	@Path("/sort/name/{asc}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> sortByName(@PathParam("asc") boolean asc){
		return getSportObjectDAO().sortByName(asc);
	}
	
	@GET
	@Path("/sort/grade/{asc}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> sortByGrade(@PathParam("asc") boolean asc){
		return getSportObjectDAO().sortByGrade(asc);
	}
	
	@GET
	@Path("/sort/location/{asc}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> sortByLocation(@PathParam("asc") boolean asc){
		return getSportObjectDAO().sortByLocation(asc);
	}
}

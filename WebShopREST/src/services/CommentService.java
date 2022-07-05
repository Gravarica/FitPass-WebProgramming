package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Comment;
import beans.SportObject;
import dao.CommentDAO;
import dao.SportObjectDAO;
import dto.NewCommentDTO;
import enums.CommentState;

@Path("/comments")
public class CommentService {

	@Context
	ServletContext ctx;
	
	public CommentService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("commentDAO") == null) {
			ctx.setAttribute("commentDAO", new CommentDAO(ctx));
		}
	}
	
	private CommentDAO getCommentDAO() {
		return (CommentDAO) ctx.getAttribute("commentDAO");
	}
	
	private SportObjectDAO getSportObjectDAO() {
		return (SportObjectDAO) ctx.getAttribute("sportObjectDAO");
	}
	
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getComments(){
		return getCommentDAO().getComments();
	}
	
	@GET
	@Path("/get/username/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getCommentByUser(@PathParam("username") String username){
		return getCommentDAO().getCommentsByUser(username);
	}
	
	@GET
	@Path("/get/id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getCommentsBySportObject(@PathParam("id") int id){
		SportObject obj = getSportObjectDAO().getById(id);
		return getCommentDAO().getCommentsBySportsObject(obj);
	}
	
	@PUT
	@Path("/approve/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment approve(@PathParam("id") int id) {
		return getCommentDAO().approve(id);
	}
	
	@PUT
	@Path("/decline/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment decline(@PathParam("id") int id) {
		return getCommentDAO().decline(id);
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment create(NewCommentDTO dto) {
		return getCommentDAO().create(dto);
	}
	
	@GET
	@Path("/getByState/{state}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getbyState(@PathParam("state") CommentState state){
		return getCommentDAO().getByState(state);
	}
	
}

package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.User;
import dao.ArtikalDAO;
import dao.UserDAO;
import dto.LoginDTO;
import dto.LoginReturnDTO;
import dto.RegistrationDTO;

@Path("/users")
public class UserService {

	@Context
	ServletContext ctx;
	
	public UserService() {}
	
	@PostConstruct
	private void init() {
		if(ctx.getAttribute("userDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers(){
		return getUserDAO().getAll();
	}
	
	@POST
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	public User register(RegistrationDTO dto) {
		System.out.println(dto.getDateOfBirth().toString());
		return getUserDAO().register(dto);
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginReturnDTO login(LoginDTO dto, @Context HttpServletRequest request) {
		System.out.println("POGODIO SAM LOGIN");
		LoginReturnDTO lrd = getUserDAO().login(dto);
		if (lrd.isSuccess()) {
			request.getSession().setAttribute("user", lrd);
		}
		
		System.out.println("VRACAM OVO: " + lrd.getUsername() + " " + lrd.getRole() + " " + lrd.isSuccess());
		return lrd;
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public User logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
		return getUserDAO().logout();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginReturnDTO login(@Context HttpServletRequest request) {
		LoginReturnDTO lrd = (LoginReturnDTO) request.getSession().getAttribute("user");
		if (lrd == null) {
			return new LoginReturnDTO(null,null,false);
		}
		return lrd;
	}
}

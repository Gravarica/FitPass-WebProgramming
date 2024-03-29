package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
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

import beans.CustomerType;
import beans.SportObject;
import beans.User;
import dao.SportObjectDAO;
import dao.SubscriptionDAO;
import dao.TrainingDAO;
import dao.UserDAO;
import dto.CustomerTypeDTO;
import dto.LoginDTO;
import dto.LoginReturnDTO;
import dto.ManagerRegistrationDTO;
import dto.RegistrationDTO;
import dto.TrainerRegistrationDTO;
import dto.UpdateProfileDTO;
import dto.UserAccountInformationDTO;
import enums.CustomerTypeName;
import enums.Role;

@Path("/users")
public class UserService {

	@Context
	ServletContext ctx;
	
	public UserService() {}
	
	@PostConstruct
	private void init() {
		if(ctx.getAttribute("userDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath,ctx));
		}
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	private TrainingDAO getTrainingDAO() {
		return (TrainingDAO) ctx.getAttribute("trainingDAO");
	}
	
	private SubscriptionDAO getSubscriptionDAO() {
		if(ctx.getAttribute("subscriptionDAO") == null) {
			ctx.setAttribute("subscriptionDAO", new SubscriptionDAO(ctx));
		}
		
		return (SubscriptionDAO) ctx.getAttribute("subscriptionDAO");
	}
		
	//DUPLI KOD
	@GET
	@Path("/loggedUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getLoggedUser(){
		return getUserDAO().getLoggedUser();
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
		System.out.println("OVO SAM PRIMIO " + dto.getUsername() + dto.getPassword());
		LoginReturnDTO lrd = getUserDAO().login(dto);
		if(lrd.getRole() == Role.CUSTOMER) {
			getSubscriptionDAO().checkForExpired();
		}
		if (lrd.isSuccess()) {
			System.out.println("Usao sam ovde");
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
		System.out.println("Dobavljanje kolacica");
		LoginReturnDTO lrd = (LoginReturnDTO) request.getSession().getAttribute("user");
		if (lrd == null) {
			return new LoginReturnDTO(null,null,false);
		}
		System.out.println("VRACAM OVO: " + lrd.getUsername() + " " + lrd.getRole() + " " + lrd.isSuccess());
		return lrd;
	}
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User registerManager(ManagerRegistrationDTO dto) {
		return getUserDAO().registerManager(dto, dto.getSportObjectId());
	}
	
	@POST
	@Path("/registerTrainer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User registerTrainer(TrainerRegistrationDTO dto) {
		System.out.println("TU SAM BAKULE");
		User trainer = getUserDAO().registerTrainer(dto);
		if(dto.getTraining() != null) getTrainingDAO().setTrainingTrainer(dto.getTraining(),trainer);
		return trainer;
	}
  
	@GET
	@Path("/get/sport_object/manager")
	@Produces(MediaType.APPLICATION_JSON)
	public SportObject getSportObjectByManager() {
		return getUserDAO().getManagerSportObject();
	}
  
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User delete(@PathParam("id") String id) {
		return getUserDAO().delete(id);
	}
	
	@GET
	@Path("/account_info")
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccountInformationDTO getUserAccountInformation() {
		return getUserDAO().getUserAccountInfromation();
	}
  
	@GET
	@Path("/available/managers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAvailableManagers(){
		return getUserDAO().getAllAvailableManagers();
	}

	@PUT
	@Path("/editProfile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserAccountInformationDTO changeProfile(UpdateProfileDTO dto) {
		return getUserDAO().editProfile(dto);
	}
	
	@GET
	@Path("/loggedUser/customerType")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomerTypeDTO getCustomerType() {
		return getUserDAO().getLoggedUserCustomerType();
	}
	
	@GET
	@Path("/sort/username/{asc}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> sortByUsername(@PathParam("asc") boolean asc){
		return getUserDAO().sortByUsername(asc);
	}
	
	@GET
	@Path("/sort/firstName/{asc}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> sortByFirstName(@PathParam("asc") boolean asc){
		return getUserDAO().sortByFirstName(asc);
	}
	
	@GET
	@Path("/sort/lastName/{asc}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> sortByLastName(@PathParam("asc") boolean asc){
		return getUserDAO().sortByLastName(asc);
	}
	
	@GET
	@Path("/sort/points/{asc}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> sortByPoints(@PathParam("asc") boolean asc){
		return getUserDAO().sortByPoints(asc);
	}
	
	@GET
	@Path("/filter/role/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> getByRole(@PathParam("role") Role role){
		return getUserDAO().getUsersByRole(role);
	}
	
	@GET
	@Path("/filter/type/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> getByCustomerType(@PathParam("type") CustomerTypeName type){
		return getUserDAO().getByCustomerType(type);
	}
	
	@GET
	@Path("/sport_object/visited/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> getCustomersFromSportObject(@PathParam("id") int id) {
		return getUserDAO().getCustomersFromSportObject(id);
	}

	@GET
	@Path("/check/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkUsername(@PathParam("username") String username) {
		return getUserDAO().checkUsername(username);
	}
	
	@GET
	@Path("/get/trainers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllTrainers(){
		return getUserDAO().getAllTrainers();
	}
}

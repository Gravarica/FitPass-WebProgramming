package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import beans.TrainingHistory;
import dao.TrainingDAO;
import dao.UserDAO;

@Path("/trainings")
public class TrainingService {	

	@Context
	ServletContext ctx;
	
	public TrainingService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("trainingDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainingDAO", new TrainingDAO(contextPath));
		}
	}
	
	private TrainingDAO getTrainingDAO() {
		return (TrainingDAO) ctx.getAttribute("trainingDAO");
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	@GET
	@Path("/customer/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getCustomerTrainings(@PathParam("username") String username){
		return getUserDAO().getCustomerTrainingHistory(username);
	}
}

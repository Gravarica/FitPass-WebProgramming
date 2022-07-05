package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import beans.TrainingHistory;
import dao.TrainingHistoryDAO;
import dao.UserDAO;

@Path("/training_histories")
public class TrainingHistoryService {

	@Context
	ServletContext ctx;
	
	public TrainingHistoryService() {}

	@PostConstruct
	public void init() {
		if(ctx.getAttribute("trainingHistoryDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainingHistoryDAO", new TrainingHistoryDAO(contextPath));
		}
	}

	private TrainingHistoryDAO getTrainingHistoryDAO() {
		return (TrainingHistoryDAO) ctx.getAttribute("trainingHistoryDAO");
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}

	@GET
	@Path("/customer/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getCustomerTrainingHistory(@PathParam("username") String username){
		return getUserDAO().getCustomerTrainingHistory(username);
	}

	@GET
	@Path("/trainer/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getTrainerTrainingHistory(@PathParam("username") String username){
		return getUserDAO().getTrainerTrainingHistory(username);
	}
	
	@GET
	@Path("/trainer/personal/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getTrainerPersonalTrainings(@PathParam("username") String username){
		return getUserDAO().getTrainerPersonalTrainings(username);
	}
	
	@GET
	@Path("/trainer/group/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getTrainerGroupTrainings(@PathParam("username") String username){
		return getUserDAO().getTrainerGroupTrainings(username);
	}
	
	
	@GET
	@Path("/manager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getSportObjectTrainingHistory(@PathParam("id") int id){
		return getTrainingHistoryDAO().getSportObjectTrainingHistory(id);
	}

	@DELETE
	@Path("/cancel/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrainingHistory cancelPersonalTraining(@PathParam("id") int id) {
		return getTrainingHistoryDAO().cancelPersonalTraining(id);
	}






}

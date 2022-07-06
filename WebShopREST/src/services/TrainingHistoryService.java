package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import beans.TrainingHistory;
import beans.User;
import dao.SubscriptionDAO;
import dao.TrainingHistoryDAO;
import dao.UserDAO;
import dto.TrainingScheduleDTO;

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
	
	private SubscriptionDAO getSubscriptionDAO() {
		return (SubscriptionDAO) ctx.getAttribute("subscriptionDAO");
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getAll(){
		return getTrainingHistoryDAO().getAll();
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

	@POST
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public TrainingHistory scheduleTraining(TrainingScheduleDTO dto) {
		dto.setCustomer(getUserDAO().getLoggedUser());
		if(!getSubscriptionDAO().getByUser(dto.getCustomer().getUsername()).isActive()) {
			return null;
		}
		
		TrainingHistory newTraining = getTrainingHistoryDAO().scheduleTraining(dto);
		getUserDAO().increaseObjectVisited(newTraining.getCustomer().getUsername(),newTraining.getTraining().getObject());
		getUserDAO().updateTrainingHistory(newTraining);
		getSubscriptionDAO().increaseTrainingCounter(getSubscriptionDAO().getByUser(dto.getCustomer().getUsername()).getId());
		return newTraining;
	}
}

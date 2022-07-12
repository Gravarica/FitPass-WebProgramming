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
import dao.TrainingDAO;
import dao.UserDAO;
import dto.TrainingScheduleDTO;

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
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getAll(){
		return getTrainingDAO().getAll();
	}
	
	@GET
	@Path("/sport_object/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getSportObjectTrainings(@PathParam("id") int id){
		System.out.println("POZVAN SAM SA ID-em " + id);
		return getTrainingDAO().getSportObjectTrainings(id);
	}
	
	@GET
	@Path("/sport_object/with_trainer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getSportObjectTrainingsWithTrainer(@PathParam("id") int id){
		return getTrainingDAO().getSportObjectTrainingsWithTrainer(id);
	}

	@GET
	@Path("/without/trainer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getTrainingsWithoutTrainer(@PathParam("id") int id){
		return getTrainingDAO().getTrainingsWithoutTrainer(id);
	}

}
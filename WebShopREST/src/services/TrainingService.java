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
import dao.SportObjectDAO;
import dao.TrainingDAO;
import dao.UserDAO;
import dto.NewTrainingDTO;
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
	
	private SportObjectDAO getSportObjectDAO() {
		return (SportObjectDAO) ctx.getAttribute("sportObjectDAO");
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
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Training createTraining(NewTrainingDTO dto) {
		return getTrainingDAO().createTraining(dto);
	}
	
	@GET
	@Path("/check/name/{name}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkName(@PathParam("name") String name,@PathParam("id") int id) {
		return getTrainingDAO().checkName(name,id);
	}

	@GET
	@Path("/trainers/object/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getTrainersByObject(@PathParam("id") int id){
		return getTrainingDAO().getTrainersBySportObject(id);
	}
}
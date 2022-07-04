package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Subscription;
import dao.SportObjectDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import dto.NewSubscriptionDTO;

@Path("/subscriptions")
public class SubscriptionService {
	
	@Context 
	ServletContext ctx;
	
	public SubscriptionService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("subscriptionDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("subscriptionDAO", new SubscriptionDAO(contextPath));
		}
	}
	
	private SubscriptionDAO getSubscriptionDAO() {
		return (SubscriptionDAO) ctx.getAttribute("subscriptionDAO");
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Subscription> getAll(){
		return getSubscriptionDAO().getAll();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Subscription createSubscription(NewSubscriptionDTO dto) {
		return getSubscriptionDAO().createSubscription(dto);
	}
}

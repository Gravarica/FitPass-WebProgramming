package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Artikal;
import dao.ArtikalDAO;

@Path("/artikli")
public class ArtikalService {

	@Context
	ServletContext ctx;
	
	public ArtikalService() {}
	
	@PostConstruct
	public void init() {
		
		if(ctx.getAttribute("artikalDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("artikalDAO", new ArtikalDAO(contextPath));
		}
	}
	
	private ArtikalDAO getArtikalDAO() {
		return (ArtikalDAO) ctx.getAttribute("artikalDAO");
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Artikal> getAll(){
		return getArtikalDAO().getAll();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Artikal createArtikal(Artikal artikal) throws WebApplicationException {
		Artikal retArt = getArtikalDAO().insert(artikal);
		if(retArt == null) {
			throw new WebApplicationException("Uneta sifra vec postoji", Response.Status.BAD_REQUEST);
		}
		return retArt;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Artikal deleteArtikal(@PathParam("id") String id) {
		return getArtikalDAO().delete(id);
	}
	
	@GET
	@Path("/total")
	@Produces(MediaType.APPLICATION_JSON)
	public double getTotalPrice() {
		return getArtikalDAO().getTotalPrice();
	}
	
}

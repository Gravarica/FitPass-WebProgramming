package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.PromoCode;
import dao.PromoCodeDAO;
import dto.NewPromoCodeDTO;
import dto.ReturnPromoCodeDTO;

@Path("/promos")
public class PromoCodeService {

	@Context
	ServletContext ctx;
	
	public PromoCodeService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("promoCodeDAO") == null) {
			ctx.setAttribute("promoCodeDAO", new PromoCodeDAO(ctx));
		}
	}
	
	private PromoCodeDAO getPromoCodeDAO() {
		return (PromoCodeDAO) ctx.getAttribute("promoCodeDAO");
	}
	
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PromoCode> getAll(){
		return getPromoCodeDAO().getCodes();
	}
	
	@GET
	@Path("/get/used")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PromoCode> getUsed(){
		return getPromoCodeDAO().getAllUsed();
	}
	
	@GET
	@Path("/get/unused")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PromoCode> getUnused(){
		return getPromoCodeDAO().getAllUnused();
	}
	
	@GET
	@Path("/get/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode getUsed(@PathParam("code") String code){
		return getPromoCodeDAO().getByCode(code);
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(NewPromoCodeDTO dto) {
		return getPromoCodeDAO().create(dto);
	}
	
	@GET
	@Path("/redeem/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnPromoCodeDTO redeem(@PathParam("code") String code) {
		return getPromoCodeDAO().redeem(code);
	}
}

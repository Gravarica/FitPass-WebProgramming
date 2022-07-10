package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.PromoCode;
import dto.NewPromoCodeDTO;
import dto.ReturnPromoCodeDTO;

public class PromoCodeDAO {

	private File file;
	private ArrayList<PromoCode> codes;
	private ServletContext ctx;
	
	private static final String VALID_CODE_MESSAGE = "Code is succesfully redeemed!";
	private static final String EXPIRED_CODE_MESSAGE = "The code you entered is expired.";
	private static final String USED_CODE = "The code you entered has already been redeemed.";
	private static final String INVALID_CODE = "The code you entered is invalid.";
	
	public PromoCodeDAO() {}
	
	public PromoCodeDAO(ServletContext ctx) {
		this.ctx = ctx;
		file = new File(ctx.getRealPath("") + "/Resources/Data/promoCodes.json");
		loadPromoCodes();
	}
	
	private void loadPromoCodes() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			codes = mapper.readValue(file, new TypeReference<List<PromoCode>>() {});
			codes = filterUndeleted();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<PromoCode> filterUndeleted(){
		ArrayList<PromoCode> filteredList = new ArrayList<>();
		for(PromoCode pc : codes) {
			if(!pc.isDeleted()) {
				filteredList.add(pc);
			}
		}
		return filteredList;
	}
	
	private void save() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writeValue(file, codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getMaxId() {
		Integer maxId = -1;
		for (PromoCode pc : codes) {
			if (pc.getId() > maxId) {
				maxId = pc.getId();
			}
		}
		
		return ++maxId;
	}
	
	public ArrayList<PromoCode> getCodes(){
		return codes;
	}
	
	public PromoCode getById(int id) {
		for(PromoCode pc : codes) {
			if(pc.getId() == id) {
				return pc;
			}
		}
		return null;
	}
	
	public PromoCode getByCode(String code) {
		for(PromoCode pc: codes) {
			if(pc.exists(code)) {
				return pc;
			}
		}
		return null;
	}
	
	public ArrayList<PromoCode> getAllUnused(){
		ArrayList<PromoCode> returnList = new ArrayList<PromoCode>();
		for(PromoCode pc : codes) {
			if(!pc.isUsed()) {
				returnList.add(pc);
			}
		}
		return returnList;
	}
	
	public ArrayList<PromoCode> getAllUsed(){
		ArrayList<PromoCode> returnList = new ArrayList<PromoCode>();
		for(PromoCode pc : codes) {
			if(pc.isUsed()) {
				returnList.add(pc);
			}
		}
		return returnList;
	}
	
	private boolean checkIfExists(String code) {
		for(PromoCode promoCode : codes) {
			if(promoCode.exists(code)) return true;
		}
		
		return false;
	}
	
	public PromoCode create(NewPromoCodeDTO dto) {
		if (checkIfExists(dto.getCode())) {
			return null;
		}
		
		PromoCode newInstance = new PromoCode(dto);
		newInstance.setId(getMaxId());
		codes.add(newInstance);
		save();
		return newInstance;
	}
	
	public ReturnPromoCodeDTO redeem(String entry) {
		PromoCode redeemCode = getByCode(entry);
		if(redeemCode == null) {
			return new ReturnPromoCodeDTO(0.0, INVALID_CODE);
		}
		if(redeemCode.hasExpired()) {
			return new ReturnPromoCodeDTO(0.0, EXPIRED_CODE_MESSAGE);
		}
		if(redeemCode.isUsed()) {
			return new ReturnPromoCodeDTO(0.0, USED_CODE);
		}
		
		redeemCode.redeemedOnce();
		return new ReturnPromoCodeDTO(redeemCode.getDiscount(), VALID_CODE_MESSAGE);
	}
}

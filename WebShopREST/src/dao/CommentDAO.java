package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Comment;
import beans.SportObject;
import beans.User;
import dto.AverageGradeDTO;
import dto.NewCommentDTO;
import enums.CommentState;

public class CommentDAO {

	private File file;
	private ArrayList<Comment> comments;
	private ServletContext ctx;
	
	public CommentDAO() {}
	
	public CommentDAO(ServletContext ctx) {
		this.ctx = ctx;
		file = new File(ctx.getRealPath("") + "/Resources/Data/comments.json");
		loadSubscriptions();
	}
	
	private void loadSubscriptions() {

		ObjectMapper mapper = new ObjectMapper();
	
		try {
			comments = mapper.readValue(file, new TypeReference<List<Comment>>() {});
			comments = filterUndeleted(comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Comment> filterUndeleted(ArrayList<Comment> comments){
		ArrayList<Comment> filteredList = new ArrayList<>();
		for(Comment c : comments) {
			if(!c.isDeleted()) {
				filteredList.add(c);
			}
		}
		
		return filteredList;
	}
	
	private void save() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writeValue(file, comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getMaxId() {
		Integer maxId = -1;
		for (Comment c : comments) {
			if (c.getId() > maxId) {
				maxId = c.getId();
			}
		}
		
		return ++maxId;
	}
	
	private SportObjectDAO getSportObjectDAO() {
		return (SportObjectDAO) ctx.getAttribute("sportObjectDAO");
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	public ArrayList<Comment> getComments(){
		return comments;
	}
	
	public Comment getById(int id) {
		for(Comment c : comments) {
			if(c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}
	
	public ArrayList<Comment> getCommentsBySportsObject(SportObject obj){
		ArrayList<Comment> objectsComments = new ArrayList<>();
		for(Comment c : comments) {
			if (c.objectMatches(obj)) {
				objectsComments.add(c);
			}
		}
		
		return objectsComments;
	}
	
	public AverageGradeDTO getObjectStats(SportObject obj) {
		int sum = 0;
		int count = 0;
		for (Comment c : comments) {
			if(c.objectMatches(obj)) {
				sum += c.getGrade();
				count++;
			}
		}
		
		return new AverageGradeDTO(sum,count);
	}
	
	public ArrayList<Comment> getCommentsByUser(String username){
		ArrayList<Comment> userComments = new ArrayList<>();
		for(Comment c: comments) {
			if (c.userMatches(username)) {
				userComments.add(c);
			}
		}
		
		return userComments;
	}
	
	public ArrayList<Comment> getByState(CommentState state){
		ArrayList<Comment> returnList = new ArrayList<>();
		for(Comment c: comments) {
			if(c.getState() == state) {
				returnList.add(c);
			}
		}
		
		return returnList;
	}
	
	public Comment create(NewCommentDTO dto) {
		User customer = getUserDAO().getByUsername(dto.getUsername());
		SportObject obj = getSportObjectDAO().getById(dto.getObjectId());
		Comment newInstance = new Comment(getMaxId(), customer, obj, dto.getText(), dto.getGrade());
		comments.add(newInstance);
		save();
		return newInstance;
	}
	
	public Comment approve(int id) {
		Comment c = getById(id);
		c.approve();
		getSportObjectDAO().calculateAverageGrade(c.getObject(), getObjectStats(c.getObject()));
		save();
		return c;
	}
	
	public Comment decline(int id) {
		Comment c = getById(id);
		c.decline();
		save();
		return c;
	}
	
	public ArrayList<Comment> getBySportObjectByState(SportObject obj, CommentState state){
		ArrayList<Comment> list = new ArrayList<Comment>();
		for (Comment c : getByState(state)) {
			if(c.objectMatches(obj)) {
				list.add(c);
			}
		}
		
		return list;
	}
	
}

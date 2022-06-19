package dto;

import enums.Role;

public class LoginReturnDTO {

	private String username;
	private Role role;
	private boolean success;
	
	public LoginReturnDTO(String username, Role role, boolean success) {
		super();
		this.username = username;
		this.role = role;
		this.success = success;
	}

	public LoginReturnDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

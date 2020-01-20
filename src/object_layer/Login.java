package object_layer;

public class Login {
	
	String id;
	String username;
	String password;
	String isAdmin;
	
	public Login(String id, String username, String password, String isAdmin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}

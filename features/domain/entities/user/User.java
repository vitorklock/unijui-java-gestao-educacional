package domain.entities.user;

public abstract class User {
	private static int nextId = 1;
    private int id;
    
    protected String name;
    protected String email;
    protected String phone;
    
    public enum UserRole {
        ADMIN, TEACHER, STUDENT
    }

    public User(String name, String email, String phone) {
    	this.setId(nextId++);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    public abstract UserRole role();

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
    public String toString() {
        return name;
    }

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
}

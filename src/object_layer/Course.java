package object_layer;

public class Course {

	String id;
	String courseNumber;
	String name;
	String department;
	
	public Course(String id, String courseNumber, String name, String department) {
		super();
		this.id = id;
		this.courseNumber = courseNumber;
		this.name = name;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}

package object_layer;

public class Class {

	String crn;
	String courseNumber;
	String department;
	String section;
	String name;
	String semester;
	String year;
	String campus;
	String grade;
	
	public Class(String crn, String courseNumber, String department, String section, String name, String semester,
			String year, String campus, String grade) {
		super();
		this.crn = crn;
		this.courseNumber = courseNumber;
		this.department = department;
		this.section = section;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.campus = campus;
		this.grade = grade;
	}
	
	public String getCRN() {
		return crn;
	}
	public void setCRN(String crn) {
		this.crn = crn;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

	
	
}

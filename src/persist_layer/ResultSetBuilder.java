package persist_layer;

import java.sql.ResultSet;
import java.sql.SQLException;
import object_layer.Class;
import object_layer.Course;
import object_layer.Instrument;
import object_layer.LearningOutcome;
import object_layer.Login;
import object_layer.MissionObjective;
import object_layer.Professor;
import object_layer.ABET;
import object_layer.Administrator;
import object_layer.Assignment;

public class ResultSetBuilder {
	
	public ABET buildABET(ResultSet RS) throws SQLException {
		String id = RS.getString(1); //ResultSet columns start at 1
		String description = RS.getString(2);
		String tag = RS.getString(3);
		String isCurrentlyUsed = RS.getString(4);
		String categoryID = RS.getString(5);
		String categoryName = RS.getString(6);
		String subcategoryID = RS.getString(7);
		
		ABET newABET = new ABET(id, description, tag, isCurrentlyUsed, categoryID, categoryName, subcategoryID);
		return newABET;
	}
	
	public Administrator buildAdministrator(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String firstName = RS.getString(2);
		String lastName = RS.getString(3);
		String email = RS.getString(4);
		
		Administrator newAdmin = new Administrator(id, firstName, lastName, email);
		return newAdmin;
	}
	
	public Assignment buildAssignment(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String lowestPDF = RS.getString(2);
		String medianPDF = RS.getString(3);
		String highestPDF = RS.getString(4);
		String lowestGrade = RS.getString(5);
		String medianGrade = RS.getString(6);
		String highestGrade = RS.getString(7);
		String averageGrade = RS.getString(8);
		
		Assignment newAssignment = new Assignment(id, lowestPDF, medianPDF, highestPDF, 
				lowestGrade, medianGrade, highestGrade, averageGrade);
		return newAssignment;
	}
	
	public Class buildClass(ResultSet RS) throws SQLException {
		String crn = RS.getString(1);
		String courseNumber = RS.getString(2);
		String department = RS.getString(3);
		String section = RS.getString(4);
		String name = RS.getString(5);
		String semester = RS.getString(6);
		String year = RS.getString(7);
		String campus = RS.getString(8);
		String grade = RS.getString(9);
		
		Class newClass = new Class(crn, courseNumber, department, section, name, semester, year, campus, grade);
		return newClass;
	}
	
	public Course buildCourse(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String courseNumber = RS.getString(2);
		String name = RS.getString(3);
		String department = RS.getString(4);
		
		Course newCourse = new Course(id, courseNumber, name, department);
		return newCourse;
	}
	
	public Instrument buildInstrument(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String gradeAverage = RS.getString(2);
		String type = RS.getString(3);
		String description = RS.getString(4);
		String weight = RS.getString(5);
		
		Instrument newInstrument = new Instrument(id, gradeAverage, type, description, weight);
		return newInstrument;
	}
	
	public LearningOutcome buildLearningOutcome(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String description = RS.getString(2);
		String year = RS.getString(3);
		String tag = RS.getString(4);
		String isCurrentlyUsed = RS.getString(5);
		
		LearningOutcome newLearningOutcome = new LearningOutcome(id, description, year, tag, isCurrentlyUsed);
		return newLearningOutcome;
	}
	
	public Login buildLogin(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String username = RS.getString(2);
		String password = RS.getString(3);
		String isAdmin = RS.getString(4);
		
		Login newLogin = new Login(id, username, password, isAdmin);
		return newLogin;
	}
	
	public MissionObjective buildMissionObjective(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String description = RS.getString(2);
		String year = RS.getString(3);
		String isCurrentlyUsed = RS.getString(4);
		String tag = RS.getString(5);
		
		MissionObjective newMissionObjective = new MissionObjective(id, description, year, isCurrentlyUsed, tag);
		return newMissionObjective;
	}
	
	public Professor buildProfessor(ResultSet RS) throws SQLException {
		String id = RS.getString(1);
		String firstName = RS.getString(2);
		String lastName = RS.getString(3);
		String email = RS.getString(4);
		
		Professor newProfessor = new Professor(id, firstName, lastName, email);
		return newProfessor;
	}

}

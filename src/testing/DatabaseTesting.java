package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import logic_layer.DatabaseLogic;
import object_layer.ABET;
import object_layer.Class;
import object_layer.Course;
import object_layer.Instrument;
import object_layer.LearningOutcome;
import object_layer.Login;
import object_layer.MissionObjective;
import object_layer.Professor;
import object_layer.Administrator;
import object_layer.Assignment;
import persist_layer.DatabaseQueries;

class DatabaseTesting {
	// Import database commands
	DatabaseLogic db = new DatabaseLogic();

	/************ ABET **************/
	// See if ArrayList of ABET objects is not null
	@Test
	void testGetAllABET() throws SQLException {
		ArrayList<ABET> ABETList = db.getAllABET();
		assertTrue(ABETList != null);
	}

	// See if list of currently used ABET objects is not null
	@Test
	void testGetAllCurrentABET() throws SQLException {
		ArrayList<ABET> ABETList = db.getAllCurrentABET();
		assertTrue(ABETList != null);
	}

	// Test to see if inserting to database ABET works
	@Test
	void testAddNewABET() {
		int result = db.addNewABET("New ABET from test class", "General");
		assertTrue(result == 1);
	}

	/************ Administrators **************/
	// See if ArrayList of Administrator objects is not null
	@Test
	void testGetAllAdministrators() throws SQLException {
		ArrayList<Administrator> AdministratorsList = db.getAllAdministrators();
		assertTrue(AdministratorsList != null);
	}

	/************ Assignments **************/
	// See if ArrayList of Assignment objects is not null
	@Test
	void testGetAllAssignments() throws SQLException {
		ArrayList<Assignment> assignmentsList = db.getAllAssignments();
		assertTrue(assignmentsList != null);
	}

	// Get assignments from ABET ID
	@Test
	void testGetAllABETAvg() throws SQLException {
		String input = "1";
		int sum = 0;
		ArrayList<Integer> avgList = new ArrayList<Integer>();
		ArrayList<Assignment> assList = new ArrayList<Assignment>();
		ArrayList<ABET> ABETlist = db.getAllABET();
		for (int i = 0; i < ABETlist.size(); i++) {
			sum = 0;
			assList = db.getAssignmentsFromABETid(ABETlist.get(i).getId());
			if(assList.size() > 0) {
			for (int j = 0; j < assList.size(); j++) {
				sum += Integer.parseInt(assList.get(j).getAverageGrade());
			}
			sum = sum/assList.size();
			}
			avgList.add(sum);
			
		}
		assertTrue(assList.size() != 0);
	}

	/************ Classes **************/
	// See if ArrayList of Class objects is not null
	@Test
	void testGetAllClasses() throws SQLException {
		ArrayList<Class> classesList = db.getAllClasses();
		assertTrue(classesList != null);
	}

	/************ Class to Learning Outcome **************/
	// Test to get Learning Outcomes list from class CRN
	@Test
	void testGetLearningOutcomesFromClassCRN() throws SQLException {
		String CRN = "111111";
		ArrayList<LearningOutcome> result = db.getLearningOutcomesFromClassCRN(CRN);
		assertTrue(result != null);
	}

	/************ Courses **************/
	// See if ArrayList of Course objects is not null
	@Test
	void testGetAllCourses() throws SQLException {
		ArrayList<Course> coursesList = db.getAllCourses();
		assertTrue(coursesList != null);
	}

	/************ Instruments **************/
	// See if ArrayList of Instrument objects is not null
	@Test
	void testGetAllInstruments() throws SQLException {
		ArrayList<Instrument> instrumentList = db.getAllInstruments();
		assertTrue(instrumentList != null);
	}

	/************ Learning Outcomes **************/
	// See if ArrayList of LearningOutcome objects is not null
	@Test
	void testGetAllLearningOutcomes() throws SQLException {
		ArrayList<LearningOutcome> learningOutcomeList = db.getAllLearningOutcomes();
		assertTrue(learningOutcomeList != null);
	}

	/************ Logins **************/
	// See if ArrayList of ABET objects is not null
	@Test
	void testGetAllLogins() throws SQLException {
		ArrayList<Login> loginList = db.getAllLogins();
		assertTrue(loginList != null);
	}

	/************ Mission Objectives **************/
	// See if ArrayList of MissionObjective objects is not null
	@Test
	void testGetAllMissionObjectives() throws SQLException {
		ArrayList<MissionObjective> missionObjectiveList = db.getAllMissionObjectives();
		assertTrue(missionObjectiveList != null);
	}

	/************ Professors **************/
	// See if ArrayList of Professor objects is not null
	@Test
	void testGetAllProfessors() throws SQLException {
		ArrayList<Professor> professorList = db.getAllProfessors();
		assertTrue(professorList != null);
	}

}

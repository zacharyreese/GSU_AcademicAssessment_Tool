package logic_layer;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import object_layer.ABET;
import object_layer.Administrator;
import object_layer.Assignment;
import object_layer.Class;
import object_layer.Course;
import object_layer.Instrument;
import object_layer.LearningOutcome;
import object_layer.Login;
import object_layer.MissionObjective;
import object_layer.Professor;
import persist_layer.DbAccessImpl;
import persist_layer.ResultSetBuilder;
import persist_layer.DatabaseQueries;
import servlet.mainServlet;

public class DatabaseLogic {
	DatabaseQueries db = new DatabaseQueries();
	ResultSetBuilder builder = new ResultSetBuilder();
	
	
	/************ Graph Functions **************/
	//Get all ABET averages
	public ArrayList<Integer> getAllABETAverages() {
		int sum = 0;
		ArrayList<Integer> avgList = new ArrayList<Integer>();
		ArrayList<Assignment> assList = new ArrayList<Assignment>();
		ArrayList<ABET> ABETlist = getAllABET();
		for (int i = 0; i < ABETlist.size(); i++) {
			sum = 0;
			assList = getAssignmentsFromABETid(ABETlist.get(i).getId());
			if(assList.size() > 0) {
			for (int j = 0; j < assList.size(); j++) {
				sum += Integer.parseInt(assList.get(j).getAverageGrade());
			}
			sum = sum/assList.size();
			}
			avgList.add(sum);
		}
		return avgList;
	}
	
	//Get all ABET averages Statesboro
		public ArrayList<Integer> getAllABETAveragesStatesboro() {
			int sum = 0;
			ArrayList<Integer> avgList = new ArrayList<Integer>();
			ArrayList<Assignment> assList = new ArrayList<Assignment>();
			ArrayList<ABET> ABETlist = getAllABET();
			for (int i = 0; i < ABETlist.size(); i++) {
				sum = 0;
				assList = getAssignmentsFromABETidStatesboro(ABETlist.get(i).getId());
				if(assList.size() > 0) {
				for (int j = 0; j < assList.size(); j++) {
					sum += Integer.parseInt(assList.get(j).getAverageGrade());
				}
				sum = sum/assList.size();
				}
				avgList.add(sum);
			}
			return avgList;
		}
		
		//Get all ABET averages Savannah
		public ArrayList<Integer> getAllABETAveragesSavannah() {
			int sum = 0;
			ArrayList<Integer> avgList = new ArrayList<Integer>();
			ArrayList<Assignment> assList = new ArrayList<Assignment>();
			ArrayList<ABET> ABETlist = getAllABET();
			for (int i = 0; i < ABETlist.size(); i++) {
				sum = 0;
				assList = getAssignmentsFromABETidSavannah(ABETlist.get(i).getId());
				if(assList.size() > 0) {
				for (int j = 0; j < assList.size(); j++) {
					sum += Integer.parseInt(assList.get(j).getAverageGrade());
				}
				sum = sum/assList.size();
				}
				avgList.add(sum);
			}
			return avgList;
		}
		
		//Get all ABET averages Hinesville
		public ArrayList<Integer> getAllABETAveragesHinesville() {
			int sum = 0;
			ArrayList<Integer> avgList = new ArrayList<Integer>();
			ArrayList<Assignment> assList = new ArrayList<Assignment>();
			ArrayList<ABET> ABETlist = getAllABET();
			for (int i = 0; i < ABETlist.size(); i++) {
				sum = 0;
				assList = getAssignmentsFromABETidHinesville(ABETlist.get(i).getId());
				if(assList.size() > 0) {
				for (int j = 0; j < assList.size(); j++) {
					sum += Integer.parseInt(assList.get(j).getAverageGrade());
				}
				sum = sum/assList.size();
				}
				avgList.add(sum);
			}
			return avgList;
		}
		
		
		/************ Average **************/
		public String getAssignmentAverageFromLearningOutcomeID(String loID) {
			String average = "";
			ResultSet RS = db.getAssignmentAverageFromLearningOutcomeID(loID);
			try {
				while(RS.next()) {
					average = RS.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return average;
		}
	

	/************ ABET **************/

	// Get all ABET requirements in a list
	public ArrayList<ABET> getAllABET() {
		ArrayList<ABET> ABETList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllABET();
			while (RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}

	// Get all ABET requirements that are currently used
	public ArrayList<ABET> getAllCurrentABET() {
		ArrayList<ABET> ABETList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllCurrentABET();
			while (RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}

	// Get most recent ABET entry
	public ABET getMostRecentABET() {
		ABET newABET = null;
		ResultSet RS = db.getMostRecentABET();
		try {
			while (RS.next()) {
				newABET = builder.buildABET(RS);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newABET;
	}

	// Insert new ABET requirement using only description and category
	public int addNewABET(String description, String categoryName) {
		String categoryID = "";
		if (categoryName.equals("General")) {
			categoryID = "1";
		} else if (categoryName.equals("CS Specific")) {
			categoryID = "2";
		}
		String subcategoryID = getNewABETSubcategory(categoryName);
		return db.addNewABET(description, categoryName, categoryID, subcategoryID);
	}

	// Get last subcategory ID to increment by one
	public String getNewABETSubcategory(String categoryName) {
		ResultSet RS = db.getABETLastSubcategoryID(categoryName);
		String newSubcategoryID = null;
		try {
			while (RS.next()) {
				newSubcategoryID = RS.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		newSubcategoryID = String.valueOf((char) (newSubcategoryID.charAt(0) + 1)); // Convert string to char and
																					// increment by 1
		return newSubcategoryID;
	}
	
	//Get one ABET from each category for header purposes
	public ArrayList<ABET> getAllABETCategories() {
		ArrayList<ABET> ABETList = new ArrayList<ABET>();
		ResultSet RS = db.getAllABETCategories();
		try {
			while(RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}
	
	
	//Get ABET from assignmentID
	public ArrayList<ABET> getABETFromAssignmentID(String assignmentID) {
		ArrayList<ABET> ABETList = new ArrayList<>();
		ResultSet RS = db.getABETFromAssignmentID(assignmentID);
		try {
			while(RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}
	
	
	//Get ABET from instrument ID
	public ArrayList<ABET> getABETFromInstrumentID(String instrumentID) {
		ArrayList<ABET> ABETList = new ArrayList<>();
		ResultSet RS = db.getABETFromInstrumentID(instrumentID);
		try {
			while(RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}
	
	
	
	/************ ABET to Class **************/
	//Get ABET from class CRN
		public ArrayList<ABET> getABETFromClassCRN(String CRN) {
			ArrayList<ABET> ABETList = new ArrayList<>();
			ResultSet RS = db.getABETFromClassCRN(CRN);
			try {
				while(RS.next()) {
					ABET newABET = builder.buildABET(RS);
					ABETList.add(newABET);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ABETList;
		}
	
	
	/************ ABET to Course **************/
	//Get ABET from course iD
	public ArrayList<ABET> getABETFromCourseID(String courseID) {
		ArrayList<ABET> ABETList = new ArrayList<>();
		ResultSet RS = db.getABETFromCourseID(courseID);
		try {
			while(RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}
	
	
	/************ ABET to Learning Outcome **************/
	//Get ABET list from learning outcome ID
	public ArrayList<ABET> getABETFromLearningOutcomeID(String learningOutcomeID) {
		ArrayList<ABET> ABETList = new ArrayList<>();
		ResultSet RS = db.getABETFromLearningOutcomeID(learningOutcomeID);
		try {
			while(RS.next()) {
				ABET newABET = builder.buildABET(RS);
				ABETList.add(newABET);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ABETList;
	}
	
	

	/************ ABET to Mission Objective **************/
	// Connect most recent ABET entry to selected Mission Objectives
	public int connectABETtoMissionObjective(String ABETid, String ABETtag, String missionObjectiveID) {
		return db.connectABETtoMissionObjective(ABETid, ABETtag, missionObjectiveID);
	}

	/************ Administrators **************/

	// Get all administrators in a list
	public ArrayList<Administrator> getAllAdministrators() {
		ArrayList<Administrator> administratorList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllAdministrators();
			while (RS.next()) {
				Administrator newAdministrator = builder.buildAdministrator(RS);
				administratorList.add(newAdministrator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administratorList;
	}
	
	//Get all admins alphabetical
	public ArrayList<Administrator> getAllAdministratorsAlphabetical() {
		ArrayList<Administrator> administratorList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllAdministratorsAlphabetical();
			while (RS.next()) {
				Administrator newAdministrator = builder.buildAdministrator(RS);
				administratorList.add(newAdministrator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administratorList;
	}

	// Add new administrator
	public int addNewAdministrator(String firstName, String lastName, String email) {
		return db.addNewAdministrator(firstName, lastName, email);
	}

	/************ Assignments **************/

	// Get all assignments in a list
	public ArrayList<Assignment> getAllAssignments() {
		ArrayList<Assignment> assignmentList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllAssignments();
			while (RS.next()) {
				Assignment newAssignment = builder.buildAssignment(RS);
				assignmentList.add(newAssignment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assignmentList;
	}
	
	//Add new assignment
	public int addNewAssignment(String lowPDF, String medPDF, String highPDF, String lowGrade, String medGrade,
			String highGrade, String avgGrade) {
		return db.addNewAssignment(lowPDF, medPDF, highPDF, lowGrade, medGrade, highGrade, avgGrade);
	}
	
	//Get most recent assignment
	public Assignment getMostRecentAssignment() {
		Assignment ass = null;
		ResultSet RS = db.getMostRecentAssignment();
		try {
			while(RS.next()) {
				ass = builder.buildAssignment(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ass;
	}
	
	//Get assignments from ABET id
	public ArrayList<Assignment> getAssignmentsFromABETid(String ABETid) {
		ArrayList<Assignment> assignmentList = new ArrayList<>();
		try {
			ResultSet RS = db.getAssignmentsFromABETid(ABETid);
			while (RS.next()) {
				Assignment newAssignment = builder.buildAssignment(RS);
				assignmentList.add(newAssignment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assignmentList;
	}
	
	//Get assignments from ABET id Statesboro
		public ArrayList<Assignment> getAssignmentsFromABETidStatesboro(String ABETid) {
			ArrayList<Assignment> assignmentList = new ArrayList<>();
			try {
				ResultSet RS = db.getAssignmentsFromABETidStatesboro(ABETid);
				while (RS.next()) {
					Assignment newAssignment = builder.buildAssignment(RS);
					assignmentList.add(newAssignment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return assignmentList;
		}
		
		//Get assignments from ABET id Savannah
		public ArrayList<Assignment> getAssignmentsFromABETidSavannah(String ABETid) {
			ArrayList<Assignment> assignmentList = new ArrayList<>();
			try {
				ResultSet RS = db.getAssignmentsFromABETidSavannah(ABETid);
				while (RS.next()) {
					Assignment newAssignment = builder.buildAssignment(RS);
					assignmentList.add(newAssignment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return assignmentList;
		}
		
		//Get assignments from ABET id Hinesville
		public ArrayList<Assignment> getAssignmentsFromABETidHinesville(String ABETid) {
			ArrayList<Assignment> assignmentList = new ArrayList<>();
			try {
				ResultSet RS = db.getAssignmentsFromABETidHinesville(ABETid);
				while (RS.next()) {
					Assignment newAssignment = builder.buildAssignment(RS);
					assignmentList.add(newAssignment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return assignmentList;
		}
		
		//Get assignments from mission objective ID
		public ArrayList<Assignment> getAssignmentsFromMissionObjectiveID(String moID) {
			ArrayList<Assignment> assList = new ArrayList<Assignment>();
			ResultSet RS = db.getAssignmentFromMissionObjectiveID(moID);
			try {
				while(RS.next()) {
					Assignment newAss = builder.buildAssignment(RS);
					assList.add(newAss);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return assList;
		}
	
	
	/************ Assignment to Instrument **************/
	//Connect assignment and instrument id with helper table
	public int connectAssignmentToInstrument(String assID, String instrumentID) {
		return db.connectAssignmentToInstrument(assID, instrumentID);
	}
	
	//Get assignments from instrument ID
	public ArrayList<Assignment> getAssignmentsFromInstrumentID(String instrumentID) {
		ArrayList<Assignment> assList = new ArrayList<Assignment>();
		ResultSet RS = db.getAssignmentsFromInstrumentID(instrumentID);
		try {
			while(RS.next()) {
				Assignment ass = builder.buildAssignment(RS);
				assList.add(ass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assList;
	}
	

	/************ Classes **************/

	// Get all classes in a list
	public ArrayList<Class> getAllClasses() {
		ArrayList<Class> classList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllClasses();
			while (RS.next()) {
				Class newClass = builder.buildClass(RS);
				classList.add(newClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classList;
	}

	// Get all classes in alphabetical order
	public ArrayList<Class> getAllClassesAlphabetical() {
		ArrayList<Class> classList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllClassesAlphabetical();
			while (RS.next()) {
				Class newClass = builder.buildClass(RS);
				classList.add(newClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classList;
	}
	
	//Get class by CRN
	public Class getClassFromCRN(String CRN) {
		Class newClass = null;
		ResultSet RS = db.getClassByCRN(CRN);
		try {
			while(RS.next()) {
				newClass = builder.buildClass(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newClass;
	}

	// Search class by name
	public ArrayList<Class> classSearchByName(String input) {
		ArrayList<Class> classList = new ArrayList<>();
		try {
			ResultSet RS = db.getClassesByName(input);
			while (RS.next()) {
				Class newClass = builder.buildClass(RS);
				classList.add(newClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classList;
	}

	// Add new class
	public int addNewClass(String CRN, String courseNum, String dept, String section, String name, String semester,
			String year, String campus) {
		return db.addNewClass(CRN, courseNum, dept, section, name, semester, year, campus);
	}
	
	//Get classes from ABET ID
	public ArrayList<Class> getClassesFromABETid(String ABETid) {
		ArrayList<Class> classList = new ArrayList<>();
		try {
			ResultSet RS = db.getClassesFromABETid(ABETid);
			while (RS.next()) {
				Class newClass = builder.buildClass(RS);
				classList.add(newClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classList;
	}
	

	/************ Class to Course **************/
	// Connect class CRN and course ID with helper table
	public int connectClassToCourse(String CRN, String courseID) {
		return db.connectClassToCourse(CRN, courseID);
	}

	// Get course from class CRN
	public Course getCourseFromClassCRN(String CRN) {
		Course course = null;
		ResultSet RS = db.getClassByCRN(CRN);
		try {
			while(RS.next()) {
				course = builder.buildCourse(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	
	/************ Class to Learning Outcomes **************/
	//Get all learning outcomes for a specific class CRN
	public ArrayList<LearningOutcome> getLearningOutcomesFromClassCRN(String CRN) {
		ArrayList<LearningOutcome> loList = new ArrayList<LearningOutcome>();
		ResultSet RS = db.getLearningOutcomesFromClassCRN(CRN);
		try {
			while(RS.next()) {
				LearningOutcome lo = builder.buildLearningOutcome(RS);
				loList.add(lo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loList;
	}

	/************ Course **************/

	// Get all Course in a list
	public ArrayList<Course> getAllCourses() {
		ArrayList<Course> courseList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllCourses();
			while (RS.next()) {
				Course newCourse = builder.buildCourse(RS);
				courseList.add(newCourse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	// Get all Course in an alphabeticalized list
	public ArrayList<Course> getAllCoursesAlphabetically() {
		ArrayList<Course> courseList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllCoursesAlphabetically();
			while (RS.next()) {
				Course newCourse = builder.buildCourse(RS);
				courseList.add(newCourse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	// Get course by ID
	public Course getCourseByID(String id) {
		ResultSet RS = db.getCourseByID(id);
		Course course = null;
		try {
			while (RS.next()) {
				course = builder.buildCourse(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	// Add new course
	public int addNewCourse(String courseNumber, String name, String department) {
		return db.addNewCourse(courseNumber, name, department);
	}
	
	//Get course from ABET ID
	public ArrayList<Course> getCoursesFromABETid(String ABETid) {
		ArrayList<Course> courseList = new ArrayList<>();
		try {
			ResultSet RS = db.getCoursesFromABETid(ABETid);
			while (RS.next()) {
				Course newCourse = builder.buildCourse(RS);
				courseList.add(newCourse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}
	

	/************ Instrument **************/

	// Get all Instruments in a list
	public ArrayList<Instrument> getAllInstruments() {
		ArrayList<Instrument> instrumentsList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllInstruments();
			while (RS.next()) {
				Instrument newInstrument = builder.buildInstrument(RS);
				instrumentsList.add(newInstrument);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrumentsList;
	}
	
	
	//Get most recent instrument
	public Instrument getMostRecentInstrument() {
		Instrument instrument = null;
		ResultSet RS = db.getMostRecentInstrument();
		try {
			while(RS.next()) {
				instrument = builder.buildInstrument(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrument;
	}
	
	//Get instrument by ID
	public Instrument getInstrumentByID(String id) {
		Instrument instrument = null;
		ResultSet RS = db.getInstrumentByID(id);
		try {
			while(RS.next()) {
				instrument = builder.buildInstrument(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrument;
	}

	// Add new instrument
	public int addNewInstrument(String type, String description, String weight) {
		return db.addNewInstrument(type, description, weight);
	}
	
	//Get instruments from ABET class
	public ArrayList<Instrument> getInstrumentsFromABETid(String ABETid) {
		ArrayList<Instrument> instrumentsList = new ArrayList<>();
		try {
			ResultSet RS = db.getInstrumentsFromABETid(ABETid);
			while (RS.next()) {
				Instrument newInstrument = builder.buildInstrument(RS);
				instrumentsList.add(newInstrument);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrumentsList;
	}
	
	
	
	/************ Instrument to Class **************/
	//Connect most recent instrument ID to Class CRN with helper table
	public int connectInstrumentToClass(String instrumentID, String CRN) {
		return db.connectInstrumentToClass(instrumentID, CRN);
	}
	
	//Get all instruments from class CRN
	public ArrayList<Instrument> getInstrumentsFromCRN(String CRN) {
		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		ResultSet RS = db.getInstrumentsFromClassCRN(CRN);
		try {
			while(RS.next()) {
				Instrument newInstrument = builder.buildInstrument(RS);
				instrumentList.add(newInstrument);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrumentList;
	}
	
	//Get all instruments from learning outcome
		public ArrayList<Instrument> getInstrumentsFromLearningOutcomeID(String loID) {
			ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
			ResultSet RS = db.getInstrumentsFromLearningOutcomeID(loID);
			try {
				while(RS.next()) {
					Instrument newInstrument = builder.buildInstrument(RS);
					instrumentList.add(newInstrument);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return instrumentList;
		}
	
	
	/************ Instrument to Learning Outcome **************/
	public int connectInstrumentToLearningOutcome(String instrumentID, String learningOutcomeID) {
		return db.connectInstrumentToLearningOutcome(instrumentID, learningOutcomeID);
	}
	

	/************ Learning Outcomes **************/

	// Get all Learning Outcomes in a list
	public ArrayList<LearningOutcome> getAllLearningOutcomes() {
		ArrayList<LearningOutcome> learningOutcomesList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllLearningOutcomes();
			while (RS.next()) {
				LearningOutcome newLearningOutcome = builder.buildLearningOutcome(RS);
				learningOutcomesList.add(newLearningOutcome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return learningOutcomesList;
	}

	// Add new learning outcome
	public int addNewLearningOutcome(String description) {
		return db.addNewLearningOutcome(description);
	}

	// Get most recent learning outcome
	public LearningOutcome getMostRecentLearningOutcome() {
		LearningOutcome lo = null;
		ResultSet RS = db.getMostRecentLearningOutcome();
		try {
			while (RS.next()) {
				lo = builder.buildLearningOutcome(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lo;
	}
	
	//Get learning outcomes from ABET id
	public ArrayList<LearningOutcome> getLearningOutcomesFromABETid(String ABETid) {
		ArrayList<LearningOutcome> loList = new ArrayList<LearningOutcome>();
		ResultSet RS = db.getLearningOutcomesFromABETid(ABETid);
		try {
			while (RS.next()) {
				LearningOutcome newLO = builder.buildLearningOutcome(RS);
				loList.add(newLO);
			}
		} catch (SQLException e) {
			return null;
		}
		return loList;
	}

	/************ Learning Outcome to Course **************/
	// Connect most recent learning outcome ID with course ID in helper table
	public int connectLearningOutcomeToCourse(String learningOutcomeID, String courseID) {
		return db.connectLearningOutcomeToCourse(learningOutcomeID, courseID);
	}

	// Get learning outcomes by course ID
	public ArrayList<LearningOutcome> getLearningOutcomesFromCourseID(String courseID) {
		ArrayList<LearningOutcome> loList = new ArrayList<LearningOutcome>();
		ResultSet RS = db.getLearningOutcomesFromCourseID(courseID);
		try {
			while (RS.next()) {
				LearningOutcome newLO = builder.buildLearningOutcome(RS);
				loList.add(newLO);
			}
		} catch (SQLException e) {
			return null;
		}
		return loList;
	}

	/************ Learning Outcome to ABET **************/
	// Connect most recent learning outcome ID with ABET ID in helper table
	public int connectLearningOutcomeToABET(String learningOutcomeID, String ABETid) {
		return db.connectLearningOutcomeToABET(learningOutcomeID, ABETid);
	}

	/************ Login **************/

	// Get all Logins in a list
	public ArrayList<Login> getAllLogins() {
		ArrayList<Login> loginsList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllLogins();
			while (RS.next()) {
				Login newLogin = builder.buildLogin(RS);
				loginsList.add(newLogin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginsList;
	}

	// Validate login
	public boolean validateLogin(String username, String password) {
		password = sha256(password);
		DbAccessImpl.connect();
		boolean status = false;
		try {
			ResultSet RS = db.validateLogin(username, password);
			if (RS.next()) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	// Hash password
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	// Add new login
	public int addNewLogin(String username, String password, String isAdmin) {
		password = sha256(password);
		return db.addNewLogin(username, password, isAdmin);
	}

	/************ Login to Administrator **************/
	// Get latest padmin and latest login and connect them in a helper table
	public int connectLoginToAdminstrator() {
		ResultSet adminRS = db.getMostRecentAdministrator();
		ResultSet loginRS = db.getMostRecentLogin();
		String adminID = "";
		String loginID = "";
		try {
			while (adminRS.next() && loginRS.next()) {
				// Create objects and retreive their IDs
				Administrator admin = builder.buildAdministrator(adminRS);
				adminID = admin.getId();
				Login login = builder.buildLogin(loginRS);
				loginID = login.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Pass IDs to helper table
		return db.connectLoginToAdministrator(loginID, adminID);
	}

	/************ Login to Professor **************/

	// Get latest professor and latest login and connect them in a helper table
	public int connectLoginToProfessor() {
		ResultSet profRS = db.getMostRecentProfessor();
		ResultSet loginRS = db.getMostRecentLogin();
		String professorID = "";
		String loginID = "";
		try {
			while (profRS.next() && loginRS.next()) {
				// Create objects and retreive their IDs
				Professor professor = builder.buildProfessor(profRS);
				professorID = professor.getId();
				Login login = builder.buildLogin(loginRS);
				loginID = login.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Pass IDs to helper table
		return db.connectLoginToProfessor(loginID, professorID);
	}

	/************ Mission Objectives **************/

	// Get all Mission Objectives in a list
	public ArrayList<MissionObjective> getAllMissionObjectives() {
		ArrayList<MissionObjective> missionObjectivesList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllMissionObjectives();
			while (RS.next()) {
				MissionObjective newMissionObjective = builder.buildMissionObjective(RS);
				missionObjectivesList.add(newMissionObjective);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return missionObjectivesList;
	}

	// Get all current mission objectives
	public ArrayList<MissionObjective> getAllCurrentMissionObjectives() {
		ArrayList<MissionObjective> missionObjectivesList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllCurrentMissionObjectives();
			while (RS.next()) {
				MissionObjective newMissionObjective = builder.buildMissionObjective(RS);
				missionObjectivesList.add(newMissionObjective);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return missionObjectivesList;
	}

	// Add new mission objective
	public int addNewMissionObjective(String description) {
		return db.addNewMissionObjective(description);
	}
	
	//Get mission obj from course ID
	public ArrayList<MissionObjective> getMissionObjectivesFromCourseID(String courseID) {
		ArrayList<MissionObjective> missionObjectivesList = new ArrayList<>();
		try {
			ResultSet RS = db.getMissionObjectivesFromCourseID(courseID);
			while (RS.next()) {
				MissionObjective newMissionObjective = builder.buildMissionObjective(RS);
				missionObjectivesList.add(newMissionObjective);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return missionObjectivesList;
	}
	
	//Get mission obj from LO ID
		public ArrayList<MissionObjective> getMissionObjectivesFromLearningOutcomeID(String loID) {
			ArrayList<MissionObjective> missionObjectivesList = new ArrayList<>();
			try {
				ResultSet RS = db.getMissionObjectivesFromLearningOutcomeID(loID);
				while (RS.next()) {
					MissionObjective newMissionObjective = builder.buildMissionObjective(RS);
					missionObjectivesList.add(newMissionObjective);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return missionObjectivesList;
		}

	/************ Professors **************/

	// Get all Professor in a list
	public ArrayList<Professor> getAllProfessors() {
		ArrayList<Professor> professorsList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllProfessors();
			while (RS.next()) {
				Professor newProfessor = builder.buildProfessor(RS);
				professorsList.add(newProfessor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professorsList;
	}

	// Get professor by ID
	public Professor getProfessorByID(String id) {
		Professor prof = null;
		ResultSet RS = db.getProfessorByID(id);
		try {
			while (RS.next()) {
				prof = builder.buildProfessor(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prof;
	}

	// Get all professors by last name
	public ArrayList<Professor> getAllProfessorsByLastName() {
		ArrayList<Professor> professorsList = new ArrayList<>();
		try {
			ResultSet RS = db.getAllProfessorsByLastName();
			while (RS.next()) {
				Professor newProfessor = builder.buildProfessor(RS);
				professorsList.add(newProfessor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professorsList;
	}

	// Add new professor
	public int addNewProfessor(String firstName, String lastName, String email) {
		return db.addNewProfessor(firstName, lastName, email);
	}

	/************ Professor to Class **************/
	public int connectProfessorToClass(String profID, String CRN) {
		return db.connectProfessorToClass(profID, CRN);
	}
}

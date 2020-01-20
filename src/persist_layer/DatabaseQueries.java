package persist_layer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseQueries {
	ResultSet RS;
	String input;
	String SQL;
	
	/************ Average **************/
	//Get assignment average from LO ID
	public ResultSet getAssignmentAverageFromLearningOutcomeID(String loID) {
		SQL = "SELECT FLOOR(AVG(average_grade)) FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id  = '"
	+loID + "'))))))))";
		return DbAccessImpl.retrieve(SQL);
	
	}

	/************ ABET **************/
	// ABET get by ID
	public ResultSet getABETByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM ABET WHERE ABET_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// get ABET by category name
	public ResultSet getABETByCategoryName(String input) {
		this.input = input;
		SQL = "SELECT * FROM ABET WHERE category_name = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all ABET objectives
	public ResultSet getAllABET() {
		SQL = "SELECT * FROM ABET ORDER BY category_id, subcategory_id";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get all ABET for Statesboro classes
	

	// Get most recent ABET objective entry (highest row ID #)
	public ResultSet getMostRecentABET() {
		SQL = "SELECT * FROM ABET ORDER BY ABET_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// Insert new ABET requirement with just a description and category
	public int addNewABET(String description, String categoryName, String categoryID, String subcategoryID) {
		SQL = "INSERT INTO ABET(ABET_description, ABET_tag, " + "is_currently_used, category_id, category_name, "
				+ "subcategory_id) " + "VALUES ('" + description + "', '1', '1', '" + categoryID + "', '" + categoryName
				+ "', '" + subcategoryID + "');";
		return DbAccessImpl.create(SQL);
	}

	// Get subcategory IDs from category name
	public ResultSet getABETSubcategoryIDs(String categoryName) {
		SQL = "SELECT subcategory_id FROM ABET WHERE category_name='" + categoryName + "';";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get last subcategory ID from category name
	public ResultSet getABETLastSubcategoryID(String categoryName) {
		SQL = "SELECT subcategory_id FROM ABET WHERE category_name='" + categoryName + "' "
				+ "ORDER BY subcategory_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all objectives currently in use
	public ResultSet getAllCurrentABET() {
		SQL = "SELECT * FROM ABET WHERE is_currently_used = '1' ORDER BY category_id";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get one ABET from each category for header purposes
	public ResultSet getAllABETCategories() {
		SQL = "SELECT * FROM ABET GROUP BY category_id";
		return DbAccessImpl.retrieve(SQL);
	}
	
	
	//Get ABET from assignment ID
	public ResultSet getABETFromAssignmentID(String assignmentID) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = ANY(SELECT course_id FROM classes_to_course WHERE CRN = ANY(SELECT CRN FROM instrument_to_class WHERE instrument_id = ANY(SELECT instrument_id FROM assignment_to_instrument WHERE assignment_id = '"
				+ assignmentID + "')))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get ABET from instrument ID
	public ResultSet getABETFromInstrumentID(String instrumentID) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = ANY(SELECT course_id FROM classes_to_course WHERE CRN = ANY(SELECT CRN FROM instrument_to_class WHERE instrument_id = '" 
				+instrumentID + "'))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get ABET from ABET ID
	public ResultSet getABETFromID(String ABETid) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = '" + ABETid + "')";
		return DbAccessImpl.retrieve(SQL);
	}
	
	
	/************ ABET to Class **************/
	//Get ABET from class CRN
	public ResultSet getABETFromClassCRN(String CRN) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = ANY(SELECT course_id FROM classes_to_course WHERE CRN = '"
				+ CRN + "')))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	
	/************ ABET to Courses **************/
	//Get all ABET from Course ID
	public ResultSet getABETFromCourseID(String courseID) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = '"
				+courseID + "'))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	

	/************ ABET to Mission Objective **************/

	// Connect ABET to Mission Objectives with a helper class
	public int connectABETtoMissionObjective(String ABETid, String ABETtag, String missionObjectiveID) {
		SQL = "INSERT INTO ABET_to_mission_objective(ABET_id, ABET_tag, mission_objective_id) VALUES('" + ABETid
				+ "', '" + ABETtag + "', '" + missionObjectiveID + "')";
		return DbAccessImpl.create(SQL);
	}
	
	
	/************ ABET to Learning Outcome **************/
	//Get ABET list from learning outcome ID
	public ResultSet getABETFromLearningOutcomeID(String learningOutcomeID) {
		SQL = "SELECT * FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = '"
				+learningOutcomeID + "')";
		return DbAccessImpl.retrieve(SQL);
	}
	

	/************ Administrators **************/
	// Get all administrators
	public ResultSet getAllAdministrators() {
		SQL = "SELECT * FROM administrators";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get all admins alphabetical last name
	public ResultSet getAllAdministratorsAlphabetical() {
		SQL = "SELECT * FROM administrators ORDER BY last_name";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent administrator entry (highest row ID #)
	public ResultSet getMostRecentAdministrator() {
		SQL = "SELECT * FROM administrators ORDER BY administrator_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get administrators by ID
	public ResultSet getAdministratorsByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM administrators WHERE administrator_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new administrator
	public int addNewAdministrator(String firstName, String lastName, String email) {
		SQL = "INSERT INTO administrators(first_name, last_name, email) VALUES('" + firstName + "', '" + lastName
				+ "', '" + email + "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Assignments **************/
	// Get all assignments
	public ResultSet getAllAssignments() {
		SQL = "SELECT * FROM assignments";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent assignment entry (highest row ID #)
	public ResultSet getMostRecentAssignment() {
		SQL = "SELECT * FROM assignments ORDER BY assignment_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get assignment by ID
	public ResultSet getAssignmentsByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM assignments WHERE assignment_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new assignment
	public int addNewAssignment(String lowPDF, String medPDF, String highPDF, String lowGrade, String medGrade,
			String highGrade, String avgGrade) {
		SQL = "INSERT INTO assignments(lowest_pdf, median_pdf, highest_pdf, lowest_grade, median_grade, highest_grade, average_grade) VALUES('"
				+ lowPDF + "', '" + medPDF + "', '" + highPDF + "', '" + lowGrade + "', '" + medGrade + "', '"
				+ highGrade + "', '" + avgGrade + "')";
		return DbAccessImpl.create(SQL);
	}
	
	//Get assignments from mission objective ID
	public ResultSet getAssignmentFromMissionObjectiveID(String moID) {
		SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = ANY(SELECT ABET_id FROM ABET WHERE ABET_id =ANY(SELECT ABET_id FROM ABET_to_mission_objective WHERE mission_objective_id =ANY(SELECT mission_objective_id FROM mission_objectives WHERE mission_objective_id = '"
				+moID + "'))))))))))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get assignments from ABET ID
	public ResultSet getAssignmentsFromABETid(String ABETid) {
		SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
				+ABETid + "')))))))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get assignments from ABET ID Statesboro
		public ResultSet getAssignmentsFromABETidStatesboro(String ABETid) {
			SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE campus='Statesboro' AND CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
					+ABETid + "')))))))))";
			return DbAccessImpl.retrieve(SQL);
		}
		
		//Get assignments from ABET ID Savannah
		public ResultSet getAssignmentsFromABETidSavannah(String ABETid) {
			SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE campus='Savannah' AND CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
					+ABETid + "')))))))))";
			return DbAccessImpl.retrieve(SQL);
		}
		
		//Get assignments from ABET ID
		public ResultSet getAssignmentsFromABETidHinesville(String ABETid) {
			SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = ANY(SELECT instrument_id FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE campus='Hinesville' AND CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
					+ABETid + "')))))))))";
			return DbAccessImpl.retrieve(SQL);
		}
	
	
	/************ Assignment to Instrument **************/
	//Connect most recent assignment with instrument ID
	public int connectAssignmentToInstrument(String assID, String instrumentID) {
		SQL = "INSERT INTO assignment_to_instrument(assignment_id, instrument_id) VALUES('"
				+ assID + "', '" + instrumentID + "')";
		return DbAccessImpl.create(SQL);
	}
	
	//Get assignment from instrument ID
	public ResultSet getAssignmentsFromInstrumentID(String instrumentID) {
		SQL = "SELECT * FROM assignments WHERE assignment_id = ANY(SELECT assignment_id FROM assignment_to_instrument WHERE instrument_id = '"
				+ instrumentID + "')";
		return DbAccessImpl.retrieve(SQL);
	}
	
	

	/************ Class **************/
	// Get classes by name
	public ResultSet getClassesByName(String input) {
		this.input = input;
		SQL = "SELECT * FROM classes WHERE name LIKE \"%" + input + "%\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all classes
	public ResultSet getAllClasses() {
		SQL = "SELECT * FROM classes";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all classes in alphabetical order
	public ResultSet getAllClassesAlphabetical() {
		SQL = "SELECT * FROM classes ORDER BY name";
		return DbAccessImpl.retrieve(SQL);
	}

	// get class by CRN
	public ResultSet getClassByCRN(String input) {
		this.input = input;
		SQL = "SELECT * FROM classes WHERE CRN = '" + input + "'";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add a new class
	public int addNewClass(String CRN, String courseNum, String dept, String section, String name, String semester,
			String year, String campus) {
		SQL = "INSERT INTO classes(CRN, course_number, department, section, name, semester, year, campus, grade) VALUES('"
				+ CRN + "', '" + courseNum + "', '" + dept + "', '" + section + "', '" + name + "', '" + semester
				+ "', '" + year + "', '" + campus + "', '100')";
		return DbAccessImpl.create(SQL);
	}
	
	//Get classes from ABET ID
	public ResultSet getClassesFromABETid(String ABETid) {
		SQL = "SELECT * FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '" 
				+ABETid + "')))))";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Class to Course **************/
	// Connect class CRN and course ID with helper table
	public int connectClassToCourse(String CRN, String courseID) {
		SQL = "INSERT INTO classes_to_course(CRN, course_id) VALUES('" + CRN + "', '" + courseID + "')";
		return DbAccessImpl.create(SQL);
	}

	// Get course from class CRN
	public ResultSet getCourseFromClassCRN(String CRN) {
		SQL = "SELECT * FROM courses WHERE course_id = (SELECT course_id FROM classes_to_course WHERE CRN = '" + CRN
				+ "')";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Class to Learning Outcomes **************/
	// Get learning outcomes for a single class CRN
	public ResultSet getLearningOutcomesFromClassCRN(String CRN) {
		SQL = "SELECT * FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = (SELECT course_id FROM courses WHERE course_id = (SELECT course_id FROM aadb.classes_to_course WHERE CRN = '"
				+ CRN + "')))";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Course **************/
	// Get all courses
	public ResultSet getAllCourses() {
		SQL = "SELECT * FROM courses";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent course entry (highest row ID #)
	public ResultSet getMostRecentCourse() {
		SQL = "SELECT * FROM courses ORDER BY course_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get course by ID
	public ResultSet getCourseByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM courses WHERE course_id = '" + input + "'";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new course
	public int addNewCourse(String courseNumber, String name, String department) {
		SQL = "INSERT INTO courses(course_number, course_name, department) VALUES('" + courseNumber + "', '" + name
				+ "', '" + department + "')";
		return DbAccessImpl.create(SQL);
	}

	// Get all courses sorted by name
	public ResultSet getAllCoursesAlphabetically() {
		SQL = "SELECT * FROM courses ORDER BY course_name";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get courses from ABET ID
	public ResultSet getCoursesFromABETid(String ABETid) {
		SQL = "SELECT * FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
				+ ABETid + "')))";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Instruments **************/
	// Get all instruments
	public ResultSet getAllInstruments() {
		SQL = "SELECT * FROM instruments";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent instrument entry (highest row ID #)
	public ResultSet getMostRecentInstrument() {
		SQL = "SELECT * FROM instruments ORDER BY instrument_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get instrument by ID
	public ResultSet getInstrumentByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM instruments WHERE instrument_id = '" + input + "'";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new instrument
	public int addNewInstrument(String type, String description, String weight) {
		SQL = "INSERT INTO instruments(instrument_grade_average, instrument_type, instrument_description, instrument_weight) VALUES('100', '"
				+ type + "', '" + description + "', '" + weight + "')";
		return DbAccessImpl.create(SQL);
	}
	
	//Get instruments from ABET id
	public ResultSet getInstrumentsFromABETid(String ABETid) {
		SQL = "SELECT * FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
				+ABETid + "')))))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get instruments from lo ID 
	public ResultSet getInstrumentsFromLearningOutcomeID(String loID) {
		SQL = "SELECT * FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = ANY(SELECT CRN FROM classes WHERE CRN = ANY(SELECT CRN FROM classes_to_course WHERE course_id = ANY(SELECT course_id FROM courses WHERE course_id = ANY(SELECT course_id FROM learning_outcome_to_course WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcomes WHERE learning_outcome_id = '"
	+loID + "'))))))";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Instrument to Class **************/
	// Connect new instrument ID with class CRN
	public int connectInstrumentToClass(String instrumentID, String CRN) {
		SQL = "INSERT INTO instrument_to_class(instrument_id, CRN) VALUES('" + instrumentID + "', '" + CRN + "')";
		return DbAccessImpl.create(SQL);
	}

	// Get instruments from class CRN
	public ResultSet getInstrumentsFromClassCRN(String CRN) {
		SQL = "SELECT * FROM instruments WHERE instrument_id = ANY(SELECT instrument_id FROM instrument_to_class WHERE CRN = '"
				+ CRN + "')";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Instrument to Learning Outcome **************/
	// Connect new instrument ID with specific class learning outcome ID
	public int connectInstrumentToLearningOutcome(String instrumentID, String learningOutcomeID) {
		SQL = "INSERT INTO instrument_to_learning_outcome(instrument_id, learning_outcome_id) VALUES('" + instrumentID
				+ "', '" + learningOutcomeID + "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Learning Outcomes **************/
	// Get all learning outcomes
	public ResultSet getAllLearningOutcomes() {
		SQL = "SELECT * FROM learning_outcomes";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent learning outcome entry (highest row ID #)
	public ResultSet getMostRecentLearningOutcome() {
		SQL = "SELECT * FROM learning_outcomes ORDER BY learning_outcome_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get learning outcome by ID
	public ResultSet getLearningOutcomeByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM learning_outcomes WHERE learning_outcome_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new learning outcome
	public int addNewLearningOutcome(String description) {
		String tag = "1";
		SQL = "INSERT INTO learning_outcomes(learning_outcome_description, learning_outcome_year, learning_outcome_tag, is_currently_used) VALUES('"
				+ description + "', DATE(NOW()), '" + tag + "', '1')";
		return DbAccessImpl.create(SQL);
	}
	
	//Get learning outcomes from ABET ID
	public ResultSet getLearningOutcomesFromABETid(String ABETid) {
		SQL = "SELECT * FROM learning_outcomes WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_ABET WHERE ABET_id = '"
				+ ABETid + "')";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Learning Outcome to Course **************/
	// Connect most recent learning outcome ID with course ID in helper table
	public int connectLearningOutcomeToCourse(String learningOutcomeID, String courseID) {
		SQL = "INSERT INTO learning_outcome_to_course(learning_outcome_id, course_id) VALUES('" + learningOutcomeID
				+ "', '" + courseID + "')";
		return DbAccessImpl.create(SQL);
	}

	// Get learning outcomes by course ID
	public ResultSet getLearningOutcomesFromCourseID(String courseID) {
		SQL = "SELECT * FROM learning_outcomes WHERE learning_outcome_id ="
				+ " ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = '" + courseID
				+ "');";
		return DbAccessImpl.retrieve(SQL);
	}

	/************ Learning Outcome to ABET **************/
	// Connect most recent learning outcome ID with ABET ID in helper table
	public int connectLearningOutcomeToABET(String learningOutcomeID, String ABETid) {
		SQL = "INSERT INTO learning_outcome_to_ABET(learning_outcome_id, ABET_id) VALUES('" + learningOutcomeID + "', '"
				+ ABETid + "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Login **************/
	// Validate login
	public ResultSet validateLogin(String username, String password) {
		SQL = "SELECT * FROM logins WHERE username = \"" + username + "\" and password = \"" + password + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all logins
	public ResultSet getAllLogins() {
		SQL = "SELECT * FROM logins";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent login entry (highest row ID #)
	public ResultSet getMostRecentLogin() {
		SQL = "SELECT * FROM logins ORDER BY login_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get login by ID
	public ResultSet getLoginByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM logins WHERE login_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Insert new login
	public int addNewLogin(String username, String password, String isAdmin) {
		SQL = "INSERT INTO logins(username, password, is_admin) VALUES('" + username + "', '" + password + "', '"
				+ isAdmin + "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Login to Administrator **************/
	// Connect most recent login ID and admin ID to helper table
	public int connectLoginToAdministrator(String loginID, String adminID) {
		SQL = "INSERT INTO logins_to_administrators(administrator_id, login_id) VALUES('" + adminID + "', '" + loginID
				+ "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Login to Professor **************/
	// Connect most recent login ID and professor ID to helper table
	public int connectLoginToProfessor(String loginID, String professorID) {
		SQL = "INSERT INTO logins_to_professors(login_id, professor_id) VALUES('" + loginID + "', '" + professorID
				+ "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Mission Objectives **************/
	// Get all mission objectives
	public ResultSet getAllMissionObjectives() {
		SQL = "SELECT * FROM mission_objectives";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent mission objective entry (highest row ID #)
	public ResultSet getMostRecentMissionObjective() {
		SQL = "SELECT * FROM mission_objectives ORDER BY mission_objective_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get mission objective by ID
	public ResultSet getMissionObjectiveByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM mission_objectives WHERE mission_objective_id = \"" + input + "\"";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new mission objective
	public int addNewMissionObjective(String description) {
		String currentTag = "1";
		SQL = "INSERT INTO mission_objectives(mission_objective_description, mission_objective_year, is_currently_used, mission_objective_tag) VALUES('"
				+ description + "', DATE(NOW()), '1', '" + currentTag + "')";
		return DbAccessImpl.create(SQL);
	}

	// Get all mission objectives that are currently used
	public ResultSet getAllCurrentMissionObjectives() {
		SQL = "SELECT * FROM mission_objectives WHERE is_currently_used = '1'";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get mission objective from assignment ID
	public ResultSet getMissionObjectivesFromAssignmentID(String assID) {
		SQL = "SELECT * FROM mission_objectives WHERE mission_objective_id = ANY(SELECT mission_objective_id FROM ABET_to_mission_objective WHERE ABET_id = ANY(SELECT ABET_id FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = ANY(SELECT course_id FROM classes_to_course WHERE CRN = ANY(SELECT CRN FROM instrument_to_class WHERE instrument_id = ANY(SELECT instrument_id FROM assignment_to_instrument WHERE assignment_id = '"
				+assID + "')))))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get mission obj from course ID
	public ResultSet getMissionObjectivesFromCourseID(String courseID) {
		SQL = "SELECT * FROM mission_objectives WHERE mission_objective_id = ANY(SELECT mission_objective_id FROM ABET_to_mission_objective WHERE ABET_id = ANY(SELECT ABET_id FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id = ANY(SELECT learning_outcome_id FROM learning_outcome_to_course WHERE course_id = '"
				+courseID +"'))))";
		return DbAccessImpl.retrieve(SQL);
	}
	
	//Get mission obj from LO ID
		public ResultSet getMissionObjectivesFromLearningOutcomeID(String loID) {
			SQL = "SELECT * FROM mission_objectives WHERE mission_objective_id = ANY(SELECT mission_objective_id FROM ABET_to_mission_objective WHERE ABET_id = ANY(SELECT ABET_id FROM ABET WHERE ABET_id = ANY(SELECT ABET_id FROM learning_outcome_to_ABET WHERE learning_outcome_id =  '"
					+loID + "')))";
			return DbAccessImpl.retrieve(SQL);
		}

	/************ Professors **************/
	// Get all professors
	public ResultSet getAllProfessors() {
		SQL = "SELECT * FROM professors";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get all professors by last name
	public ResultSet getAllProfessorsByLastName() {
		SQL = "SELECT * FROM professors ORDER BY last_name";
		return DbAccessImpl.retrieve(SQL);
	}

	// Get most recent professor entry (highest row ID #)
	public ResultSet getMostRecentProfessor() {
		SQL = "SELECT * FROM professors ORDER BY professor_id DESC LIMIT 1;";
		return DbAccessImpl.retrieve(SQL);
	}

	// get professor by ID
	public ResultSet getProfessorByID(String input) {
		this.input = input;
		SQL = "SELECT * FROM professors WHERE professor_id = '" + input + "'";
		return DbAccessImpl.retrieve(SQL);
	}

	// Add new professor
	public int addNewProfessor(String firstName, String lastName, String email) {
		SQL = "INSERT INTO professors(first_name, last_name, email) VALUES('" + firstName + "', '" + lastName + "', '"
				+ email + "')";
		return DbAccessImpl.create(SQL);
	}

	/************ Professor to Class **************/
	// Connect professor ID and CRN with helper class
	public int connectProfessorToClass(String profID, String CRN) {
		SQL = "INSERT INTO professor_to_class(professor_id, CRN) VALUES('" + profID + "', '" + CRN + "')";
		return DbAccessImpl.create(SQL);
	}
}

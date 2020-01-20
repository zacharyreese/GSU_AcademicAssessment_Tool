package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logic_layer.DatabaseLogic;
import object_layer.ABET;
import object_layer.Administrator;
import object_layer.Assignment;
import object_layer.Class;
import object_layer.Course;
import object_layer.Instrument;
import object_layer.LearningOutcome;
import object_layer.MissionObjective;
import object_layer.Professor;
import persist_layer.DbAccessImpl;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/mainServlet")
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Configuration cfg;
	private String templateDir = "/WEB-INF/templates";
	private String graphDir = "/WEB-INF/templates/Graphs";
	DbAccessImpl DbAccess = new DbAccessImpl();

	public mainServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// Initialize servlet
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_28);
		DbAccess.connect();

		File file = new File(getServletContext().getRealPath(templateDir));
		try {
			cfg.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // Create writer
		Map<String, Object> root = new HashMap<>(); // Hashmap for .ftl pages
		DatabaseLogic db = new DatabaseLogic(); // Object to call methods from DatabaseLogic

		/************ Test **************/
		if (request.getParameter("gotoTest") != null) {
			int sum = 0;
			ArrayList<Integer> avgList = new ArrayList<Integer>();
			ArrayList<Assignment> assList = new ArrayList<Assignment>();
			ArrayList<ABET> ABETlist = db.getAllABET();
			for (int i = 0; i < ABETlist.size(); i++) {
				sum = 0;
				assList = db.getAssignmentsFromABETid(ABETlist.get(i).getId());
				if (assList.size() > 0) {
					for (int j = 0; j < assList.size(); j++) {
						sum += Integer.parseInt(assList.get(j).getAverageGrade());
					}
					sum = sum / assList.size();
				}
				avgList.add(sum);
			}
		}
		
		
		

		/************ ABET **************/

		// Add a new ABET requirement to the database
		if (request.getParameter("addNewABET") != null) {
			String description = request.getParameter("ABET_description");
			String categoryName = request.getParameter("ABETCategoryName");
			String success = "";

			// Get list of current mission objectives to determine ABET to Mission Objective
			// link
			ArrayList<MissionObjective> moList = new ArrayList<>(db.getAllCurrentMissionObjectives());

			// If new ABET insert, connect ABET to mission objectives
			if (db.addNewABET(description, categoryName) != 0) {
				for (int i = 0; i < moList.size(); i++) {
					if (request.getParameter(String.valueOf(i + 1)) != null) {
						String moID = moList.get(i).getId();
						ABET newABET = db.getMostRecentABET();
						String id = newABET.getId();
						String tag = newABET.getTag();
						db.connectABETtoMissionObjective(id, tag, moID);
					}
				}
				success = "Successfully added ABET";
			} else {
				success = "Erroor adding ABET";
			}
			ArrayList<ABET> ABETList = db.getAllABET();
			root.put("ABETs", ABETList);
			ArrayList<ABET> categoryList = db.getAllABETCategories();
			root.put("categories", categoryList);
			String templateName = "ABET.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to insert new ABET page
		if (request.getParameter("gotoInsertNewABET") != null) {
			ArrayList<MissionObjective> moList = new ArrayList<MissionObjective>(db.getAllCurrentMissionObjectives());
			root.put("missionObjectives", moList);
			String templateName = "NewABET.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Show all of the ABET requirements
		if (request.getParameter("gotoABET") != null) {
			ArrayList<ABET> ABETList = new ArrayList<ABET>(db.getAllABET());
			root.put("ABETs", ABETList);
			String templateName = "ABET.ftl";
			ArrayList<ABET> categoryList = db.getAllABETCategories();
			root.put("categories", categoryList);
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Administrators **************/

		// Add new admin and add login for that admin
		if (request.getParameter("addNewAdmin") != null) {
			String firstName = request.getParameter("admin_firstname");
			String lastName = request.getParameter("admin_lastname");
			String email = request.getParameter("admin_email");
			String password = request.getParameter("admin_password");
			ArrayList<Administrator> adminList = db.getAllAdministratorsAlphabetical();
			root.put("administrators", adminList);
			String success = "";
			if (db.addNewAdministrator(firstName, lastName, email) != 0) {
				if (db.addNewLogin(email, password, "1") != 0) {
					// Link admin and login in helper table
					if (db.connectLoginToAdminstrator() != 0) {
						success = "Successfully added new admin";
					}
				} else {
					success = "Error adding admin";
				}
			}
			root.put("success", success);
			String templateName = "Administrators.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to administrators page
		if (request.getParameter("gotoAdministrators") != null) {
			ArrayList<Administrator> adminList = db.getAllAdministratorsAlphabetical();
			root.put("administrators", adminList);
			String templateName = "Administrators.ftl";
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new administrator page
		if (request.getParameter("gotoAddNewAdministrator") != null) {
			ArrayList<Administrator> adminList = db.getAllAdministratorsAlphabetical();
			root.put("administrators", adminList);
			String templateName = "NewAdmin.ftl";
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Assignments **************/
		// Go to assignments page for specific instrument
		if (request.getParameter("gotoAssignments") != null) {
			String instrumentID = request.getParameter("gotoAssignments");
			Instrument instrument = db.getInstrumentByID(instrumentID);
			root.put("instrument", instrument);
			ArrayList<Assignment> assList = db.getAssignmentsFromInstrumentID(instrument.getId());
			root.put("assignments", assList);
			String templateName = "Assignments.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new assignment page
		if (request.getParameter("gotoAddNewAssignment") != null) {
			String instrumentID = request.getParameter("instrumentID");
			Instrument instrument = db.getInstrumentByID(instrumentID);
			root.put("instrument", instrument);

			String templateName = "NewAssignment.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Add new assignment
		if (request.getParameter("addNewAssignment") != null) {
			String instrumentID = request.getParameter("addNewAssignment");
			Instrument instrument = db.getInstrumentByID(instrumentID);
			root.put("instrument", instrument);
			String avgGrade = request.getParameter("avgGrade");
			String highGrade = request.getParameter("highGrade");
			String medianGrade = request.getParameter("medianGrade");
			String lowGrade = request.getParameter("lowGrade");
			String highPDF = request.getParameter("highPDF");
			String medianPDF = request.getParameter("medianPDF");
			String lowPDF = request.getParameter("lowPDF");
			String success;

			if (db.addNewAssignment(lowPDF, medianPDF, highPDF, lowGrade, medianGrade, highGrade, avgGrade) != 0) {
				Assignment ass = db.getMostRecentAssignment();
				// Connect assignment and instrument
				db.connectAssignmentToInstrument(ass.getId(), instrument.getId());
				success = "Successfully added new assignment";
				ArrayList<Assignment> assList = db.getAssignmentsFromInstrumentID(instrument.getId());
				root.put("assignments", assList);
			} else {
				success = "Error: could not add new assignment";
			}
			root.put("success", success);
			String templateName = "Assignments.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Classes **************/

		// Go to search page
		if (request.getParameter("search") != null) {
			response.sendRedirect("courseSearch.html"); // Send to HTML instead of freemarker
//			String templateName = "search.ftl"; // Specify freemarker template
//			Template template = cfg.getTemplate(templateName);
//			try {
//				template.process(root, out);
//			} catch (TemplateException e) {
//				e.printStackTrace();
//			}
		}

		// Class Search
		if (request.getParameter("classSearch") != null) {
			System.out.println(request.getParameter("classSearch"));

			String className = request.getParameter("className"); // Get search term
			ArrayList<Class> classList = new ArrayList<>(db.classSearchByName(className)); // Retrieve arraylist of all
																							// movies
																							// that contain search term

			root.put("classes", classList); // Put the classes into FreeMarker
			String templateName = "classSearch.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// View all classes
		if (request.getParameter("viewAllClasses") != null) {

			ArrayList<Class> classList = new ArrayList<>(db.getAllClasses()); // Retrieve ArrayList of all movies
			root.put("classes", classList); // Add arrayList to Freemarker
			String templateName = "Classes.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to class page
		if (request.getParameter("gotoClasses") != null) {

			ArrayList<Class> classList = new ArrayList<>(db.getAllClassesAlphabetical()); // Retrieve ArrayList of all
																							// movies
			root.put("classes", classList); // Add arrayList to Freemarker
			String templateName = "Classes.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new class page
		if (request.getParameter("gotoAddNewClass") != null) {
			// Add course and professor lists to freemarker
			ArrayList<Course> courseList = db.getAllCoursesAlphabetically();
			root.put("courses", courseList);
			ArrayList<Professor> profList = db.getAllProfessorsByLastName();
			root.put("professors", profList);

			String templateName = "NewClass.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Add new class
		if (request.getParameter("addNewClass") != null) {
			// Get input variables
			ArrayList<Professor> profList = db.getAllProfessorsByLastName();
			ArrayList<Course> courseList = db.getAllCoursesAlphabetically();
			String CRN = request.getParameter("CRN");
			String semester = request.getParameter("semester");
			String year = request.getParameter("year");
			String campus = request.getParameter("campus");
			String section = request.getParameter("section");
			String success;

			// Convert select course and professor into objects
			String courseID = request.getParameter("course");
			courseID = courseID.substring(6); // Strip 'course' and only get ID #
			Course course = db.getCourseByID(courseID);
			String courseNum = course.getCourseNumber();
			String courseDept = course.getDepartment();
			String courseName = course.getName();
			String professorID = request.getParameter("professor");
			professorID = professorID.substring(4);
			Professor prof = db.getProfessorByID(professorID);

			// Insert new class
			if (db.addNewClass(CRN, courseNum, courseDept, section, courseName, semester, year, campus) != 0) {
				// Iterate through courses and see which one was selected
				for (int i = 0; i < courseList.size(); i++) {
					if (request.getParameter("course").equals("course" + courseList.get(i).getId())) {
						// Connect class to course with helper table
						db.connectClassToCourse(CRN, courseList.get(i).getId());
					}
				}
				// Connect professor to CRN
				for (int i = 0; i < profList.size(); i++) {
					if (request.getParameter("professor").equals("prof" + profList.get(i).getId())) {
						db.connectProfessorToClass(profList.get(i).getId(), CRN);
					}
				}
				success = "Successfully added new class";
			} else {
				success = "Error adding class, possible duplicate CRN";
			}

			ArrayList<Class> classList = new ArrayList<>(db.getAllClassesAlphabetical()); // Retrieve ArrayList of all
																							// movies
			root.put("classes", classList); // Add arrayList to Freemarker
			root.put("success", success);
			String templateName = "Classes.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Courses **************/
		
		//Go to course details (grid) page
		if(request.getParameter("gotoCourseDetails") != null) {
			String courseID = request.getParameter("gotoCourseDetails");
			//Get all learning outcomes for course
			ArrayList<LearningOutcome> loList = db.getLearningOutcomesFromCourseID(courseID);
			//Loop through LO list and put lists in individualy with Lo ID attached to name
			for(int i = 0; i < loList.size(); i++) {
				String loID = loList.get(i).getId();
				String index = (i+1)+"";
				//Get ABET from lo.ID
				ArrayList<ABET> ABETList = db.getABETFromLearningOutcomeID(loID);
				ArrayList<MissionObjective> moList = db.getMissionObjectivesFromCourseID(courseID);
				ArrayList<Instrument> instrumentList = db.getInstrumentsFromLearningOutcomeID(loID);
				String average = db.getAssignmentAverageFromLearningOutcomeID(loID);
				root.put(("ABET" + index), ABETList);
				root.put(("MO") + index, moList);
				root.put(("Instruments" + index), instrumentList);
				root.put(("avg" + index), average);
			}
			
			
			root.put("learningOutcomes", loList);
			String templateName = "CourseDetails.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		

		// Go to courses page and load all courses
		if (request.getParameter("adminCourses") != null) {

			ArrayList<Course> courseList = new ArrayList<>(db.getAllCourses()); // Retrieve ArrayList of all movies
			root.put("courses", courseList); // Add arrayList to Freemarker
			String templateName = "Courses.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to courses page and load all courses
		if (request.getParameter("gotoCourses") != null) {

			ArrayList<Course> courseList = new ArrayList<>(db.getAllCoursesAlphabetically()); // Retrieve ArrayList of
																								// all movies
			root.put("courses", courseList); // Add arrayList to Freemarker
			String templateName = "Courses.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new course page
		if (request.getParameter("gotoAddNewCourse") != null) {
			String templateName = "NewCourse.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Add new course page
		if (request.getParameter("addNewCourse") != null) {
			String courseNumber = request.getParameter("courseNumber");
			String courseName = request.getParameter("courseName");
			String courseDepartment = request.getParameter("courseDepartment");
			String templateName = "";
			if (db.addNewCourse(courseNumber, courseName, courseDepartment) != 0) {
				templateName = "Courses.ftl";
				ArrayList<Course> courseList = new ArrayList<>(db.getAllCoursesAlphabetically()); // Retrieve ArrayList
																									// of all movies
				root.put("courses", courseList); // Add arrayList to Freemarker
				root.put("success", "Successfully added course");
			} else {
				templateName = "NewCourse.ftl"; // Specify freemarker template
				String success = "Error adding course";
				root.put("success", success);
			}
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Graphs **************/

		// Mega Grid
		if (request.getParameter("gotoMegaGrid") != null) {
			String templateName = "Graphs/MegaGrid.ftl";
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Basic Graphs page
		if (request.getParameter("overallGraph") != null) {
			ArrayList<Integer> avgList = db.getAllABETAverages();
			ArrayList<Integer> avgListStatesboro = db.getAllABETAveragesStatesboro();
			ArrayList<Integer> avgListSavannah = db.getAllABETAveragesSavannah();
			ArrayList<Integer> avgListHinesville = db.getAllABETAveragesHinesville();
			ArrayList<ABET> ABETList = db.getAllABET();
			root.put("ABETs", ABETList);
			root.put("averages", avgList);
			root.put("averagesStatesboro", avgListStatesboro);
			root.put("averagesSavannah", avgListSavannah);
			root.put("averagesHinesville", avgListHinesville);
			String templateName = "/Graphs/graph.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Basic Graphs page
		if (request.getParameter("graphs") != null) {
			String templateName = "graph.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// 2 Semesters Comparison Graphs page
		if (request.getParameter("timeComparisonGraph") != null) {
			String templateName = "comparisonGraph.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// 2000 Level and Lower Graphs page
		if (request.getParameter("courseComparisonGraph") != null) {
			String templateName = "2000lower.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Home **************/

		// Home
		if (request.getParameter("home") != null) {
			String templateName = "home.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			response.sendRedirect("home.html"); // Send to HTML instead of freemarker
			/*
			 * String templateName = "home.ftl"; // Specify freemarker template Template
			 * template = cfg.getTemplate(templateName); try { template.process(root, out);
			 * } catch (TemplateException e) { e.printStackTrace(); }
			 */
		}

		// Admin Home
		if (request.getParameter("adminHome") != null) {
			String templateName = "home.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			response.sendRedirect("adminHome.html"); // Send to HTML instead of freemarker
			/*
			 * String templateName = "home.ftl"; // Specify freemarker template Template
			 * template = cfg.getTemplate(templateName); try { template.process(root, out);
			 * } catch (TemplateException e) { e.printStackTrace(); }
			 */
		}

		/************ Instruments **************/
		// Go to instruments page for a specific class
		if (request.getParameter("gotoInstruments") != null) {
			String CRN = request.getParameter("gotoInstruments");
			Class newClass = db.getClassFromCRN(CRN);
			root.put("class", newClass);
			// Add existing instruments to freemarker
			ArrayList<Instrument> instrumentList = db.getInstrumentsFromCRN(CRN);
			root.put("instruments", instrumentList);
			String templateName = "Instruments.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add a new instrument page
		if (request.getParameter("gotoNewInstrument") != null) {
			String CRN = request.getParameter("gotoNewInstrument");
			Class newClass = db.getClassFromCRN(CRN);
			root.put("class", newClass);
			ArrayList<LearningOutcome> loList = db.getLearningOutcomesFromClassCRN(CRN);
			root.put("learningOutcomes", loList);
			String templateName = "NewInstrument.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Add a new instrument
		if (request.getParameter("addNewInstrument") != null) {
			String type = request.getParameter("instrumentType");
			String description = request.getParameter("description");
			String weight = request.getParameter("weight");
			String success = "";

			// Get class and learning outcome objects
			String CRN = request.getParameter("classCRN");
			Class newClass = db.getClassFromCRN(CRN);
			root.put("class", newClass);
			ArrayList<LearningOutcome> loList = db.getLearningOutcomesFromClassCRN(CRN);

			if (db.addNewInstrument(type, description, weight) != 0) {
				// Connect new instrument with class and learning outcomes
				Instrument instrument = db.getMostRecentInstrument();
				// Connect instrument with class
				db.connectInstrumentToClass(instrument.getId(), newClass.getCRN());
				// Connect instrument to class specific learning outcomes
				for (int i = 0; i < loList.size(); i++) {
					if (request.getParameter(loList.get(i).getId()) != null) {
						db.connectInstrumentToLearningOutcome(instrument.getId(), loList.get(i).getId());
					}
				}
				success = "Successfully added new instrument";
				// Add existing instruments to freemarker
				ArrayList<Instrument> instrumentList = db.getInstrumentsFromCRN(CRN);
				root.put("instruments", instrumentList);
			} else {
				success = "Error: could not add instrument";
			}
			root.put("success", success);
			String templateName = "Instruments.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Learning Outcomes **************/

		// Go to learning outcomes for a specific course
		if (request.getParameter("gotoLearningOutcomes") != null) {
			// Get courseID from webpage and create course object
			String courseID = request.getParameter("gotoLearningOutcomes");
			Course course = db.getCourseByID(courseID);
			root.put("course", course);
			// Get list of learning outcomes by course ID
			ArrayList<LearningOutcome> loList = db.getLearningOutcomesFromCourseID(courseID);
			root.put("learningOutcomes", loList);
			String templateName = "LearningOutcomes.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add a new learning objective page and pass course object into ftl
		if (request.getParameter("gotoNewLearningOutcome") != null) {
			// Get courseID from webpage and create course object
			String courseID = request.getParameter("gotoNewLearningOutcome");
			Course course = db.getCourseByID(courseID);
			root.put("course", course);
			// Get all current ABET requirements to map
			ArrayList<ABET> ABETlist = db.getAllCurrentABET();
			root.put("ABETlist", ABETlist);
			ArrayList<ABET> categoryList = db.getAllABETCategories();
			root.put("categories", categoryList);

			String templateName = "NewLearningOutcome.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Add new learning outcome and connect it with course and ABET requirements
		if (request.getParameter("addNewLearningOutcome") != null) {
			String description = request.getParameter("learningOutcomeDescription");
			// Get courseID from webpage and create course object
			String courseID = request.getParameter("courseID");
			Course course = db.getCourseByID(courseID);
			root.put("course", course);

			// Get checked ABET requirements
			ArrayList<ABET> ABETlist = db.getAllCurrentABET();

			// Insert new learning outcome
			if (db.addNewLearningOutcome(description) != 0) {
				// Connect learning outcome and course with helper table
				LearningOutcome lo = db.getMostRecentLearningOutcome(); // Get ID from previous insert
				String learningOutcomeID = lo.getId();
				db.connectLearningOutcomeToCourse(learningOutcomeID, courseID);

				// Connect learning outcome and ABET requirements with helper table
				for (int i = 0; i < ABETlist.size(); i++) {
					String ABETid = ABETlist.get(i).getId();
					if (request.getParameter(ABETid) != null) {
						db.connectLearningOutcomeToABET(learningOutcomeID, ABETid);
					}
				}
			}
			// Return to course page with updated learning outcomes
			ArrayList<LearningOutcome> loList = db.getLearningOutcomesFromCourseID(courseID);
			root.put("learningOutcomes", loList);
			String success = "Successfully added new Learning Outcome";
			root.put("success", success);
			String templateName = "LearningOutcomes.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template

			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		

		/************ Login **************/

		// Login
		if (request.getParameter("submit") != null) {
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");
			// Confirms login is valid and allows entry
			if (db.validateLogin(username, password)) {
				response.sendRedirect("adminAddRemove.html"); // Send to HTML instead of freemarker
				/*
				 * String templateName = "home.ftl"; // Specify freemarker template Template
				 * template = cfg.getTemplate(templateName); try { template.process(root, out);
				 * } catch (TemplateException e) { e.printStackTrace(); }
				 */

				// out.println("<html><h1><font color='green'><p
				// align=\"right\">Successful</h1></html>");
				// out.println("<html><h1><font color='green'><p align=\"right\"> Welcome " +
				// username + "</font></h1></html>");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
				out.println("<html><h1>Incorrect username or password</h1></html>");
				out.println("<html><h1>Please try again</h1></html>");
			}
		}

		/************ Mission Objectives **************/
		// Add new mission objective
		if (request.getParameter("addNewMissionObjective") != null) {
			String description = request.getParameter("mission_objective_description");
			if (db.addNewMissionObjective(description) != 0) {
				String success = "Mission Objective successfully added";
				root.put("success", success);
			} else {
				String success = "Error adding Mission Objective";
				root.put("success", success);
			}
			String templateName = "MissionObjectives.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			ArrayList<MissionObjective> moList = new ArrayList<>(db.getAllCurrentMissionObjectives());
			root.put("missionObjectives", moList);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to mission objective page
		if (request.getParameter("gotoMissionObjective") != null) {
			String templateName = "MissionObjectives.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			ArrayList<MissionObjective> moList = new ArrayList<>(db.getAllCurrentMissionObjectives());
			root.put("missionObjectives", moList);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new mission objective
		if (request.getParameter("gotoInsertNewMissionObjective") != null) {
			String templateName = "NewMissionObjective.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			ArrayList<MissionObjective> moList = new ArrayList<>(db.getAllCurrentMissionObjectives());
			root.put("missionObjectives", moList);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		/************ Professors **************/

		// Add new professor and add login for that professor
		if (request.getParameter("addNewProfessor") != null) {
			String firstName = request.getParameter("professor_firstname");
			String lastName = request.getParameter("professor_lastname");
			String email = request.getParameter("professor_email");
			String password = request.getParameter("professor_password");
			String success = "";
			if (db.addNewProfessor(firstName, lastName, email) != 0) {
				if (db.addNewLogin(email, password, "0") != 0) {
					// Link prof and login in helper table
					if (db.connectLoginToProfessor() != 0) {
						success = "Successfully added new professor";
						ArrayList<Professor> profList = db.getAllProfessorsByLastName();
						root.put("professors", profList);
					}
				} else {
					success = "Error adding professor";
				}
			}
			root.put("success", success);
			String templateName = "Professors.ftl"; // Specify freemarker template
			Template template = cfg.getTemplate(templateName);
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to professors page
		if (request.getParameter("gotoProfessors") != null) {
			ArrayList<Professor> profList = db.getAllProfessorsByLastName();
			root.put("professors", profList);
			String templateName = "Professors.ftl";
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

		// Go to add new professors page
		if (request.getParameter("gotoAddNewProfessor") != null) {
			ArrayList<Professor> profList = db.getAllProfessorsByLastName();
			root.put("professors", profList);
			String templateName = "NewProfessor.ftl";
			Template template = cfg.getTemplate(templateName); // Specify freemarker template
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

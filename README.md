# Academic Assessment Tool
>A JSP-based tool for tracking ABET certification performance of the Georgia Southern University courses
![](https://i.imgur.com/aZpfFei.png)
![](https://i.imgur.com/wROBrKt.png)
![](https://i.imgur.com/zkZQBTv.png)

## About

The Academic Assessment Tool or 'AA' Tool was a team capstone project for CSCI 5331 at Georgia Southern University. This was a collaborative project created by Zachary Reese, Griffin Garrat, Tracy Bridges, Keenan Lewis-Jolly, and Daylan Post. Our team worked directly with Dr. Andrew Allen of Georgia Southern University to gather requirements and client feedback during sprints. The purpose of the Academic Assessment Tool is to create a web-based tool that allowed the CS department and faculty to have a central hub to track, compare, and manage trends of class scores of various ABET and department requirements and assignments through visuals that display calculated data.

## Team Roles

* Zachary Reese - Team Lead, Back-end Developer, Tester
* Griffin Garrat - Front-end Developer, Back-end Developer, Tester
* Tracy Bridges - Front-end Developer, Tester
* Keenan Lewis-Jolly - Front-end Developer
* Daylan Post - Database Design and Creation

## Software Development Methodology

* #### Agile
	* 3 Sprints
	* Weekly meetings
	* Bi-weekly client meetings
	
## Design Methodologies

* Model View Controller
* Object-Oriented Design
![](https://i.imgur.com/kzSYXvf.png)

## Flow Diagram

![](https://i.imgur.com/SiSKt6Y.png)

## Database

![](https://i.imgur.com/RTtXVv1.png)
	
## External Resources

* Clubhouse (Project management software)
* Github
* Wildfly (Eclipse JSP Server)
* AWS (RDS & Java Elastic Beanstalk)
* Freemarker
* MySQL
* ChartJS

## Testing

* JUnit
	* Database
	* Subsystems
* WC3 Validator
* System Testing

## Setup
1. Download Eclipse EE
	- https://www.eclipse.org/downloads/
	- Download eclipse dl manager and then select Eclipse for enterprise developers at setup
2. Import git project from GitHub
	- In eclipse, File -> Import
	- Git -> Project from git -> Clone URI
	- Copy url from github https://github.com/GSU-CS-Software-Engineering/2019fall-19f_assessment_tool.git
	- Enter github login information associated with AA github 
	- Next
	- Make sure all branches are selected, click next
	- ***Note directory, click next 
	- Select import using new project manager, click finish
	- In New Project wizard, select Web -> Dynamic Web Project
	- Name your project, doesnt have to be the same for everyone since it is local
	- ***IMPORTANT, uncheck use default location for project location
	- ***Select the directory from brfore and select the folder 
	- Project location should end in /2019fall-19f_assessment_tool
	- Click finish
3. Set up wildfly server
	- In Eclipse, click Window -> Show View -> Servers
	- In the server tab, create a new server 
	- Select JBoss -> Wildfly or whatever option has wildfly in it, may have multiple like AS, Wildfly, and Other
	- Wait for download, might have to close server Wizard after download for servers to show up
	- Create a new server with JBoss, Wildfly 17
	- Click next, create a new runtime should be selected at bottom, click next again
	- Name Wildfly runtime
	- Under home directory, click download and install runtime
	- Select Wildfly 17.0.1 and let download, after download click next
	- Add project to server by double clicking or clicking on the project and then 'Add'
4. Configure project
	- Servlet should have errors since server library is not imported
	- Right click on project in directory, click properties
	- Click Java Build Path -> Libraries
	- Click 'Add library' -> Server runtime
	- Select your wildfly instance
	- Apply and close
5. Test connection
	- Under the WebContent folder, select index.html
	- Right click on page, Run as -> Run on server
	- Select Wildfly, Finish
	- Wildfly will start and open a new tab in eclipse
	- If successful, you will see index.html


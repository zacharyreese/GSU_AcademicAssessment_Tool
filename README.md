# Academic Assessment Tool
This is the read me for the 2019 System Software class 
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

## MySQL (or Workbench) Connection Setup
	- Connection URL: aadb.cloepmhco6if.us-east-1.rds.amazonaws.com
	- Username: admin
	- Password: password
	- Database name: aadb

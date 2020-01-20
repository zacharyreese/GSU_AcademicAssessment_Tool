<html>

<head>
<title>Admin-Add/Remove</title>

<link rel="stylesheet" type="text/css" href="Style/adminstyle4.css">
</head>

<body>

	<iframe
		src="http://free.timeanddate.com/clock/i70uz2gf/n623/fs20/fc041e43/tct/pct/ftb/pa20/tt0/tm2/ta1/tb4"
		frameborder="0" width="261" height="85" allowTransparency="true"></iframe>

	<a class="weatherwidget-io"
		href="https://forecast7.com/en/32d45n81d78/statesboro/?unit=us"
		data-label_1="STATESBORO" data-label_2="WEATHER" data-theme="original">STATESBORO
		WEATHER</a>
	<script>
        ! function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (!d.getElementById(id)) {
                js = d.createElement(s);
                js.id = id;
                js.src = 'https://weatherwidget.io/js/widget.min.js';
                fjs.parentNode.insertBefore(js, fjs);
            }
        }(document, 'script', 'weatherwidget-io-js');
    </script>

	<nav>
		<ul>
			<li><a href="adminHome.html">Home</a></li>
			<li><a href="courseSearch.html">Course Search</a></li>
			<li><a href="graphs.html">Graphs</a></li>
			<li><a href="adminNotes.html">Notes</a></li>
			<li><a href="adminAddRemove.html">Admin Panel</a></li>
			<li><a href="adminContacts.html">Contacts</a></li>
			<li><a href="adminFlyTransition.html">Fly Eagles</a></li>
		</ul>
	</nav>
	<div class="">
	<span id='success'> ${(success)!""} </span>
		<h3>New Course</h3>
		<form action="mainServlet" method="Post">
        <ul>
            <label>Select Course: <br></label>
            <select id="course" name="course" required>
            <#list courses as course>
            	<option value="course${course.id}">${course.department} ${course.courseNumber} - ${course.name}</option>
            	</#list>
            </select>
        </ul>
        
        <ul>
            <label>CRN: <br></label>
            <input type="text" id="CRN" name="CRN" required>
        </ul>
        <ul>
            <label>Section: <br></label>
            <input type="text" id="section" name="section" required>
        </ul>
        <ul>
            <label>Semester: <br></label>
            <select id="semester" name="semester" required>
            	<option value="Fall">Fall</option>
            	<option value="Spring">Spring</option>
            	<option value="Summer">Summer</option>
            </select>
        </ul>
        <ul>
            <label>Year: <br></label>
            <input type="text" id="year" name="year" required>
        </ul>
        <ul>
            <label>Campus: <br></label>
            <select id="campus" name="campus" required>
            	<option value="Statesboro">Statesboro</option>
            	<option value="Savannah">Savannah</option>
            	<option value="Hinesville">Hinesville</option>
            </select>
        </ul>
        
         <ul>
            <label>Select Professor: <br></label>
            <select id="professor" name="professor" required>
            <#list professors as prof>
            	<option value="prof${prof.id}">${prof.lastName}, ${prof.firstName}</option>
            	</#list>
            </select>
        </ul>
       
        

        <input type="submit" value="Submit" name="addNewClass">
    </form>
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
	</div>
</body>
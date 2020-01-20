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
		<h3>${class.department} ${class.courseNumber} CRN: ${class.CRN} - New Instrument</h3>
		<form action="mainServlet" method="Post">
        <ul>
            <label>Select Type: <br></label>
            <select id="instrumentType" name="instrumentType" required>
	            <option value="Homework">Homework</option>
	            <option value="Quiz">Quiz</option>
	            <option value="Exam">Exam</option>
	            <option value="Project">Project</option>
            </select>
        </ul>
        
        <ul>
            <label>Instrument Name/Description: <br></label>
            <input type="text" id="description" name="description" required>
        </ul>
        <ul>
            <label>Instrument Grade Weight in %: <br></label>
            <input type="text" id="weight" name="weight" required>
        </ul>
		<ul>
		<label>Select which ${class.department} ${class.courseNumber} Learning Outcomes this Instrument satisfies<br></label>
			<#list learningOutcomes as lo>
        	<input type="checkbox" name="${lo.id}" value="${lo.id}">${lo.description}<br>
        	
        	</#list>
		</ul>
       
        

        <input type="hidden" value="${class.CRN}" name="classCRN">
        <input type="submit" value="Submit" name="addNewInstrument">
    </form>
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
	</div>
</body>
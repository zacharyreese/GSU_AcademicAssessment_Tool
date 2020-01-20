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
	<div class="courseList">
		<h3>New ${course.department} ${course.courseNumber} Learning
			Outcome</h3>
		<form action="mainServlet" method="Post">
			<ul>
				<label>Learning Outcome Description: <br></label>

				<textarea id="learningOutcomeDescription"
					name="learningOutcomeDescription" required rows="4" cols="50"></textarea>
			</ul>

			<ul style="text-align: left; background-color: lightgray;">
				<h3>Select which ABET requirement this Learning Objective
					fulfills</h3>
				<#list ABETlist as ABET>
					<#list categories as cat>
                <#if cat.id == ABET.id><h2>${cat.categoryID}. ${cat.categoryName}</h2></#if>
                </#list>
				 <input type="checkbox"
					name="${ABET.id}" value="${ABET.id}">${ABET.categoryID}${ABET.subcategoryID}:
				${ABET.description}<br>

				</#list>
			</ul>
			<input type="hidden" name="courseID" value="${course.id}">
			<input type="submit" name="addNewLearningOutcome" value="Submit">
		</form>
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
	</div>
</body>
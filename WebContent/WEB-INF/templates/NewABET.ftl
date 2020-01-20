<html>

<head>
<title>Admin-Add/Remove</title>

<link rel="stylesheet" type="text/css" href="Style/adminstyle4.css">
<script src="Scripts/gDriveAPI.js"></script>
<script async defer src="https://apis.google.com/js/api.js"
      onload="this.onload=function(){};handleClientLoad()"
      onreadystatechange="if (this.readyState === 'complete') this.onload()">
    </script>

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
		<h3> ABET </h3>
    <form action="mainServlet" method="Post">
        <ul>
            <label for="ABET_description">ABET Description: <br></label>

            <textarea id="ABET_description" name="ABET_description" required rows="4" cols="50"></textarea>
        </ul>
        
        <ul>
            <label>Category Name: </label>
            
            <select type="text" name="ABETCategoryName" required>
                <option value="General">General</option>
                <option value="CS Specific">CS Specific</option>
            </select> 
        </ul>
        
        <ul>
        	<h3>Select which Mission Objectives this ABET requirement fulfills</h3>
        	<#list missionObjectives as obj>
        	<input type="checkbox" name="${obj.id}" value="${obj.id}">${obj.description}<br>
        	
        	</#list>
        </ul>

        <input type="submit" value="Add New ABET Requirement" name="addNewABET">
    </form>
    
    
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
	</div>
</body>
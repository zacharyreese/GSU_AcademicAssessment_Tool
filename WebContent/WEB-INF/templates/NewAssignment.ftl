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
	<span id='success'> ${(success)!""} </span>
		<h3>New Assignment for ${instrument.description}</h3>
		<form action="mainServlet" method="Post">
        
        <h3>Grades (% out of 100%)</h3>
        <ul>
            <label>Average Grade: <br></label>
            <input type="text" id="avgGrade" name="avgGrade" required>
        </ul>
        <ul>
            <label>Highest Grade: <br></label>
            <input type="text" id="highGrade" name="highGrade" required>
        </ul>
        <ul>
            <label>Median Grade: <br></label>
            <input type="text" id="medianGrade" name="medianGrade" required>
        </ul>
        <ul>
            <label>Lowest Grade: <br></label>
            <input type="text" id="lowGrade" name="lowGrade" required>
        </ul>
        
        <h3>Upload scanned PDFs - BLOCK ANY IDENTIFIABLE INFORMATION</h3>
       <u>
       		<label>Highest Grade PDF</label>
       		<br><input type="file" id="highPDF" name="highPDF" multiple size="1">
       		<br><input type="submit" name="uploadHighPDF" value="Upload">
       		<br>
       </u>
       <u>
       		<br><label>Median Grade PDF</label>
       		<br><input type="file" id="medianPDF" name="medianPDF" multiple size="1">
       		<br><input type="submit" name="uploadMedianPDF" value="Upload">
       		<br>
       </u>
       <u>
       		<br><label>Lowest Grade PDF</label>
       		<br><input type="file" id="lowPDF" name="lowPDF" multiple size="1">
       		<br><input type="submit" name="uploadLowPDF" value="Upload">
       		<br><br>
       </u>
        

        <button name="addNewAssignment" value="${instrument.id}">Submit</button>
    </form>
    
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
	</div>
</body>
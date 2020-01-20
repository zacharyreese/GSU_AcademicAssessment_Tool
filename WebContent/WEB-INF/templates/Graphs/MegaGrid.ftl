<html>

<head>
<title>Admin-Home</title>

<link rel="stylesheet" type="text/css" href="Style/adminstyle.css">
<link rel="stylesheet" type="text/css" href="Style/custom.css">
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
		!function(d, s, id) {
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
		<form action="mainServlet" method="Post">
			<ul>
				<li><a href="adminHome.html">Home</a></li>
				<li><a href="courseSearch.html">Course Search</a></li>
				<li><a href="graphs.html">Graphs</a></li>
				<li><a href="adminNotes.html">Notes</a></li>
				<li><a href="adminAddRemove.html">Admin Panel</a></li>
				<li><a href="adminContacts.html">Contacts</a></li>
				<li><a href="adminFlyTransition.html">Fly Eagles</a></li>
			</ul>
		</form>
	</nav>
	<div>
		<div class="grid-container">
			<div class="grid-item">Learning Outcome</div>
			<div class="grid-item">Mission Objective(s)</div>
			<div class="grid-item">ABET Learning Outcome(s)</div>
			<div class="grid-item">Evaluation Instrument</div>
			<div class="grid-item">5</div>
			<div class="grid-item">6</div>
			<div class="grid-item">7</div>
			<div class="grid-item">8</div>
			<div class="grid-item">9</div>
		</div>

	</div>
</body>


</html>
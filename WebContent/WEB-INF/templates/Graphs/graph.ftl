<!doctype html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <title>GSU Academic Assessment</title>
    <link rel="stylesheet" tpye="text/css" href="Style/graph.css">
    <link rel="stylesheet" tpye="text/css" href="Style/custom.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.2/dist/Chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="Scripts/OverallABETGraph.js"></script>
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
	
	<div class="mainContent">
	
    <div class="graphHeader">
        <h1>Campus BSCS Achievement of Learning Outcomes</h1>
        <label>Campus: </label>
        <select id="campusSelect">
            <option value="AllCampus">All Campuses</option>
            <option value="Statesboro">Statesboro</option>
            <option value="Savannah">Savannah</option>
            <option value="Hinesville">Hinesville</option>
        </select>
        
    <button class="addData">Compare Against All Campus</button>
    </div>
    <br>
    <div id="container">
        <canvas id="myChart" width="400" height="400"></canvas>
    </div>
    <script>
    var ABETList = [<#list ABETs as ABET>'${ABET.categoryID}${ABET.subcategoryID}'<#sep>,</#sep></#list>];
    var avgList = [<#list averages as avg>${avg}<#sep>,</#sep></#list>];
    var avgListStatesboro = [<#list averagesStatesboro as avgBoro>${avgBoro}<#sep>,</#sep></#list>];
    var avgListSavannah = [<#list averagesSavannah as avgSav>${avgSav}<#sep>,</#sep></#list>];
    var avgListHinesville = [<#list averagesHinesville as avgHines>${avgHines}<#sep>,</#sep></#list>];
    </script>

    <form action="mainServlet" method="Post">
        <input type="submit" name="home" value="Home" class='button'>
    </form>
	</div>
</body>

</html>
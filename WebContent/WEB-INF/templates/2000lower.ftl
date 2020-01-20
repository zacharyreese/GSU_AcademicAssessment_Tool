<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>GSU Academic Assessment 1</title>
  <link rel="stylesheet" tpye="text/css" href="Style/graph.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.2/dist/Chart.min.js"></script>
  
<link rel="stylesheet" type="text/css" href="Style/adminstyle.css">
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    
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
  <div id="container">
    <canvas id="myChart" width="400" height="400"></canvas>
    </div>
    <form action="mainServlet" method="Post">
    <input type="submit" name="home" value="Home" class='button'> 
    </form>
<script>
var ctx = document.getElementById('myChart').getContext('2d');
    var dataset = [88, 78, 0, 84, 75, 0, 75, 73, 0, 0, 0];
    var dataset2 = [80, 0, 0, 78, 0, 85, 71, 0, 67, 73, 90];
    var dataset3 = [94, 87, 84, 88, 90, 83, 0, 77, 84, 96, 71];
    var dataset4 = [91, 80, 67, 0, 55, 66, 90, 97, 88, 0, 69];
    var backgroundColors = [
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4',
                '#4285f4'
            ];
    //Change color to red if score is lower than 70
    for(i = 0; i < dataset.length; i++) {
        if(dataset[i] < 80) {
            backgroundColors[i] = "#ed0707";
        }
    }
    var backgroundColors2 = [
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40',
        '#39bf40'
    ];
//Change color to red if score is lower than 70
for(i = 0; i < dataset2.length; i++) {
if(dataset2[i] < 80) {
    backgroundColors2[i] = "#ed0707";
}
}
var backgroundColors3 = [
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb',
    '#bf39bb'
];
//Change color to red if score is lower than 70
for(i = 0; i < dataset3.length; i++) {
if(dataset3[i] < 80) {
backgroundColors3[i] = "#ed0707";
}
}
var backgroundColors4 = [
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92',
    '#39bf92'
];
//Change color to red if score is lower than 70
for(i = 0; i < dataset4.length; i++) {
if(dataset4[i] < 80) {
backgroundColors4[i] = "#ed0707";
}
}
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['1a', '1b', '1c', '1d', '1e', '1f', '1g', '1h', '1i', '2a', '2b'],
        datasets: [{
            label: '1000 Level Courses',
            data: dataset,
            backgroundColor: backgroundColors,
            datalabels: {
                color: '#fff'
            },
        },{
            label: '2000 Level Courses',
            data: dataset2,
            backgroundColor: backgroundColors2,
            datalabels: {
                color: '#fff'
            },
        },{
            label: '3000 Level Courses',
            data: dataset3,
            backgroundColor: backgroundColors3,
            datalabels: {
                color: '#fff'
            },
        },{
            label: '5000 Lvel Courses',
            data: dataset4,
            backgroundColor: backgroundColors4,
            datalabels: {
                color: '#fff'
            },
        },
        ]
    },
    options: {
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                    suggestedMax: 100
                }
            }]
        },
        title: {
            display: true,
            text: 'Course Level vs Learning Outcomes - Threshhold = 80%'
        }
    }
});
</script>
    <style>
        #container {
            position: relative;
            height: 80vh;
            width: 80vw;
        }
    </style>
    
</body>
</html>
<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>GSU Academic Assessment</title>
  <link rel="stylesheet" tpye="text/css" href="Style/graph.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.2/dist/Chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
    
<link rel="stylesheet" type="text/css" href="Style/adminstyle.css">
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
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['1a', '1b', '1c', '1d', '1e', '1f', '1g', '1h', '1i', '2a', '2b'],
        datasets: [{
            label: 'Spring 2017',
            data: [78, 78, 80, 84, 75, 87, 75, 73, 78, 75, 79],
            backgroundColor: [
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
            ],
            datalabels: {
                color: '#fff'
            },
        },
                  {
            label: 'Fall 2018',
            data: [79, 81, 81, 87, 86, 85, 90, 84, 80, 80, 81],
            backgroundColor: [
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437',
                '#db4437'
            ],
            datalabels: {
                color: '#fff'
            },
        },
        {
            label: 'Spring 2018',
            data: [74, 76, 88, 85, 90, 83, 98, 78, 70, 80, 85],
            backgroundColor: [
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852',
                '#32a852'
            ],
            datalabels: {
                color: '#fff'
            },
        },
        {
            label: 'Fall 2019',
            data: [80, 80, 75, 90, 75, 75, 88, 77, 75, 73, 86],
            backgroundColor: [
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf',
                '#39bbbf'
            ],
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
            text: 'Comparison of Current & Previous Cycles Achievement of Learning Outcomes'
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
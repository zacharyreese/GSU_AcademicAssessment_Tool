$(function() {

//Campus data lists
var allCampuses = avgList;
var statesboro = avgListStatesboro;
var boroLabel = "Statesboro Campus"
var savLabel = "Savannah Campus"
	var hinesvilleLabel = "Hinesville Campus"
		var allLabel = "All Campuses"
var savannah = avgListSavannah;
var hinesville = avgListHinesville;
var campusDataList = allCampuses;
var dataLabel = allLabel;

//Data set
var data = {
	labels : ABETList,
	datasets : [ {
		label : dataLabel,
		data : campusDataList,
		backgroundColor : [ '#4285f4', '#4285f4', '#4285f4', '#4285f4',
				'#4285f4', '#4285f4', '#4285f4', '#4285f4', '#4285f4',
				'#4285f4', '#4285f4' ],
		borderColor : [ 'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
				'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
				'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
		borderWidth : 1,
		datalabels : {
			color : '#fff'
		},
	} ]
};

//Make chart
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
	type : 'bar',
	data : data,
	options : {
		maintainAspectRatio : false,
		scales : {
			yAxes : [ {
				ticks : {
					beginAtZero : true,
					suggestedMax : 100
				}
			} ]
		}
	}
});

$(".addData").click(
		function() {
			var newDataset = {
				label : 'All Campuses',
				data : avgList,
				backgroundColor : [ '#b7483e', '#b7483e', '#b7483e', '#b7483e',
						'#b7483e', '#b7483e', '#b7483e', '#b7483e', '#b7483e',
						'#b7483e', '#b7483e' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1,
				datalabels : {
					color : '#fff'
				},
			};
			data.datasets.push(newDataset);
			myChart.update();
		});

$('#campusSelect').change(function() {
	if ($(this).val() == "AllCampus") {
		campusDataList = allCampuses;
		dataLabel = allLabel;
	}
	if ($(this).val() == "Statesboro") {
		campusDataList = statesboro;
		dataLabel = boroLabel
	}
	if ($(this).val() == "Savannah") {
		campusDataList = savannah;
		dataLabel = savLabel;
	}
	if ($(this).val() == "Hinesville") {
		campusDataList = hinesville;
		dataLabel = hinesvilleLabel;
	}
	myChart.data.datasets[0].data = campusDataList;
	myChart.data.datasets[0].label = dataLabel;
	myChart.update();
});

});

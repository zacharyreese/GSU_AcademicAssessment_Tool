<html>

<head>
<title>GSU Academic Assessment</title>

<link rel="stylesheet" type="text/css" href="Style/adminstyle4.css">
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
	
	<div class="grid-container">
			<div class="grid-item"><strong>Learning Outcome</strong></div>
			<div class="grid-item"><strong>Mission Objective(s)</strong></div>
			<div class="grid-item"><strong>ABET Learning Outcome(s)</strong></div>
			<div class="grid-item"><strong>Evaluation Instrument</strong></div>
			<div class="grid-item"><strong>Avg (%)</strong></div>
			<#if learningOutcomes[0]??>
			<div class="grid-item">${learningOutcomes[0].description}</div>
			<div class="grid-item"><#list MO1 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET1 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments1 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg1}</div>
			</#if>
			<#if learningOutcomes[1]??>
			<div class="grid-item">${learningOutcomes[1].description}</div>
			<div class="grid-item"><#list MO2 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET2 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments2 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg2}</div>
			</#if>
			<#if learningOutcomes[2]??>
			<div class="grid-item">${learningOutcomes[2].description}</div>
			<div class="grid-item"><#list MO3 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET3 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments3 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg3}</div>
			</#if>
			<#if learningOutcomes[3]??>
			<div class="grid-item">${learningOutcomes[3].description}</div>
			<div class="grid-item"><#list MO4 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET4 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments4 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg4}</div>
			</#if>
			<#if learningOutcomes[4]??>
			<div class="grid-item">${learningOutcomes[4].description}</div>
			<div class="grid-item"><#list MO5 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET5 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments5 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg5}</div>
			</#if>
			<#if learningOutcomes[5]??>
			<div class="grid-item">${learningOutcomes[5].description}</div>
			<div class="grid-item"><#list MO6 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET6 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments6 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg6}</div>
			</#if>
			<#if learningOutcomes[6]??>
			<div class="grid-item">${learningOutcomes[6].description}</div>
			<div class="grid-item"><#list MO7 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET7 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments7 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg1}</div>
			</#if>
			<#if learningOutcomes[7]??>
			<div class="grid-item">${learningOutcomes[7].description}</div>
			<div class="grid-item"><#list MO8 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET8 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments8 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg1}</div>
			</#if>
			<#if learningOutcomes[8]??>
			<div class="grid-item">${learningOutcomes[8].description}</div>
			<div class="grid-item"><#list MO9 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET9 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments9 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg1}</div>
			</#if>
			<#if learningOutcomes[9]??>
			<div class="grid-item">${learningOutcomes[9].description}</div>
			<div class="grid-item"><#list MO10 as MO>${MO.id}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list ABET10 as ABET>${ABET.categoryID}${ABET.subcategoryID}<#sep>, </#sep></#list></div>
			<div class="grid-item"><#list Instruments10 as instrument>${instrument.description}<#sep>, </#sep></#list></div>
			<div class="grid-item">${avg10}</div>
			</#if>
		</div>
	
	
</body>
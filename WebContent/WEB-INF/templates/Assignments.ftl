<html>

<head>
    <title>
        GSU Academic Assessment
    </title>

    <link rel="stylesheet" type="text/css" href="Style/adminstyle4.css">
</head>

<body>

    <iframe src="http://free.timeanddate.com/clock/i70uz2gf/n623/fs20/fc041e43/tct/pct/ftb/pa20/tt0/tm2/ta1/tb4" frameborder="0" width="261" height="85" allowTransparency="true"></iframe>

    <a class="weatherwidget-io" href="https://forecast7.com/en/32d45n81d78/statesboro/?unit=us" data-label_1="STATESBORO" data-label_2="WEATHER" data-theme="original">STATESBORO WEATHER</a>
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
			<li><a href="adminAddRemove.html">Add/Remove</a></li>
			<li><a href="adminContacts.html">Contacts</a></li>
			<li><a href="adminFlyTransition.html">Fly Eagles</a></li>
        </ul>
    </nav>
    <div class="courseList">
    <h2>Assignment for ${instrument.description}</h2> <br><hr><br>
    <span id='success'> ${(success)!""} </span>
    <form action="mainServlet" method="Post">
        <div>
				<ol>
					<#if assignments??>
                <#list assignments as ass>
                    <h3>Grades (% out of 100%)</h3>
                    <li>Average Grade: ${ass.averageGrade}
                    <br>Highest Grade: ${ass.highestGrade}
                    <br>Median Grade: ${ass.medianGrade}
                    <br>Lowest Grade: ${ass.lowestGrade}
                    
                    <#if (ass.lowestPDF?? && ass.highestPDF != "") || (ass.medianPDF?? && ass.medianPDF != "") || (ass.lowestPDF?? && ass.lowestPDF != "")>
                    <h3>View Documents</h3>
                    <#if ass.highestPDF?? && ass.highestPDF != "">
                    <br><a target="_blank" href="${ass.highestPDF}">Highest Grade PDF</a>
                    </#if>
                    <#if ass.medianPDF?? && ass.medianPDF != "">
                    <br><a target="_blank" href="${ass.medianPDF}">Median Grade PDF</a>
                    </#if>
                    <#if ass.lowestPDF?? && ass.lowestPDF != "">
                    <br><a target="_blank" href="${ass.lowestPDF}">Lowest Grade PDF</a>
                    </#if>
                    </#if>
                    
                    </li><br>
                   
                </#list>
                </#if>
            </ol>
			</div>
			<input type="hidden" name="instrumentID" value="${instrument.id}">
        <input type="submit" name="gotoAddNewAssignment" value="Add New Assignment">
		</form>
		<input type="button" name="backBtn" onclick="history.back()"
			value="Back" class='button'>
    </div>
    </body>
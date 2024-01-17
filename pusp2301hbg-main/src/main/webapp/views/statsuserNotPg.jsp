
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>
</head>

    <%@ include file="menu.jsp" %>
    
	<%@ page import="java.util.List" %>
	
	<% Double totalTime = (Double) request.getAttribute("totalTime"); %>
	<% Double SDP = (Double) request.getAttribute("11"); %>
	<% Double SRS = (Double) request.getAttribute("12"); %>
	<% Double SVVS = (Double) request.getAttribute("13"); %>
	<% Double STLDD = (Double) request.getAttribute("14"); %>
	<% Double SVVI = (Double) request.getAttribute("15"); %>
	<% Double SDDD = (Double) request.getAttribute("16"); %>
	<% Double SVVR = (Double) request.getAttribute("17"); %>
	<% Double SSD = (Double) request.getAttribute("18"); %>
	<% Double SLUTRAPPORT = (Double) request.getAttribute("19"); %>
	<% Double FUNKTIONSTEST = (Double) request.getAttribute("21"); %>
	<% Double SYSTEMTEST = (Double) request.getAttribute("22"); %>
	<% Double REGRESSIONSTEST = (Double) request.getAttribute("23"); %>
	<% Double MÖTE = (Double) request.getAttribute("30"); %>
	<% Double FÖRELÄSNING = (Double) request.getAttribute("41"); %>
	<% Double ÖVNING = (Double) request.getAttribute("42"); %>
	<% Double TERMINALÖVNING = (Double) request.getAttribute("43"); %>
	<% Double SJÄLVSTUDIER = (Double) request.getAttribute("44"); %>
	<% Double ÖVRIGT = (Double) request.getAttribute("100"); %>
	<% String CURRENTUSER = (String) request.getAttribute("nameUser");%>
	<style>
    	#content {
    		display: flex;
    		flex-direction: column;
    		align-items: center;
    		margin-top: 5vh;
    		row-gap: 5vh;
    	}
    	
   		#pg_stats_bar {
   			display: flex;
   			align-items: center;
   			justify-content: center;
   			width: 90%;
   			height: 80vh;
   			background-color: gray;
   			border-radius: 20px;
   			
   		}
   		
   		#stats_bar_content {
   			display: flex;
   			align-items: center;
   			flex-direction: column;
   			width: 95%;
   			height: 90%;
   			background-color: white;
   			border-radius: 20px;
   		}
   		
   		#left {
   			display:flex;
   			flex-direction: row;
   			width: 80%;
   			height: 80%; 
   			padding-top: 3vh;
   			row-gap: 2vh;
			font-size: larger;
			font-weight: bold;
   		}
   		
   		#right {
   			display:flex;
   			align-items: center;
   			width: 20%;
   			height: 5vh;
   			border: solid 2px black;
   			border-radius: 20px;
   			margin-left: 200px;
   			margin: 2vh;
   		}
   		
   		#right p {
   			margin: 1vh;
   			font-weight: bold;
   		}
   		
   		
   		#table_container {
   			display:flex;
   			border-radius: 20px;
   			border: solid 2px black;
   			width: 85%;
   			overflow: auto; 
			height: 80%;
			border-radius: 20px;
   		}
   		
   		table{
   			overflow: auto; 
   			border-collapse: collapse; 
			width: 100%;
		}
		
		
		
		thead {
			height: 10%;
		}
		
		
		th {
			background-color: #eee;
			border:1px solid black;
		}
		
		td {
			padding: 0; 
			margin: 0;
			border:1px solid black;
			height: 5%;
		}
   		
    	
    </style>

    <div id="content">

        <h1>STATISTICS</h1>
        
        <div id="pg_stats_bar">
        	<div id="stats_bar_content">
        		<div style="width: 85%; height: 10%; display: flex; align-items: center; flex-direction: row;" >
	        		<div id="left">
	        			<p>User: </p>
                        <p name = "nameUser"> <%= CURRENTUSER %> </p>
						
	
		        	</div>
		        	
		        	<div id="right">
		        	
		        		<p>Total time:<p > <%= totalTime %> </p></p>
		        	
		        	</div>
		        </div>
	        	
	        	
	        	
	        	<div id="table_container">
        
		        	<table>
		        		<thead>
			        		<tr>
			        			<th style="width: 10%;" >Number</th>
								<th>Activity</th>
								<th>Time</th>
							</tr>
		        		</thead>
		        		
		        		<tbody>
		        		
			        		<tr>
			        			<td style="text-align: center;" >11</td>
			        			<td>SDP</td>
			        			<td><%= SDP %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">12</td>
			        			<td>SRS</td>
			        			<td><%= SRS %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">13</td>
			        			<td>SVVS</td>
			        			<td><%= SVVS %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">14</td>
			        			<td>STLDD</td>
			        			<td><%= STLDD %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">15</td>
			        			<td>SVVI</td>
			        			<td><%= SVVI %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">16</td>
			        			<td>SDDD</td>
			        			<td><%= SDDD %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">17</td>
			        			<td>SVVR</td>
			        			<td><%= SVVR %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">18</td>
			        			<td>SSD</td>
			        			<td><%= SSD %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">19</td>
			        			<td>Slutrapport</td>
			        			<td><%= SLUTRAPPORT %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">21</td>
			        			<td>Funktionstest</td>
			        			<td><%= FUNKTIONSTEST %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">22</td>
			        			<td>Systemtest</td>
			        			<td><%= SYSTEMTEST %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">23</td>
			        			<td>Regressionstest</td>
			        			<td><%= REGRESSIONSTEST %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">30</td>
			        			<td>Möte</td>
			        			<td><%= MÖTE %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">41</td>
			        			<td>Föreläsning</td>
			        			<td><%= FÖRELÄSNING %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">42</td>
			        			<td>Övning</td>
			        			<td><%= ÖVNING %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">43</td>
			        			<td>Terminalövning</td>
			        			<td><%= TERMINALÖVNING %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">44</td>
			        			<td>Självstudier</td>
			        			<td><%= SJÄLVSTUDIER %></td>
			        		</tr>
			        		<tr>
			        			<td style="text-align: center;">100</td>
			        			<td>Övrigt</td>
			        			<td><%= ÖVRIGT %></td>
			        		</tr>
		        		
		        	
		        		</tbody>
		        	</table>    
		        </div>
        	</div>
        </div>
        
    </div>
    </div>
    </body>
<%@ include file="footer.jsp" %>
</html>
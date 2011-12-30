<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Easyparallel status page</title>
</head>
<body>
	<h1>Easyparallel console</h1>
	<hr />
	<h2>Current executions</h2>
	<table width="80%" border="1">
		<thead>
			<tr>
				<th>Task name</th><th>Requested executors #</th><th>Registered executors #</th><th>Actions</th>
			</tr>
		</thead>
		<c:forEach var="execution" items="${executions}">
			<tr>
		      <td>${execution.task.name}</td>
		      <td>${execution.requestedExecutorsCount}</td>
		      <td>${execution.registeredExecutorsCount}</td>
		      <td><a href="">See results</a><a href="remove">Remove</a></td>
		    </tr>  
	    </c:forEach>
	</table>
	<hr />
	<h2>Available Tasks</h2>
	<table width="80%" border="1">
		<thead>
			<tr>
				<th>Task name</th><th>Actions</th>
			</tr>
		</thead>
		<c:forEach var="task" items="${tasks}">
			<tr>
		      <td>${task.name}</td><td><a href="/newexecution.jsp?id=${task.uniqueIdentifier}">Execute</a> <a href="/task/remove?id=${task.uniqueIdentifier}">Remove</a></td>
		    </tr>  
	    </c:forEach>
	</table>
	<hr />
	<p>
		<a href="newtask.jsp">Create new task</a><br />
		<a href="execute.jsp">Become an executor</a>
	</p>	
	
</body>
</html>

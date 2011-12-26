<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Easyparallel new execution</title>
</head>
<body>
	<h1>New task</h1>
	<p style="color: red;">${errors}</p>
	<form action="/newexecution" method="post">
		<input type="hidden" name="taskid" value="<%= request.getParameter("id") %>"></input>
	  	<label for="requestedExecutors">How many executors should execute the task:</label>
		<input type="text" name="requestedExecutors" id="requestedExecutors" value="10"></input>
		<input type="submit" value="Create executions"></input>
	</form>
</body>
</html>

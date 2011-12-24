<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Easyparallel new task</title>
</head>
<body>
	<h1>New task</h1>
	<p style="color: red;">${errors}</p>
	<form action="/newtask" method="post">
	  	<label for="name">Task name</label>
		<input type="text" name="name" id="name"></input>
		<br />
	  	<label for="perform">Perform function</label><br />
		<textarea name="performFunction" id="perform" rows="30" cols="120"></textarea>
		<br />
	  	<label for="merge">Merge function</label><br />
		<textarea name="mergeFunction" id="merge" rows="30" cols="120"></textarea><br />
		<input type="submit" value="Create"></input>
	</form>
</body>
</html>

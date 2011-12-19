<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Easyparallel status page</title>
  </head>
  <body>
      <c:forEach var="task" items="${tasks}">
	      ${task}  
      </c:forEach>
  </body>
</html> 
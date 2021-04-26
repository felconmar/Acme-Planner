<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.startDate" path="startDate" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.endDate" path="endDate" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.visibility" path="visibility" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.optionalLink" path="optionalLink" width="10%"/>
</acme:list>
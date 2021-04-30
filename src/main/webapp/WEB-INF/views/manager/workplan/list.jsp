<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	 
	<acme:list-column code="manager.workplan.list.label.startDate" path="startDate" width="10%"/>
	<acme:list-column code="manager.workplan.list.label.endDate" path="endDate" width="10%"/>
	<acme:list-column code="manager.workplan.list.label.visibility" path="visibility" width="10%"/>
	 
	
</acme:list>
<acme:form>
	<a href="manager/workplan/create" class="btn btn-primary" >
	<acme:message code="master.menu.manager.created-workplan"/></a>
</acme:form>
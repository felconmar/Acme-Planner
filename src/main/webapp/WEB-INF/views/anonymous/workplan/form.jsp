<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="anonymous.workplan.form.title.details"/>
</h2>

<acme:form>
	
	<acme:form-double code="anonymous.workplan.form.label.startDate" path="startDate" readonly="true"/>
	<acme:form-double code="anonymous.workplan.form.label.endDate" path="endDate" readonly="true"/>
	<acme:form-double code="anonymous.workplan.form.label.workload" path="workload" readonly="true"/>
	<acme:form-double code="anonymous.workplan.form.label.visibility" path="visibility" readonly="true"/>
		
  	<acme:form-return code="anonymous.workplan.form.button.return"/> 
  	<acme:form-submit code="anonymous.workplan.form.button.showNotFinishedTasksFromWorkPlan" action="/anonymous/task/list_from_workplan?id=${id}" method="get"/>
</acme:form>


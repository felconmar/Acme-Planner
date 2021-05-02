<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox 	code="manager.task.form.label.title" path="title"/>
	<acme:form-moment 	code="manager.task.form.label.startDate" path="startDate"/>
	<acme:form-moment 	code="manager.task.form.label.endDate" path="endDate"/>
	<acme:form-integer 	code="manager.task.form.label.workload" path="workload"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description"/>
	<acme:form-url 		code="manager.task.form.label.optionalLink" path="optionalLink"/>
	<acme:form-select	code="manager.task.form.label.visibility" path="visibility">
		<acme:form-option code="manager.task.form.label.visibility.PRIVATE" value="PRIVATE"/>
		<acme:form-option code="manager.task.form.label.visibility.PUBLIC" value="PUBLIC"/>
	</acme:form-select>	
	<acme:form-textbox 	code="manager.task.form.label.executionPeriod" path="executionPeriod"/>
	<acme:form-submit   test="${command == 'create'}"	code="manager.task.form.button.create" action="/manager/task/create"/>
	<acme:form-return 	code="manager.task.form.button.return"/>	
</acme:form>
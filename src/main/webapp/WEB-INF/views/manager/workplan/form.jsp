<%@page language="java"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="manager.workplan.form.label.startDate"
		path="startDate" />
	<acme:form-moment code="manager.workplan.form.label.endDate"
		path="endDate" />
	<acme:form-double readonly="true"
		code="manager.workplan.form.label.workload" path="workload" />
	<acme:form-select code="manager.workplan.form.label.visibility"
		path="visibility">
		<acme:form-option
			code="manager.workplan.form.label.visibility.PRIVATE" value="PRIVATE" />
		<acme:form-option code="manager.workplan.form.label.visibility.PUBLIC"
			value="PUBLIC" />
	</acme:form-select>

	<acme:form-select code="manager.workplan.form.label.tasks" path="tasks">
		<acme:form-option code="HOLA" value="${prueba}" />
		<acme:form-option code="${prueba}" value="${prueba}" />
		<acme:form-option code="${prueba3}" value="${prueba}" />
		<acme:form-option code="${prueba4}" value="${prueba}" />
		<acme:form-option code="${prueba5}" value="${prueba}" />
		<acme:form-option code="${prueba6}" value="${prueba}" />


	</acme:form-select>

	<%-- <acme:form-double code="manager.workplan.form.label.workload"
		path="workload" /> --%>



	<acme:form-submit test="${command == 'create'}"
		code="manager.workplan.form.button.create"
		action="/manager/workplan/create" />
	<acme:form-submit test="${command != 'create'}"
		code="manager.workplan.form.button.update"
		action="/manager/workplan/update" />
	<acme:form-submit test="${command != 'create'}"
		code="manager.workplan.form.button.delete"
		action="/manager/workplan/delete" />
	<acme:form-return code="manager.workplan.form.button.return" />
</acme:form>
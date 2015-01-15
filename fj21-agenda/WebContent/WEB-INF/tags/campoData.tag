<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="true" type="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<input type="text" id="${id}" name="${id}" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${value}"/>">
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <jsp:useBean id="dao" class="br.com.caelum.agenda.dao.ContatoDao"></jsp:useBean> --%>
	
	<c:import url="cabecalho.jsp"></c:import>
	
	<table border="1">
		<!-- percorre contatos montando as linhas da tabela -->
		<tr>
			<td>Nome</td>
			<td>E-mail</td>
			<td>Enderenço</td>
			<td>Data de nascimento</td>
		</tr>
<%--<c:forEach var="contato" items="${dao.lista}" varStatus="id"> --%>
		<c:forEach var="contato" items="${contatos}" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : '00FFFF' }">
				<td>${contato.nome}</td>
				<td>
<!--				<c:if test="${not empty contato.email }">
						<a href="mailto:${contato.email}">${contato.email}</a>
					</c:if> 
					<c:if test="${empty contato.email }">
						Email nao informado!
					</c:if>  -->
					<c:choose>
						<c:when test="${not empty contato.email }">
							<a href="mailto:${contato.email}">${contato.email}</a>
						</c:when>
						<c:otherwise>
							Email nao informado!
						</c:otherwise>
					</c:choose>
				</td>
				<td>${contato.endereco}</td>
				<td>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${contato.dataNascimento.time}"/>
				</td>
				<td>
					<a href="mvc?logica=RemoveContatoLogic&id=${contato.id}">Remove</a>
				</td>
				<td>
					<a href="mvc?logica=MostraContatoLogic&id=${contato.id}">Alterar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<br />

	<c:import url="rodape.jsp"></c:import>
</body>
</html>
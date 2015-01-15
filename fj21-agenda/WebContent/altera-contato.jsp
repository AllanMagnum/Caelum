<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Altera Contato</title>
</head>
<body>
	<c:import url="cabecalho.jsp"></c:import>
	<h1>Altera Contato</h1>
	<hr>
	<form action="mvc?logica=AlteraContatoLogic" method="post">
		Id: <input type="hidden" name="id" value="${contato.id}"/> <br />
		Nome: <input type="text" name="nome" value="${contato.nome}"/> <br />
		E-mail: <input type="text" name="email" value="${contato.email}"/> <br />
		EndereÃ§o: <input type="text" name="endereco" value="${contato.endereco}"/> <br />
		Data de nascimento: <caelum:campoData id="dataNascimento" value="${contato.dataNascimento.time}"/> <br />
		<input type="submit" value="Alterar">
	</form>
	<c:import url="rodape.jsp"></c:import>
</body>
</html>
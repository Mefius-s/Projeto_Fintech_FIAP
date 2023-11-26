<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat, br.com.fiap.bean.ObjetivoFinanceiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("objetivoFinanceiro") != null ? "Alterar" : "Incluir" %>
	Objetivo Financeiro</title>
<%@ include file="header.jsp"%>
</head>
<body class="bg-index">
	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h1 class="mx-auto text-light"><%= request.getAttribute("objetivoFinanceiro") != null ? "Alterar" : "Incluir" %>
			Objetivo Financeiro
		</h1>
		<div class="container col-xl-6 col-lg-8 col-md-10 col-sm-11">
			<form action="incluir_objetivo" method="post" class="form-box">
				<% ObjetivoFinanceiro objetivoFinanceiro = (ObjetivoFinanceiro) request.getAttribute("objetivoFinanceiro"); %>
				<% if (objetivoFinanceiro != null) { %>
				<input type="hidden" name="idObjetivo"
					value="<%= objetivoFinanceiro.getIdObjetivo() %>">
				<% } %>
				<div class="mb-3">
					<label for="valor" class="form-label">Valor[R$]</label> <input
						required type="number" step="0.01" class="form-control" id="valor"
						name="valor"
						value="<%= objetivoFinanceiro != null ? objetivoFinanceiro.getValor() : "" %>">
				</div>
				<div class="mb-3">
					<label for="data-inicio" class="form-label">Data de Início</label>
					<input required type="date" class="form-control" id="data-inicio"
						name="data-inicio"
						value="<%= objetivoFinanceiro != null ? new SimpleDateFormat("yyyy-MM-dd").format(objetivoFinanceiro.getDataInicio().getTime()) : "" %>">
				</div>
				<div class="mb-3">
					<label for="data-fim" class="form-label">Data de Fim</label> <input
						required type="date" class="form-control" id="data-fim"
						name="data-fim"
						value="<%= objetivoFinanceiro != null ? new SimpleDateFormat("yyyy-MM-dd").format(objetivoFinanceiro.getDataFim().getTime()) : "" %>">
				</div>
				<div class="mb-3">
					<label for="descricao" class="form-label">Descrição</label>
					<textarea required class="form-control" id="descricao"
						name="descricao" maxlength="150"><%= objetivoFinanceiro != null ? objetivoFinanceiro.getDescricao() : "" %></textarea>
				</div>
				<button type="submit" class="btn btn-success d-grid col-12 mx-auto"><%= objetivoFinanceiro != null ? "Atualizar" : "Cadastrar" %></button>

			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
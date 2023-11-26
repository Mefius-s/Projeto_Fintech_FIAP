<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat, br.com.fiap.bean.Despesa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("despesa") != null ? "Alterar" : "Incluir" %>
	Despesa</title>
<%@ include file="header.jsp"%>
</head>
<body class="bg-index">
	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h1 class="mx-auto text-light"><%= request.getAttribute("despesa") != null ? "Alterar" : "Incluir" %>
			Despesa
		</h1>
		<div class="container col-xl-6 col-lg-8 col-md-10 col-sm-11">
			<form action="incluir_despesa" method="post" class="form-box">
				<% Despesa despesa = (Despesa) request.getAttribute("despesa"); %>
				<% if (despesa != null) { %>
				<input type="hidden" name="idDespesa"
					value="<%= despesa.getIdDespesa() %>">
				<% } %>
				<div class="mb-3">
					<label for="nome" class="form-label">Nome da despesa</label> <input
						required type="text" class="form-control" id="nome" name="nome"
						value="<%= despesa != null ? despesa.getNome() : "" %>">
				</div>
				<div class="mb-3">
					<label for="categoria" class="form-label">Categoria</label> <select
						required id="categoria" name="categoria" class="form-select">
						<option disabled selected>Selecionar</option>
						<option value="1">Comida e bebida</option>
						<option value="2">Transporte</option>
						<option value="3">Educação</option>
						<option value="4">Lazer</option>
						<option value="5">Saúde</option>
						<option value="6">Contas</option>
						<option value="7">Compras</option>
						<option value="8">Mercado</option>
						<option value="9">Outro</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="valor" class="form-label">Valor [R$]</label> <input
						required type="number" step="0.01" class="form-control" id="valor"
						name="valor"
						value="<%= despesa != null ? despesa.getValor() : "" %>">
				</div>
				<div class="mb-3">
					<label for="data" class="form-label">Data</label> <input required
						type="date" class="form-control" id="data" name="data"
						value="<%= despesa != null ? new SimpleDateFormat("yyyy-MM-dd").format(despesa.getData().getTime()) : "" %>">
				</div>
				<div class="mb-3">
					<label for="descricao" class="form-label">Descrição</label> <input
						required type="textarea" class="form-control" id="descricao"
						name="descricao"
						value="<%= despesa != null ? despesa.getDescricao() : "" %>">
				</div>
				<button type="submit" class="btn btn-success d-grid col-12 mx-auto"><%= despesa != null ? "Atualizar" : "Cadastrar" %></button>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
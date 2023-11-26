<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat, br.com.fiap.bean.Recebimento"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("recebimento") != null ? "Alterar" : "Incluir" %>
	Recebimento</title>
<%@ include file="header.jsp"%>
</head>
<body class="bg-index">
	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h1 class="mx-auto text-light"><%= request.getAttribute("recebimento") != null ? "Alterar" : "Incluir" %>
			Recebimento
		</h1>
		<div class="container col-xl-6 col-lg-8 col-md-10 col-sm-11">
			<form action="incluir_recebimento" method="post" class="form-box">
				<% Recebimento recebimento = (Recebimento) request.getAttribute("recebimento"); %>
				<% if (recebimento != null) { %>
				<input type="hidden" name="idRecebimento"
					value="<%= recebimento.getIdRecebimento() %>">
				<% } %>
				<div class="mb-3">
					<label for="nome-recebimento" class="form-label">Nome do
						recebimento</label> <input required type="text" class="form-control"
						id="nome-recebimento" name="nome-recebimento"
						value="<%= recebimento != null ? recebimento.getNome() : "" %>">
				</div>
				<div class="mb-3">
					<label for="categoria" class="form-label">Categoria</label> <select
						required id="categoria" name="categoria" class="form-select">
						<option disabled selected>Selecionar</option>
						<option value="1">Salário</option>
						<option value="2">Presente</option>
						<option value="3">Venda</option>
						<option value="4">Freelance</option>
						<option value="5">Transferências</option>
						<option value="6">Renda</option>
						<option value="7">Outro</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="valor" class="form-label">Valor [R$]</label> <input
						required type="number" step="0.01" class="form-control" id="valor"
						name="valor"
						value="<%= recebimento != null ? recebimento.getValor() : "" %>">
				</div>
				<div class="mb-3">
					<label for="data" class="form-label">Data</label> <input required
						type="date" class="form-control" id="data" name="data"
						value="<%= recebimento != null ? new SimpleDateFormat("yyyy-MM-dd").format(recebimento.getData().getTime()) : "" %>">
				</div>
				<div class="mb-3">
					<label for="descricao" class="form-label">Descrição</label> <input
						required type="textarea" class="form-control" id="descricao"
						name="descricao"
						value="<%= recebimento != null ? recebimento.getDescricao() : "" %>">
				</div>
				<button type="submit" class="btn btn-success d-grid col-12 mx-auto">Submit</button>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat, br.com.fiap.bean.Investimento"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("investimento") != null ? "Alterar" : "Incluir" %>
	Investimento</title>
<%@ include file="header.jsp"%>
</head>
<body class="bg-index">
	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h1 class="mx-auto text-light"><%= request.getAttribute("investimento") != null ? "Alterar" : "Incluir" %>
			Investimento
		</h1>
		<div class="container col-xl-6 col-lg-8 col-md-10 col-sm-11">
			<form action="incluir_investimento" method="post" class="form-box">
				<% Investimento investimento = (Investimento) request.getAttribute("investimento"); %>
				<% if (investimento != null) { %>
				<input type="hidden" name="idInvestimento"
					value="<%= investimento.getIdInvestimento() %>">
				<% } %>
				<div class="mb-3">
					<label for="nome" class="form-label">Nome do investimento</label> <input
						required type="text" class="form-control" id="nome" name="nome"
						value="<%= investimento != null ? investimento.getNome() : "" %>">
				</div>
				<div class="mb-3">
					<label for="corretora" class="form-label">Corretora</label> <select
						required id="corretora" name="corretora" class="form-select">
						<option disabled selected>Selecionar</option>
						<option value="1">XP Investimentos</option>
						<option value="2">BTG Pactual</option>
						<option value="3">Toro Investimentos</option>
						<option value="4">Itaú Kinea</option>
						<option value="5">Nu Invest</option>
						<option value="6">Clear Corretora</option>
						<option value="7">Outro</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="valor" class="form-label">Valor Inicial[R$]</label> <input
						required type="number" step="0.01" class="form-control" id="valor"
						name="valor"
						value="<%= investimento != null ? investimento.getValorInicial() : "" %>">
				</div>
				<div class="mb-3">
					<label for="taxa" class="form-label">Taxa[%]</label> <input
						required type="number" step="0.01" class="form-control" id="taxa"
						name="taxa"
						value="<%= investimento != null ? investimento.getTaxa() : "" %>">
				</div>
				<button type="submit" class="btn btn-success d-grid col-12 mx-auto"><%= investimento != null ? "Atualizar" : "Cadastrar" %></button>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
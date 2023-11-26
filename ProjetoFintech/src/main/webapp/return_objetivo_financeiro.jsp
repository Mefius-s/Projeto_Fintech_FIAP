<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Calendar"
	import="br.com.fiap.bean.ObjetivoFinanceiro"
	import="br.com.fiap.bean.Usuario"
	import="br.com.fiap.dao.ObjetivoFinanceiroDAO" import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	int userId = usuarioLogado.getIdUsuario();
	ObjetivoFinanceiroDAO dao = new ObjetivoFinanceiroDAO();
	List<ObjetivoFinanceiro> lista = dao.listarFiltradoUsuario(userId);
	request.setAttribute("listaObjetivoFinanceiro", lista);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<title>Objetivo financeiro</title>
</head>
<body class="bg-index">

	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h2 class="mx-auto text-light">Objetivos financeiros</h2>
	</div>
	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>#</th>
				<th>Valor[R$]</th>
				<th>Data de início</th>
				<th>Data de fim</th>
				<th>Descrição</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="Objetivo" items="${listaObjetivoFinanceiro}">
				<tr>
					<td><a href="incluir_objetivo?id=${Objetivo.idObjetivo}"
						class="text-light"><i class="bi bi-pencil-square text-light"></i></a></td>
					<td><a href="#" class="deletar-objetivo-trigger text-light"
						data-id="${Objetivo.idObjetivo}" data-bs-toggle="modal"
						data-bs-target="#deleteModal"><i
							class="bi bi-trash-fill text-light"></i></a></td>
					<td><c:out value="${Objetivo.idObjetivo}" /></td>
					<td><c:out value="${Objetivo.valor}" /></td>
					<td><fmt:formatDate value="${Objetivo.dataInicio.time}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${Objetivo.dataFim.time}"
							pattern="yyyy-MM-dd" /></td>
					<td><c:out value="${Objetivo.descricao} " /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form action="incluir_objetivo_financeiro.jsp" method="post">
		<button type="submit" class="my-button">Incluir novo objetivo</button>
	</form>

	<!-- Bootstrap Delete Confirmation Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">Confirmar</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Você gostaria de deletar o Objetivo
					Financeiro?</div>
				<div class="modal-footer">
					<form id="deleteForm" action="incluir_objetivo" method="post">
						<input type="hidden" id="deleteId" name="idObjetivo"> <input
							type="hidden" name="delete" value="true">
						<button type="submit" class="btn btn-danger">Deletar</button>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>

<script>
    // When a delete icon is clicked, open the modal and set the deleteId
    document.querySelectorAll('.deletar-objetivo-trigger').forEach(function (icon) {
        icon.addEventListener('click', function (event) {
            event.preventDefault();
            var idObjetivo = this.getAttribute('data-id');
            document.getElementById('deleteId').value = idObjetivo;
        });
    });
</script>


</html>

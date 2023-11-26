<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Calendar"
	import="br.com.fiap.bean.Investimento"
	import="br.com.fiap.bean.Usuario"
	import="br.com.fiap.dao.InvestimentoDAO" import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	int userId = usuarioLogado.getIdUsuario();
	InvestimentoDAO dao = new InvestimentoDAO();
	List<Investimento> lista = dao.listarFiltradoUsuario(userId);
	request.setAttribute("listaInvestimento", lista);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<title>Recebimentos</title>
</head>
<body class="bg-index">

	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h2 class="mx-auto text-light">Investimentos</h2>
	</div>
	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>#</th>
				<th>Corretora</th>
				<th>Nome do investimento</th>
				<th>Valor Inicial[R$]</th>
				<th>Taxa[%]</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="investimento" items="${listaInvestimento}">
				<tr>
					<td><a
						href="incluir_investimento?id=${investimento.idInvestimento}"
						class="text-light"><i class="bi bi-pencil-square text-light"></i></a></td>
					<td><a href="#"
						class="deletar-investimento-trigger text-light"
						data-id="${investimento.idInvestimento}" data-bs-toggle="modal"
						data-bs-target="#deleteModal"><i
							class="bi bi-trash-fill text-light"></i></a></td>
					<td><c:out value="${investimento.idInvestimento}" /></td>
					<td><c:out value="${investimento.idCorretora}" /></td>
					<td><c:out value="${investimento.nome}" /></td>
					<td><c:out value="${investimento.valorInicial} " /></td>
					<td><c:out value="${investimento.taxa}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form action="incluir_investimento.jsp" method="post">
		<button type="submit" class="my-button">Incluir novo
			investimento</button>
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
				<div class="modal-body">Você gostaria de deletar o
					Investimento?</div>
				<div class="modal-footer">
					<form id="deleteForm" action="incluir_investimento" method="post">
						<input type="hidden" id="deleteId" name="idInvestimento">
						<input type="hidden" name="delete" value="true">
						<button type="submit" class="btn btn-danger">Deletar</button>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>

<script>
    // When a delete icon is clicked, open the modal and set the deleteId
    document.querySelectorAll('.deletar-investimento-trigger').forEach(function (icon) {
        icon.addEventListener('click', function (event) {
            event.preventDefault();
            var idInvestimento = this.getAttribute('data-id');
            document.getElementById('deleteId').value = idInvestimento;
        });
    });
</script>


</html>

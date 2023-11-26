<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Calendar"
	import="br.com.fiap.bean.Despesa" import="br.com.fiap.bean.Usuario"
	import="br.com.fiap.dao.DespesaDAO" import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	int userId = usuarioLogado.getIdUsuario();
	DespesaDAO dao = new DespesaDAO();
	List<Despesa> lista = dao.listarFiltradoUsuario(userId);
	request.setAttribute("listaDespesa", lista);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<title>Despesas</title>
</head>
<body class="bg-index">

	<%@ include file="menu.jsp"%>
	<div class="container d-flex flex-column justify-content-center">
		<h2 class="mx-auto text-light">Despesas</h2>
	</div>
	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>#</th>
				<th>Nome da despesa</th>
				<th>Categoria</th>
				<th>Valor[R$]</th>
				<th>Descrição</th>
				<th>Data</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="despesa" items="${listaDespesa}">
				<tr>
					<td><a href="incluir_despesa?id=${despesa.idDespesa}"
						class="text-light"><i class="bi bi-pencil-square text-light"></i></a></td>
					<td><a href="#" class="deletar-despesa-trigger text-light"
						data-id="${despesa.idDespesa}" data-bs-toggle="modal"
						data-bs-target="#deleteModal"><i
							class="bi bi-trash-fill text-light"></i></a></td>
					<td><c:out value="${despesa.idDespesa}" /></td>
					<td><c:out value="${despesa.nome}" /></td>
					<td><c:out value="${despesa.idCategoria}" /></td>
					<td><c:out value="${despesa.valor} " /></td>
					<td><c:out value="${despesa.descricao}" /></td>
					<td><fmt:formatDate value="${despesa.data.time}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form action="incluir_despesa.jsp" method="post">
		<button type="submit" class="my-button">Incluir nova despesa</button>
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
				<div class="modal-body">Você gostaria de deletar a Despesa?</div>
				<div class="modal-footer">
					<form id="deleteForm" action="incluir_despesa" method="post">
						<input type="hidden" id="deleteId" name="idDespesa"> <input
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
    document.querySelectorAll('.deletar-despesa-trigger').forEach(function (icon) {
        icon.addEventListener('click', function (event) {
            event.preventDefault();
            var idDespesa = this.getAttribute('data-id');
            document.getElementById('deleteId').value = idDespesa;
        });
    });
</script>

</html>

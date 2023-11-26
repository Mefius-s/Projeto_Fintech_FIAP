<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Calendar"
	import="br.com.fiap.bean.Recebimento" import="br.com.fiap.bean.Usuario"
	import="br.com.fiap.dao.RecebimentoDAO" import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	int userId = usuarioLogado.getIdUsuario();
	RecebimentoDAO dao = new RecebimentoDAO();
	List<Recebimento> lista = dao.listarFiltradoUsuario(userId);
	request.setAttribute("listaRecebimento", lista);
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
		<h2 class="mx-auto text-light">Recebimentos</h2>
	</div>
	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>#</th>
				<th>Nome do recebimento</th>
				<th>Categoria</th>
				<th>Valor[R$]</th>
				<th>Descrição</th>
				<th>Data</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="recebimento" items="${listaRecebimento}">
				<tr>
					<td><a
						href="incluir_recebimento?id=${recebimento.idRecebimento}"
						class="text-light"><i class="bi bi-pencil-square text-light"></i></a></td>
					<td><a href="#" class="deletar-recebimento-trigger text-light"
						data-id="${recebimento.idRecebimento}" class="text-light"
						data-bs-toggle="modal" data-bs-target="#deleteModal"><i
							class="bi bi-trash-fill text-light"></i></a></td>
					<td><c:out value="${recebimento.idRecebimento}" /></td>
					<td><c:out value="${recebimento.nome}" /></td>
					<td><c:out value="${recebimento.idCategoria}" /></td>
					<td><c:out value="${recebimento.valor} " /></td>
					<td><c:out value="${recebimento.descricao}" /></td>
					<td><fmt:formatDate value="${recebimento.data.time}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="incluir_recebimento">
		<button type="submit" class="my-button">Incluir novo
			recebimento</button>
	</a>

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
					Recebimento?</div>
				<div class="modal-footer">
					<form id="deleteForm" action="incluir_recebimento" method="post">
						<input type="hidden" id="deleteId" name="idRecebimento"> <input
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
    document.querySelectorAll('.deletar-recebimento-trigger').forEach(function (icon) {
        icon.addEventListener('click', function (event) {
        	event.preventDefault();
            var idRecebimento = this.getAttribute('data-id');
            document.getElementById('deleteId').value = idRecebimento;
        });
    });
</script>

</html>




<%@ page import="br.com.fiap.bean.Usuario" import="java.util.List"%>

<%
	Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioLogado");
	String userName = usuarioAutenticado.getNome();
	request.setAttribute("nomeUsuario", userName);
%>

<header class="container mt-3 mb-5">
	<nav
		class="navbar navbar-expand-lg bg-dark border-bottom border-bottom-dark"
		data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand" href="landing_page.jsp"><img
				src="resources/images/logo-sem-fundo-3.png" alt="" class="image-nav"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fa-solid fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#sobre">Sobre</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Serviços </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="return_recebimento.jsp">Recebimentos</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="return_despesa.jsp">Despesas</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="return_investimento.jsp">Investimentos</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="return_objetivo_financeiro.jsp">Objetivos</a></li>
						</ul>
					<li class="nav-item"><a class="nav-link" href="#contatos">Contato</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">${nomeUsuario}</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
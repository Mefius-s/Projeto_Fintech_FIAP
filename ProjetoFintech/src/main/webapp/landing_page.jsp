<!doctype html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Moneyplan</title>
<%@ include file="header.jsp"%>
<%@page import="br.com.fiap.bean.Usuario"%>
</head>

<body class="bg-index">
	<%@ include file="menu.jsp"%>

	<div class="container">
		<% 
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            if (usuarioLogado != null) {
        %>
		<div
			class="alert alert-success alert-dismissible fade show py-1 ps-2 pe-1 d-flex align-items-center"
			role="alert">
			<p class="m-0">
				Olá,
				<%= usuarioLogado.getNome() %>! Bem-vindo(a) de volta!
			</p>
			<button type="button" class="btn-close p-2 position-static ms-auto"
				data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<% 
            }
        %>
	</div>


	<!--------------- sobre ------------------->
	<section class="container mt-5 sobre" id="sobre">
		<div class="row d-flex justify-content-center align-itens-center">
			<div class="col-lg-6 text-light">
				<h1 class="display-2 mt-5">Moneyplan</h1>
				<h5 class="my-4">Seu planejamento financeiro onde e quando
					precisar.</h5>
				<a href="#" class="btn btn-lg btn-outline-danger">Saiba mais</a>
			</div>

			<div class="col-lg-6 text-center">
				<img src="resources/images/dashboard.jpg"
					alt="IlustraÃ§Ã£o sobre a empresa" class="img-fluid rounded-2 mt-2">
			</div>
		</div>
	</section>
	<!-------------- Fim Sobre -------------------->


	<!--------------- Serviços --------------------->
	<section class="container my-5 text-light" id="controle-financeiro">
		<div class="row d-flex justify-content-center align-itens-center">
			<div class="col-lg-6 my-bg m-0 pe-0">
				<h2 class="my-3 mt-2 display-4">Serviços</h2>
				<ul>
					<li>
						<p class="mb-3">Gerencie e monitore as finanças pessoais ou
							empresariais de forma eficiente.</p>
					</li>
				</ul>
				<ul>
					<li>
						<p class="mb-3" id="investimentos">Investimenta em ativos
							financeiros, como ações, FIIs, renda fixa, dentre outros.</p>
					</li>
				</ul>
				<ul>
					<li>
						<p class="mb-3" id="metas">Defina suas metas financeiras e
							deixe que a Moneyplan o guie por todo o caminho.</p>
					</li>
				</ul>

				<a href="#" class="btn btn-lg btn-dark mt-4 mb-2">Mais
					informações <i class="fa-solid fa-chevron-right seta"></i>
				</a>
			</div>

			<div class="col-lg-6 m-0 pe-0">
				<div id="carouselExampleCaptions"
					class="carousel slide carousel-fade">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleCaptions"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleCaptions"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleCaptions"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="resources/images/carousel/controle-financeiro.jpg"
								class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="resources/images/carousel/investimentos.jpg"
								class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="resources/images/carousel/metas.jpg"
								class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</div>
	</section>

	<!----------------- Fim de ServiÃ§os ----------------->

	<!--------------------- Contato -------------------->
	<section class="container-fluid text-light mt-5" id="contatos">
		<div class="container py-5">
			<h3 class="text-center display-2">Contato</h3>
			<h4 class="text-center mb-3">Veja como você pode falar conosco</h4>
			<div class="row mt-5">
				<div class="col-lg-6">
					<div class="row">
						<div class="col-lg-6 my-4 text-center">
							<p>
								<i class="fa-solid fa-location-dot fa-xl"></i>
							</p>
							<h4>Endereço</h4>
							<p>
								Rua Gandavo, 550 <br> SÃ£o Paulo - SP
							</p>
						</div>
						<div class="col-lg-6 my-4 text-center">
							<p>
								<i class="fa-solid fa-phone fa-xl"></i>
							</p>
							<h4>Telefone</h4>
							<p>
								(11) 3923-8549<br> (11) 3982-0558
							</p>
						</div>
						<div class="col-lg-6 my-4 text-center">
							<p>
								<i class="fa-solid fa-envelope fa-xl"></i>
							</p>
							<h4>E-mail</h4>
							<p>helpcenter@moneyplan.com.br</p>
						</div>
						<div class="col-lg-6 my-4 text-center">
							<p>
								<i class="fa-solid fa-clock fa-xl"></i>
							</p>
							<h4>Atendimento</h4>
							<p>
								Segunda - Sexta <br> 08:00 - 22:00
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<form action="">
						<div class="row text-start">
							<div class="col-lg-6 my-3">
								<label for="name" class="form-label">Seu nome</label> <input
									type="text" class="form-control" name="email" id="name">
							</div>
							<div class="col-lg-6 my-3">
								<label for="email" class="form-label">Seu e-mail</label> <input
									type="email" class="form-control" name="email" id="email">
							</div>
							<div class="col-lg-6 my-2">
								<label for="fone" class="form-label">Seu telefone</label> <input
									type="tel" class="form-control" name="fone" id="fone">
							</div>
							<div class="col-lg-6 my-2">
								<label for="assunto" class="form-label">Assunto</label> <input
									type="text" class="form-control" name="assunto" id="assunto">
							</div>
							<div class="col-lg-12 my-2">
								<label for="mensagem" class="form-label">Mensagem</label>
								<textarea type="text" class="form-control" name="mensagem"
									id="mensagem" placeholder="Sua mensagem"></textarea>
							</div>

							<div class="col-lg-12 my-3 d-flex justify-content-end">
								<button type="submit" class="btn btn-outline-danger">Enviar
									mensagem</button>

							</div>

						</div>
					</form>

				</div>
			</div>
		</div>

	</section>

</body>

</html>
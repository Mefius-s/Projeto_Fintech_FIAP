<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Moneyplan</title>

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">

<!-- Minha CSS -->
<link rel="stylesheet" href="resources/css/styles.css">

<!-- Favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="resources/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="resources/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="resources/images/favicon/favicon-16x16.png">
</head>

<body class="bg-login">

	<div class="container pt-5">
		<!-- Error Message -->
		<% if (request.getAttribute("loginError") != null) { %>
		<div
			class="alert alert-<%= request.getAttribute("errorType") %> alert-dismissible fade show py-1 ps-2 pe-1 d-flex align-items-center"
			role="alert">
			<p class="m-0"><%= request.getAttribute("loginError") %></p>
			<button type="button" class="btn-close p-2 position-static ms-auto"
				data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<% } %>

		<% 
			String loginError = request.getParameter("unauthenticatedUser");
			if ("true".equals(loginError)) {
			%>
		<div
			class="alert alert-warning alert-dismissible fade show py-1 ps-2 pe-1 d-flex align-items-center"
			role="alert">
			<p class="m-0">Por favor, faça login para acessar esta página.</p>
			<button type="button" class="btn-close p-2 position-static ms-auto"
				data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<% 
			} 
		%>

	</div>

	<div class=login-box>
		<form action="login" method="post">
			<fieldset>
				<h1 class="title">Login</h1>
				<label for="usuario"></label> <input type="email" name="usuario"
					id="usuario" placeholder="Email Usuário"> <br> <br>
				<label for="senha"></label> <input type="password" name="senha"
					id="senha" placeholder="Senha"> <br> <br>
			</fieldset>
			<button type="submit" class="my-button">Entrar</button>
		</form>
		<p class="mt-2">
			Não tem uma conta? <a href="cadastro">Cadastre-se</a>
		</p>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>

</html>
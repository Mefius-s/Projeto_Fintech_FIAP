<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" import="java.util.*"
	import="br.com.fiap.bean.*" import="java.text.SimpleDateFormat"%>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        request.setAttribute("nome", usuario.getNome());
        request.setAttribute("email", usuario.getEmail());

        Calendar dataNascimento = usuario.getDataNascimento();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        String dataFormatada = dateFormat.format(dataNascimento.getTime());  
        request.setAttribute("data", dataFormatada);

        request.setAttribute("telefone", usuario.getTelefone());
        request.setAttribute("endereco", usuario.getEndereco());
    }
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sucesso</title>
</head>
<body>
	<h1>Cadastro Realizado com Sucesso!</h1>
	<p>As informações do usuário foram cadastradas com sucesso.</p>

	<%-- Exemplo de como mostrar os dados --%>
	<p>Nome: ${nome}</p>
	<p>Email: ${email}</p>
	<p>Data de Nascimento: ${data}</p>
	<p>Telefone: ${telefone}</p>
	<p>Endereço: ${endereco}</p>

</body>
</html>
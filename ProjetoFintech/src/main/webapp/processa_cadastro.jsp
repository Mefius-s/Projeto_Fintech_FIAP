<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="br.com.fiap.bean.*"
	import="br.com.fiap.dao.*"%>


<%
    // Recupera os parâmetros do formulário
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    String dataNascimento = request.getParameter("dataNascimento");
    String telefone = request.getParameter("telefone");
    String endereco = request.getParameter("endereco");
    
    //Cria instancia de Calendar para fazer um parse de dataNascimento
    Calendar data = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
    	data.setTime(sdf.parse(dataNascimento));
    } catch (ParseException e) {
    	e.printStackTrace();
    }
    
    //Nova instancia do usuario para setar seus parâmetros
    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(senha);
    usuario.setDataNascimento(data);
    usuario.setTelefone(telefone);
    usuario.setEndereco(endereco);
    
    //Inserir novos usuarios no banco de dados
    UsuarioDAO dao = new UsuarioDAO();
    dao.cadastrar(usuario);

    // Verifica se os parâmetros não são nulos antes de usá-los
    if (nome != null && email != null && senha != null && dataNascimento != null && telefone != null && endereco != null) {
        // Cria um objeto com as informações do usuário
        out.println(nome + " " + email + " " + senha + " " + data + " " + telefone + " " + endereco);

        // Redireciona para a página de sucesso
        response.sendRedirect("login.jsp");
    } else {
        // Redireciona para a página de erro
        response.sendRedirect("erro.jsp");
    }
%>

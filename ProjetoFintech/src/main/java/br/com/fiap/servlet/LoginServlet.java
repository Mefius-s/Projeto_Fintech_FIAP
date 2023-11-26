package br.com.fiap.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.UsuarioDAO;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = usuarioDao.autenticar(email, senha);

        if (usuario != null) {
            // User authenticated
            request.getSession().setAttribute("usuarioLogado", usuario);
            response.sendRedirect("landing_page.jsp"); // Redirect to landing page
        } else {
            // Authentication failed
            request.setAttribute("loginError", "Email ou senha inválidos");
            request.setAttribute("errorType", "danger");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}

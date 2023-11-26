package br.com.fiap.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fiap.bean.Usuario;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLogado");
        String path = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println(path);
        
        if (path.startsWith("/resources/") || path.contains("cadastro") || path.contains("Cadastro")) {
            chain.doFilter(request, response); // Bypass authentication check
            return;
        }
        if (usuarioLogado == null && !req.getRequestURI().endsWith("login.jsp") && !req.getRequestURI().contains("/login")) {
        	res.sendRedirect("login.jsp?unauthenticatedUser=true");
        } else {
            chain.doFilter(request, response); // User is logged in, proceed
        }
    }
	
}

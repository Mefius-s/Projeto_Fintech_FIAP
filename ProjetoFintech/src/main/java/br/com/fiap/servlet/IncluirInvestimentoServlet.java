package br.com.fiap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.bean.Investimento;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.InvestimentoDAO;


@WebServlet("/incluir_investimento")
public class IncluirInvestimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idInvestimentoStr = request.getParameter("id");

	    if (idInvestimentoStr != null) {
	        try {
	            int idInvestimento = Integer.parseInt(idInvestimentoStr);
	            InvestimentoDAO dao = new InvestimentoDAO();
	            Investimento investimento = dao.buscarPorId(idInvestimento);
	            if (investimento != null) {
	                request.setAttribute("investimento", investimento);
	            }
	        } catch (NumberFormatException e) {
	        	System.out.println("Número inválido!");
	        }
	    }
	    request.getRequestDispatcher("incluir_investimento.jsp").forward(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idInvestimentoStr = request.getParameter("idInvestimento");
	    InvestimentoDAO dao = new InvestimentoDAO();
	    
	    String deleteFlag = request.getParameter("delete");
	    if (deleteFlag != null && idInvestimentoStr != null) {
	        int idInvestimento = Integer.parseInt(idInvestimentoStr);
	        dao.deletar(idInvestimento);
	        response.sendRedirect("return_investimento.jsp");
	        return;
	    }
	    
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        int userId = usuarioLogado.getIdUsuario();
        
        // Retrieve and parse form parameters
        String nome = request.getParameter("nome");
        int idCorretora = Integer.parseInt(request.getParameter("corretora"));
        double valorInicial = Double.parseDouble(request.getParameter("valor"));
        double taxa = Double.parseDouble(request.getParameter("taxa"));
		
		System.out.println(nome + " " + idCorretora + " " + valorInicial + " " + taxa);
		
        // Create an instance of Investimento and set its properties
        Investimento investimento = new Investimento();
        investimento.setIdUsuario(userId); // This should come from session or a similar source
        investimento.setIdCorretora(idCorretora);
        investimento.setNome(nome);
        investimento.setValorInicial(valorInicial);
        investimento.setTaxa(taxa);
        
        if (idInvestimentoStr != null) {
            int idInvestimento = Integer.parseInt(idInvestimentoStr);
            investimento.setIdInvestimento(idInvestimento);
            dao.atualizar(investimento);
        } else {
            dao.cadastrar(investimento);
        }
		
		request.getRequestDispatcher("return_investimento.jsp").forward(request, response);
	}

}
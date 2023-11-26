package br.com.fiap.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.bean.ObjetivoFinanceiro;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.ObjetivoFinanceiroDAO;


@WebServlet("/incluir_objetivo")
public class IncluirObjetivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idObjetivoStr = request.getParameter("id");

	    if (idObjetivoStr != null) {
	        try {
	            int idObjetivo = Integer.parseInt(idObjetivoStr);
	            ObjetivoFinanceiroDAO dao = new ObjetivoFinanceiroDAO();
	            ObjetivoFinanceiro objetivo = dao.buscarPorId(idObjetivo);
	            if (objetivo != null) {
	                request.setAttribute("objetivoFinanceiro", objetivo);
	            }
	        } catch (NumberFormatException e) {
	        	System.out.println("Número inválido!");
	        }
	    }
	    request.getRequestDispatcher("incluir_objetivo_financeiro.jsp").forward(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String idObjetivoStr = request.getParameter("idObjetivo");
	    ObjetivoFinanceiroDAO dao = new ObjetivoFinanceiroDAO();
	    
	    String deleteFlag = request.getParameter("delete");
	    if (deleteFlag != null && idObjetivoStr != null) {
	        int idObjetivo = Integer.parseInt(idObjetivoStr);
	        dao.deletar(idObjetivo);
	        response.sendRedirect("incluir_objetivo_financeiro.jsp");
	        return;
	    }
	    
		float valor = Float.parseFloat(request.getParameter("valor"));
		String dateInicioString = request.getParameter("data-inicio");
		String dateFimString = request.getParameter("data-fim");
		String descricao = request.getParameter("descricao");
		
		System.out.println(valor + " " + dateInicioString + " " + dateFimString + " " + descricao);
		
        // Create a Calendar instance to parse the date string
        Calendar dataInicio = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataInicio.setTime(sdf.parse(dateInicioString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Calendar dataFim= Calendar.getInstance();
        try {
            dataFim.setTime(sdf.parse(dateFimString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro();
        objetivo.setValor(valor);
        objetivo.setDataInicio(dataInicio);
        objetivo.setDataFim(dataFim);
        objetivo.setDescricao(descricao);

        // Retrieve the Usuario object from the session
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        int userId = usuarioLogado.getIdUsuario();
        objetivo.setIdUsuario(userId);
        
        if (idObjetivoStr != null) {
            int idObjetivo = Integer.parseInt(idObjetivoStr);
            objetivo.setIdObjetivo(idObjetivo);
            dao.atualizar(objetivo);
        } else {
            dao.cadastrar(objetivo);
        }
        
		request.getRequestDispatcher("return_objetivo_financeiro.jsp").forward(request, response);
	}

}
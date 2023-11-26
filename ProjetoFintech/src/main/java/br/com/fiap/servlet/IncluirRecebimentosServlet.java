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

import br.com.fiap.bean.Recebimento;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.RecebimentoDAO;


@WebServlet("/incluir_recebimento")
public class IncluirRecebimentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String idRecebimentoStr = request.getParameter("id");
		
        if (idRecebimentoStr != null) {
            try {
                int idRecebimento = Integer.parseInt(idRecebimentoStr);
                RecebimentoDAO dao = new RecebimentoDAO();
                Recebimento recebimento = dao.buscarRecebimentoPorId(idRecebimento);
                if (recebimento != null) {
                    request.setAttribute("recebimento", recebimento);
                }
            } catch (NumberFormatException e) {
            	System.out.println("Número inválido!");
            }
        }
        request.getRequestDispatcher("incluir_recebimento.jsp").forward(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idRecebimentoStr = request.getParameter("idRecebimento");
        RecebimentoDAO dao = new RecebimentoDAO();
		
        String deleteFlag = request.getParameter("delete");
        if (deleteFlag != null) {
        	int idRecebimento = Integer.parseInt(idRecebimentoStr);
        	dao.deletar(idRecebimento);
        	response.sendRedirect("return_recebimento.jsp");
        	return;
        }
		
		String nome = request.getParameter("nome-recebimento");
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		double valor = Double.parseDouble(request.getParameter("valor"));
		String dateString = request.getParameter("data");
		String descricao = request.getParameter("descricao");
		
		System.out.println(nome + " " + categoria + " " + valor + " " + dateString + " " + descricao);
		
        // Create a Calendar instance to parse the date string
        Calendar data = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            data.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new instance of Recebimento and set its properties
        Recebimento recebimento = new Recebimento();
        recebimento.setNome(nome);
        recebimento.setIdCategoria(categoria); // Assuming you have a method to set category by ID
        recebimento.setValor(valor);
        recebimento.setData(data);
        recebimento.setDescricao(descricao);

        // Retrieve the Usuario object from the session
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        int userId = usuarioLogado.getIdUsuario();
        recebimento.setIdUsuario(userId);
        
		if (idRecebimentoStr != null) {
			int idRecebimento = Integer.parseInt(idRecebimentoStr);
			recebimento.setIdRecebimento(idRecebimento);
			dao.atualizar(recebimento);
		} else {
			dao.cadastrar(recebimento);
		}

        request.getRequestDispatcher("return_recebimento.jsp").forward(request, response);
	}

}
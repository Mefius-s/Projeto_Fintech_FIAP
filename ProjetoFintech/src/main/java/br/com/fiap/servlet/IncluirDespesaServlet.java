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

import br.com.fiap.bean.Despesa;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.DespesaDAO;


@WebServlet("/incluir_despesa")
public class IncluirDespesaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idDespesaStr = request.getParameter("id");

        if (idDespesaStr != null) {
            try {
                int idDespesa = Integer.parseInt(idDespesaStr);
                DespesaDAO dao = new DespesaDAO();
                Despesa despesa = dao.buscarPorId(idDespesa);
                if (despesa != null) {
                    request.setAttribute("despesa", despesa);
                }
            } catch (NumberFormatException e) {
            	System.out.println("Número inválido!");
            }
        }
        request.getRequestDispatcher("incluir_despesa.jsp").forward(request, response);
    }

       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idDespesaStr = request.getParameter("idDespesa");
        DespesaDAO dao = new DespesaDAO();
        
        String deleteFlag = request.getParameter("delete");
        if (deleteFlag != null && idDespesaStr != null) {
            int idDespesa = Integer.parseInt(idDespesaStr);
            dao.deletar(idDespesa);
            response.sendRedirect("return_despesa.jsp");
            return;
        }
        String nome = request.getParameter("nome"); // Make sure the form parameter name matches
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        String dateString = request.getParameter("data");
        String descricao = request.getParameter("descricao");

        // Parse the date string into Calendar
        Calendar data = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            data.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new instance of Despesa and set its properties
        Despesa despesa = new Despesa();
        despesa.setNome(nome);
        despesa.setIdCategoria(categoria); // Assuming you have a method to set category by ID
        despesa.setValor(valor);
        despesa.setData(data);
        despesa.setDescricao(descricao);
        
        // Retrieve the Usuario object from the session
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        int userId = usuarioLogado.getIdUsuario();
        despesa.setIdUsuario(userId);

        if (idDespesaStr != null) {
            int idDespesa = Integer.parseInt(idDespesaStr);
            despesa.setIdDespesa(idDespesa);
            dao.atualizar(despesa);
        } else {
            dao.cadastrar(despesa);
        }

        request.getRequestDispatcher("return_despesa.jsp").forward(request, response);
    }
}

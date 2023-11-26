package br.com.fiap.testes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.fiap.bean.Recebimento;
import br.com.fiap.dao.RecebimentoDAO;

public class TesteRecebimento {

    public static void main(String[] args) {
    	
    	System.out.println("Estou no teste!");

        RecebimentoDAO dao = new RecebimentoDAO();

        String[] names = {"Salário", "Presente", "Venda", "Freelance", "Investimento"};
        String[] descriptions = {"Recebimento de salário mensal", "Recebimento de presente em dinheiro", "Recebimento de venda de produto", "Recebimento de trabalho freelance", "Recebimento de retorno de investimento"};

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Recebimento recebimento = new Recebimento();

            Calendar date = Calendar.getInstance();
            int year = random.nextInt(5) + 2018;
            int month = random.nextInt(12);
            int day = random.nextInt(28) + 1;
            date.set(year, month, day);

            recebimento.setIdUsuario(random.nextInt(5) + 1);
            recebimento.setIdCategoria(random.nextInt(5) + 1);
            recebimento.setNome(names[i]);
            recebimento.setValor(random.nextInt(901) + 100);  // Generates a random number between 100 and 1000
            recebimento.setData(date);
            recebimento.setDescricao(descriptions[i]);

            dao.cadastrar(recebimento);
        }

        System.out.println("Cadastro realizado com sucesso!");

        List<Recebimento> lista = dao.listar();

        for (Recebimento item : lista) {
            System.out.println(item.getIdRecebimento() + " " + item.getIdUsuario() + " " + item.getIdCategoria() + " " + item.getNome() + " " + item.getValor() + " " + item.getData().getTime() + " " + item.getDescricao());
        }
    }
}

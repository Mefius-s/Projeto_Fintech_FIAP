package br.com.fiap.testes;

import java.util.List;
import java.util.Random;

import br.com.fiap.bean.Investimento;
import br.com.fiap.dao.InvestimentoDAO;

public class TesteInvestimento {

    public static void main(String[] args) {

        InvestimentoDAO dao = new InvestimentoDAO();

        String[] names = {"Ações", "CDB", "LCI", "LC", "Fundo Imobiliário"};
        
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Investimento investimento = new Investimento();

            investimento.setIdUsuario(random.nextInt(5) + 1);
            investimento.setIdCorretora(random.nextInt(5) + 1);
            investimento.setNome(names[i]);
            investimento.setValorInicial(random.nextInt(4501) + 500);  // Generates a random number between 500 and 5000
            investimento.setTaxa(random.nextDouble() * (1.15 - 1.08) + 1.08);  // Generates a random number between 1.08 and 1.15

            dao.cadastrar(investimento);
        }

        System.out.println("Cadastro realizado com sucesso!");

        List<Investimento> lista = dao.listar();

        for (Investimento item : lista) {
            System.out.println(item.getIdInvestimento() + " " + item.getIdUsuario() + " " + item.getIdCorretora() + " " + item.getNome() + " " + item.getValorInicial() + " " + item.getTaxa());
        }
    }
}

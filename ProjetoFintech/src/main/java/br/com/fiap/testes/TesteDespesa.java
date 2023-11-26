package br.com.fiap.testes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.fiap.bean.Despesa;
import br.com.fiap.dao.DespesaDAO;

public class TesteDespesa {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();

        String[] names = {"Comida", "Transporte", "Educação", "Lazer", "Saúde"};
        String[] descriptions = {"Gastos com alimentação", "Gastos com transporte público e gasolina", "Gastos com mensalidade escolar", "Gastos com entretenimento", "Gastos com consultas médicas e medicamentos"};

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Despesa despesa = new Despesa();

            Calendar date = Calendar.getInstance();
            int year = random.nextInt(5) + 2018;
            int month = random.nextInt(12);
            int day = random.nextInt(28) + 1;
            date.set(year, month, day);

            despesa.setIdUsuario(random.nextInt(5) + 1);
            despesa.setIdCategoria(random.nextInt(5) + 1);
            despesa.setNome(names[i]);
            despesa.setValor(random.nextInt(901) + 100);  // Generates a random number between 100 and 1000
            despesa.setData(date);
            despesa.setDescricao(descriptions[i]);

            dao.cadastrar(despesa);
        }

        System.out.println("Cadastro realizado com sucesso!");

        List<Despesa> lista = dao.listar();

        for (Despesa item : lista) {
            System.out.println(item.getIdDespesa() + " " + item.getIdUsuario() + " " + item.getIdCategoria() + " " + item.getNome() + " " + item.getValor() + " " + item.getData().getTime() + " " + item.getDescricao());
        }
    }
}

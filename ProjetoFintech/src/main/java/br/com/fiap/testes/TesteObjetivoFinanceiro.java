package br.com.fiap.testes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.fiap.bean.ObjetivoFinanceiro;
import br.com.fiap.dao.ObjetivoFinanceiroDAO;

public class TesteObjetivoFinanceiro {

	public static void main(String[] args) {
		
		ObjetivoFinanceiroDAO dao = new ObjetivoFinanceiroDAO();
		
        int[] userIds = {1, 2, 3, 4, 5};
        float[] valores = {200, 250, 400, 150, 600};

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro();

            Calendar startDate = Calendar.getInstance();
            startDate.set(2024, random.nextInt(12), random.nextInt(28) + 1);

            Calendar endDate = (Calendar) startDate.clone();
            int monthsToAdd = random.nextInt(25) + 12;
            endDate.add(Calendar.MONTH, monthsToAdd);

            objetivo.setIdUsuario(userIds[i]);
            objetivo.setValor(valores[i]);
            objetivo.setDataInicio(startDate);
            objetivo.setDataFim(endDate);

            dao.cadastrar(objetivo);
            System.out.println("Cadastro realizado com sucesso!");
        }
		
		
		List<ObjetivoFinanceiro> lista = dao.listar();
		
	    for (ObjetivoFinanceiro item : lista) {
	        System.out.println(item.getIdObjetivo() + " " + item.getIdUsuario() + " " + item.getValor() + " " + item.getDataInicio().getTime() + " " + item.getDataFim().getTime());
	      }
		
	}

}
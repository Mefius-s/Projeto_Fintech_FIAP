package br.com.fiap.testes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.UsuarioDAO;

public class TesteUsuario {

    public static void main(String[] args) {

        UsuarioDAO dao = new UsuarioDAO();

        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma"};
        String[] emails = {"alice@email.com", "bob@email.com", "charlie@email.com", "david@email.com", "emma@email.com"};
        String[] passwords = {"password1", "password2", "password3", "password4", "password5"};
        String[] phones = {"(11) 91234-5678", "(11) 98765-4321", "(11) 92345-6789", "(11) 95678-4321", "(11) 99876-5432"};
        String[] addresses = {"123 Main St", "456 Elm St", "789 Oak St", "101 Maple St", "202 Birch St"};

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();

            Calendar birthDate = Calendar.getInstance();
            int year = random.nextInt(50) + 1950;
            int month = random.nextInt(12);
            int day = random.nextInt(28) + 1;
            birthDate.set(year, month, day);

            usuario.setNome(names[i]);
            usuario.setEmail(emails[i]);
            usuario.setSenha(passwords[i]);
            usuario.setDataNascimento(birthDate);
            usuario.setTelefone(phones[i]);
            usuario.setEndereco(addresses[i]);

            dao.cadastrar(usuario);
        }

        System.out.println("Cadastro realizado com sucesso!");

        List<Usuario> lista = dao.listar();

        for (Usuario item : lista) {
            System.out.println(item.getIdUsuario() + " " + item.getNome() + " " + item.getEmail() + " " + item.getDataNascimento().getTime() + " " + item.getTelefone() + " " + item.getEndereco());
        }
    }
}

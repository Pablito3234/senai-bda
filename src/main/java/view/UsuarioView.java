package view;

import models.Usuario;
import services.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UsuarioView {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Usuario telaLogin(Scanner input, UsuarioService service) {
        String email = solicitarEmail(input);
        Usuario usuarioExistente = buscarUsuarioSeJaLogado(service, email);
        if (usuarioExistente != null) {
            return usuarioExistente;
        }

        return cadastrarNovoUsuario(input, service, email);
    }

    private static String solicitarEmail(Scanner input) {
        System.out.print("Digite seu email: ");
        return input.nextLine().trim();
    }

    private static Usuario buscarUsuarioSeJaLogado(UsuarioService service, String email) {
        if (!service.isUsuarioLogado(email)) {
            return null;
        }

        System.out.println("Você já está logado!\n");
        return service.buscarPorEmail(email);
    }

    private static Usuario cadastrarNovoUsuario(Scanner input, UsuarioService service, String email) {
        while (true) {
            String nome = solicitarNome(input);
            Date dataNascimento = solicitarDataNascimento(input);

            try {
                service.cadastrarUsuario(nome, email, dataNascimento);
                System.out.println("Cadastro realizado com sucesso!\n");
                return service.buscarPorEmail(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Cadastro inválido: " + e.getMessage() + "\n");
                if (deveEncerrarCadastro(input)) {
                    return null;
                }
            }
        }
    }

    private static String solicitarNome(Scanner input) {
        System.out.println("Cadastre-se");
        System.out.print("Digite seu nome: ");
        return input.nextLine().trim();
    }

    private static Date solicitarDataNascimento(Scanner input) {
        while (true) {
            try {
                System.out.print("Digite sua data de nascimento (dd/MM/aaaa): ");
                DATE_FORMAT.setLenient(false);
                return DATE_FORMAT.parse(input.nextLine().trim());
            } catch (ParseException e) {
                System.out.println("Formato inválido. Use dd/MM/aaaa.\n");
            }
        }
    }

    private static boolean deveEncerrarCadastro(Scanner input) {
        System.out.print("Deseja sair do cadastro? (s/n): ");
        return input.nextLine().trim().equalsIgnoreCase("s");
    }
}

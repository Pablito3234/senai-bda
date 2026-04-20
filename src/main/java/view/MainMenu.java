package view;

import models.Usuario;
import services.PostagemService;
import services.UsuarioService;

import java.util.Scanner;

public class MainMenu {
    public static void getMainMenu(Usuario loggedUser, Scanner sc, UsuarioService usuarioService, PostagemService postagemService) {
        while (true) {
            if (loggedUser != null) {
                System.out.printf("%nBem-vindo, %s!%n", loggedUser.getNome());
                System.out.println("""
                        Opções
                        1 - Criar Postagem
                        2 - Deletar Postagem
                        3 - Listar Postagens
                        4 - Sair
                        """);
            } else {
                System.out.println("""
                        Você não está logado
                        1 - Logar/Cadastrar
                        2 - Sair
                        """);
            }

            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim();

            if (loggedUser == null) {
                switch (opcao) {
                    case "1" -> {
                        Usuario usuarioLogado = UsuarioView.telaLogin(sc, usuarioService);
                        if (usuarioLogado != null){
                            getMainMenu(usuarioLogado, sc, usuarioService, postagemService);
                        }
                    }
                    case "2" -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } else {
                switch (opcao) {
                    case "1" -> PostagemView.getPostagemMenu(loggedUser, sc);
//                    case "2" -> System.out.println("(Deletar postagem - não implementado)");
//                    case "3" -> System.out.println("(Listar postagens - não implementado)");
                    case "4" -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }
}

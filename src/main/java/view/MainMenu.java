package view;

import models.Usuario;
import services.PostagemService;
import services.UsuarioService;

import java.util.Scanner;

public class MainMenu {
    public static void getMainMenu(Usuario loggedUser, Scanner sc, UsuarioService usuarioService, PostagemService postagemService) {
        while (true) {
            try {
                if (loggedUser == null) {
                    loggedUser = processarMenuDeslogado(sc, usuarioService);
                    if (loggedUser == null) {
                        return;
                    }
                    continue;
                }

                if (processarMenuLogado(loggedUser, sc, postagemService)) {
                    System.out.println("Saindo...");
                    return;
                }
            } catch (RuntimeException e) {
                System.out.println("Erro ao processar ação: " + e.getMessage() + "\n");
            }
        }
    }

    private static Usuario processarMenuDeslogado(Scanner sc, UsuarioService usuarioService) {
        exibirMenuDeslogado();
        String opcao = lerOpcao(sc);

        return switch (opcao) {
            case "1" -> UsuarioView.telaLogin(sc, usuarioService);
            case "2" -> null;
            default -> {
                System.out.println("Opção inválida.\n");
                yield null;
            }
        };
    }

    private static boolean processarMenuLogado(Usuario loggedUser, Scanner sc, PostagemService postagemService) {
        exibirMenuLogado(loggedUser);
        String opcao = lerOpcao(sc);

        return switch (opcao) {
            case "1" -> {
                PostagemView.getPostagemMenu(loggedUser, sc, postagemService);
                yield false;
            }
            case "2" -> true;
            default -> {
                System.out.println("Opção inválida.\n");
                yield false;
            }
        };
    }

    private static void exibirMenuDeslogado() {
        System.out.println("\nVocê não está logado");
        System.out.println("1 - Logar/Cadastrar");
        System.out.println("2 - Sair");
    }

    private static void exibirMenuLogado(Usuario loggedUser) {
        System.out.printf("%nBem-vindo, %s!%n", loggedUser.getNome());
        System.out.println("Opções");
        System.out.println("1 - Gerenciar Postagens");
        System.out.println("2 - Sair");
    }

    private static String lerOpcao(Scanner sc) {
        System.out.print("Escolha: ");
        return sc.nextLine().trim();
    }
}

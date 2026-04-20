package view;

import models.Usuario;
import services.PostagemService;

import java.util.Scanner;

public class PostagemView {
    public static void getPostagemMenu(Usuario usuarioLogado, Scanner input, PostagemService postagemService) {
        if (usuarioLogado == null) {
            throw new IllegalArgumentException("Este menu só pode ser acessado por usuario logado");
        }

        while (true) {
            exibirMenuPostagem();
            String opcao = lerOpcao(input);

            if (processarOpcao(opcao, usuarioLogado, input, postagemService)) {
                return;
            }
        }
    }

    private static void exibirMenuPostagem() {
        System.out.println("\nSelecione uma opção");
        System.out.println("1 - Criar Postagem");
        System.out.println("2 - Deletar Postagem");
        System.out.println("3 - Listar Postagens");
        System.out.println("4 - Voltar");
    }

    private static String lerOpcao(Scanner input) {
        System.out.print("Escolha: ");
        return input.nextLine().trim();
    }

    private static boolean processarOpcao(String opcao, Usuario usuarioLogado, Scanner input, PostagemService postagemService) {
        switch (opcao) {
            case "1":
                criarPostagem(input, postagemService, usuarioLogado);
                return false;
            case "2":
                deletarPostagem(input, postagemService, usuarioLogado);
                return false;
            case "3":
                listarPostagens(postagemService, usuarioLogado);
                return false;
            case "4":
                return true;
            default:
                System.out.println("Opção inválida!\n");
                return false;
        }
    }

    private static void criarPostagem(Scanner input, PostagemService postagemService, Usuario usuarioLogado) {
        try {
            System.out.print("Digite o conteúdo da postagem: ");
            String conteudo = input.nextLine().trim();
            postagemService.criarPostagem(conteudo, usuarioLogado);
            System.out.println("Postagem criada com sucesso!\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível criar a postagem: " + e.getMessage() + "\n");
        }
    }

    private static void deletarPostagem(Scanner input, PostagemService postagemService, Usuario usuarioLogado) {
        System.out.print("Digite o ID da postagem a deletar: ");
        while (true) {
            try {
                Integer idDeletar = Integer.parseInt(input.nextLine().trim());
                postagemService.deletarPostagem(idDeletar, usuarioLogado);
                System.out.println("Postagem deletada com sucesso!\n");
                return;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, digite um número inteiro: ");
            } catch (RuntimeException e) {
                System.out.println("Não foi possível deletar a postagem: " + e.getMessage() + "\n");
                return;
            }
        }
    }

    private static void listarPostagens(PostagemService postagemService, Usuario usuarioLogado) {
        System.out.println(postagemService.listarPostagens(usuarioLogado));
    }
}

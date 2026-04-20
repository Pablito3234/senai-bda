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
            System.out.println("Selecione uma opção\n");
            System.out.println("1 - Criar Postagem\n");
            System.out.println("2 - Deletar Postagem\n");
            System.out.println("3 - Listar Postagens\n");
            System.out.println("4 - Voltar\n");
            System.out.print("Escolha: ");
            String opcao = input.nextLine().trim();
            switch (opcao) {
                case "1":
                    System.out.print("Digite o conteúdo da postagem: ");
                    String conteudo = input.nextLine().trim();
                    postagemService.criarPostagem(conteudo, usuarioLogado);
                    System.out.println("Postagem criada com sucesso!\n");
                    break;
                case "2":
                    System.out.print("Digite o ID da postagem a deletar: ");
                    while (true){
                        try {
                            Integer idDeletar = Integer.parseInt(input.nextLine().trim());
                            postagemService.deletarPostagem(idDeletar, usuarioLogado);
                            break;
                        } catch (NumberFormatException e){
                            System.err.println("Entrada invalida, digite um numero inteiro:");
                        } catch (RuntimeException e){
                            System.err.println(e.getMessage());
                            break;
                        }
                    }
                    System.out.println("Postagem deletada com sucesso!\n");
                    break;
                case "3":
                    System.out.println(postagemService.listarPostagens(usuarioLogado));
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida!\n");
                    break;
            }
        }
    }
}

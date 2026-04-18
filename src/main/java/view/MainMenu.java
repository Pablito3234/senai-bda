package view;

import models.Usuario;

import java.util.Scanner;

public class MainMenu {
    public static void getMainMenu(Usuario loggedUser){
        Scanner sc = new Scanner(System.in);
        String mensagem = "";
        if (loggedUser != null){
            mensagem = """
                    Opções
                    1 - Criar Postagem
                    2 - Deletar Postagem
                    3 - Listar Postagens
                    4 - Sair
                    """;
        } else {
            mensagem = """
                    Você não está logado
                    1 - Logar
                    2 - Sair
                    """;
        }
    }
}

package view;

import models.Usuario;
import services.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UsuarioView {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Usuario telaLogin(Scanner input, UsuarioService service){
        System.out.println("Digite seu email\n");
        String email = input.nextLine().trim();

        if (service.isUsuarioLogado(email)){
            System.out.println("Você está logado!\n");
            return service.buscarPorEmail(email);
        } else {
            while (true){
                System.out.println("Cadastre-se:\n");
                System.out.println("Digite seu nome:\n");
                String nome = input.nextLine().trim();

                System.out.println("Digite sua data de nascimento (dd/mm/aaaa):\n");
                Date dataNascimento = null;
                while (dataNascimento == null){
                    try {
                        String dataStr = input.nextLine().trim();
                        DATE_FORMAT.setLenient(false);
                        dataNascimento = DATE_FORMAT.parse(dataStr);
                    } catch (ParseException e) {
                        System.out.println("Formato inválido. Use dd/mm/aaaa\n");
                    }
                }
                try {
                    service.cadastrarUsuario(nome, email, dataNascimento);
                    System.out.println("Cadastrado com sucesso");
                    return service.buscarPorEmail(email);
                } catch (IllegalArgumentException e){
                    System.out.println("Cadastro invalido: "+e.getMessage() + "\n");
                    System.out.println("Quer sair? (s/n): ");
                    if (input.nextLine().equals("s")){
                        break;
                    }
                }
            }
        }
        return null;
    }
}

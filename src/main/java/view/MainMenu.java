package view;

import models.Usuario;
import services.PostagemService;
import services.Service;
import services.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void getMainMenu(Usuario loggedUser, Scanner sc, List<Service> services) {
        while (true) {
            try {
                if (loggedUser == null) {
                    loggedUser = processarMenuDeslogado(sc, services);
                    if (loggedUser == null) {
                        return;
                    }
                    continue;
                }

                if (processarMenuLogado(loggedUser, sc, services)) {
                    System.out.println("Saindo...");
                    return;
                }
            } catch (RuntimeException e) {
                System.out.println("Erro ao processar ação: " + e.getMessage() + "\n");
            }
        }
    }

    private static Usuario processarMenuDeslogado(Scanner sc, List<Service> services) {
        exibirMenuDeslogado();
        String opcao = lerOpcao(sc);
        UsuarioService usuarioService = getService(services, UsuarioService.class);

        return switch (opcao) {
            case "1" -> UsuarioView.telaLogin(sc, usuarioService);
            case "2" -> null;
            default -> {
                System.out.println("Opção inválida.\n");
                yield null;
            }
        };
    }

    private static boolean processarMenuLogado(Usuario loggedUser, Scanner sc, List<Service> services) {
        exibirMenuLogado(loggedUser);
        String opcao = lerOpcao(sc);
        PostagemService postagemService = getService(services, PostagemService.class);
        UsuarioService usuarioService = getService(services, UsuarioService.class);

        return switch (opcao) {
            case "1" -> {
                PostagemView.getPostagemMenu(loggedUser, sc, postagemService);
                yield false;
            }
            case "2" -> true;
            case "3" -> {
                usuarioService.exportarUsuariosParaCsv("usuarios.csv");
                System.out.println("CSV exportado com sucesso para usuarios.csv\n");
                yield false;
            }
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
        System.out.println("3 - Exportar Usuários para CSV");
    }

    private static String lerOpcao(Scanner sc) {
        System.out.print("Escolha: ");
        return sc.nextLine().trim();
    }

    private static <T extends Service> T getService(List<Service> services, Class<T> serviceClass) {
        return services.stream()
                .filter(serviceClass::isInstance)
                .map(serviceClass::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Serviço não encontrado: " + serviceClass.getSimpleName()));
    }
}
import config.HibernateUtil;
import org.hibernate.SessionFactory;
import services.PostagemService;
import services.Service;
import services.UsuarioService;
import view.MainMenu;

import java.util.List;
import java.util.Scanner;

void main() {
    try (Scanner input = new Scanner(System.in)) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UsuarioService usuarioService = new UsuarioService(sessionFactory);
        PostagemService postagemService = new PostagemService(sessionFactory);
        List<Service> services = List.of(usuarioService, postagemService);
        MainMenu.getMainMenu(null, input, services);
    } catch (RuntimeException e) {
        System.out.println("Erro inesperado na aplicação: " + e.getMessage());
    }
}

import config.HibernateUtil;
import org.hibernate.SessionFactory;
import services.PostagemService;
import services.UsuarioService;
import view.MainMenu;
import java.util.Scanner;

void main() {
    try (Scanner input = new Scanner(System.in)) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UsuarioService usuarioService = new UsuarioService(sessionFactory);
        PostagemService postagemService = new PostagemService(sessionFactory);
        MainMenu.getMainMenu(null, input, usuarioService, postagemService);
    } catch (RuntimeException e) {
        System.out.println("Erro inesperado na aplicação: " + e.getMessage());
    }
}

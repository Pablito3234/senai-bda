package services;

import models.Usuario;
import org.hibernate.SessionFactory;
import repository.UsuarioRepository;

import java.util.Date;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(SessionFactory sessionFactory) {
        this.usuarioRepository = new UsuarioRepository(sessionFactory);
    }

    public void cadastrarUsuario(String nome, String email, Date dataNascimento){
        if (nome.length() < 5 || nome.length() > 50){
            throw new IllegalArgumentException("Nome deve ser entre 5 e 50 caracteres");
        }
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException(("Deve informar email"));
        }
        if ((dataNascimento == null)){
            throw new IllegalArgumentException("Deve ter Data de nascimento");
        }
        usuarioRepository.salvar(new Usuario(nome, email, dataNascimento));
    }
}

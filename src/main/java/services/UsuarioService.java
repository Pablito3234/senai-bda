package services;

import jakarta.persistence.RollbackException;
import models.Usuario;
import org.hibernate.SessionFactory;
import repository.UsuarioRepository;
import utils.CSVUtils;

import java.util.Date;
import java.util.List;

public class UsuarioService implements Service{
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
        try {
            usuarioRepository.salvar(new Usuario(nome, email, dataNascimento));
        } catch (RollbackException e) {
            throw new RuntimeException("Aconteceu um erro ao salvar o usuario: " + e.getMessage());
        }
    }

    public boolean isUsuarioLogado(String email){
        Usuario queryUsuario = usuarioRepository.buscarPorEmail(email);
        return queryUsuario != null;
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.buscarPorEmail(email);
    }

    public void exportarUsuariosParaCsv(String caminhoArquivo) {
        List<Usuario> usuarios = usuarioRepository.listarTodos();
        CSVUtils.exportarUsuarios(usuarios, caminhoArquivo);
    }
}

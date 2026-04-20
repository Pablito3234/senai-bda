package services;

import jakarta.persistence.RollbackException;
import models.Postagem;
import models.Usuario;
import org.hibernate.SessionFactory;
import repository.PostagemRepository;

public class PostagemService {
    private final PostagemRepository postagemRepository;

    public PostagemService(SessionFactory sessionFactory) {
        this.postagemRepository = new PostagemRepository(sessionFactory);
    }

    public void criarPostagem(String conteudo, Usuario usuario){
//        if (postagem.getConteudo().isBlank()){
//            throw new IllegalArgumentException("O conteudo não pode ser vazio");
//        }
//        if (postagem.getConteudo().length() < 5 || postagem.getConteudo().length() > 500){
//            throw new IllegalArgumentException("O conteudo deve ter entre 5 e 500 caracteres");
//        }
//        if (postagem.getUsuario() == null){
//            throw new IllegalArgumentException("A postagem deve pertencer a um usuario");
//        }
        if (conteudo.isBlank() || conteudo.length() < 5 || conteudo.length() > 500){
            throw new IllegalArgumentException("O conteudo deve ter entre 5 e 500 caracteres");
        }
        if (usuario == null){
            throw new IllegalArgumentException("A postagem deve pertencer a um usuario");
        }
        Postagem postagemSalva = new Postagem(conteudo, usuario);
        try {
            postagemRepository.salvar(postagemSalva);
        } catch (RollbackException e) {
            throw new RuntimeException("Acontecu um erro ao salvar a postagem: " + e.getMessage());
        }
    }

    public void deletarPostagem(Integer id, Usuario usuario){
        Postagem postagemAchada = postagemRepository.buscarPorId(id);
        if (postagemAchada == null){
            throw new IllegalArgumentException("Esta postagem não existe!");
        }
        if (!postagemAchada.getUsuario().equals(usuario)){
            throw new IllegalArgumentException("Voce não é o dono desta postagem!");
        }

        postagemRepository.remover(postagemAchada);
    }

    public String listarPostagens(Usuario usuario){
        if (usuario == null){
            throw new IllegalArgumentException("O usuario deve estar logado para listar postagens");
        }
        if (usuario.getPostagens().isEmpty()){
            return "Você não tem postagens";
        }
        StringBuilder postagens = new StringBuilder();
        for (Postagem postagem : usuario.getPostagens()){
            postagens.append(postagem.getId()).append(" - ").append(postagem.getConteudo()).append("\n");
        }
        return postagens.toString();
    }
}

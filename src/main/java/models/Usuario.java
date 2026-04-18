package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.List;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Usuario {
    @EmbeddedId
    private UsuarioId usuarioId;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "usuario")
    private List<Postagem> postagens;

    @CurrentTimestamp
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;

    public Usuario(String nome, String email, Date dataNascimento) {
        this.usuarioId = new UsuarioId(email);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}

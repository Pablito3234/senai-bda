package models;

import jakarta.persistence.*;
import lombok.NonNull;
import org.hibernate.type.descriptor.jdbc.DateJdbcType;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;

import java.sql.Date;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private Date dataNascimento;

    @OneToMany(mappedBy = "usuario")
    private List<Postagem> postagens;
}

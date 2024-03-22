package com.netlify.lggdev.crudsistemamedico.model.paciente;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Table(name = "paciente")
public class PacienteEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToMany(
            mappedBy = "paciente",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<EnderecoEntity> listaEnderecos;

    @OneToMany(
            mappedBy = "paciente",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<EnderecoEntity> consultas;

    @Override
    public boolean isNew() {
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
